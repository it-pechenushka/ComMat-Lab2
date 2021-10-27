package service.equation;

import exceptions.InfinityValueException;
import exceptions.InvalidRangeException;
import method.TrapezoidMethod;
import model.EquationEntity;
import service.Service;
import service.message.EquationMessagePrinter;

import java.util.*;

import static service.message.helper.MessageHolder.*;

public class EquationService implements Service {
    private EquationMessagePrinter printer;
    private String equation;
    private int equationNumber;
    private static final Map<Integer, String> equationExpressions = new HashMap<Integer, String>(){{
            put(1, "x^2");
            put(2, "1/x");
            put(3, "sin(x)");
            put(4, "ln(x)");
        }};

    public EquationService(int equationNumber){
        this.equationNumber = equationNumber;
        equation = equationExpressions.get(equationNumber);
        printer = new EquationMessagePrinter();
        printer.printEquationExpression(equation);
    }

    @Override
    public void doing() {
        double lowerLimit = enterLimit(LOWER);
        double upperLimit = enterLimit(UPPER);
        double accuracy = enterAccuracy();

        if(upperLimit < lowerLimit){
            double tmp = lowerLimit;
            lowerLimit = upperLimit;
            upperLimit = tmp;
        }

        printer.printLimits(upperLimit, lowerLimit);
        printer.printAccuracy(accuracy);

        new TrapezoidMethod(new EquationEntity(equationNumber, upperLimit, lowerLimit, accuracy)).solve();
    }

    private double enterAccuracy(){
        Scanner scanner;
        printer.printInformation(ENTER_THE_ACCURACY_MESSAGE);
        double accuracy;

        while (true){
            try {
                scanner = new Scanner(System.in);
                accuracy = scanner.nextDouble();

                if(accuracy >= 0.000001 && accuracy <= 1)
                    break;

                printer.printError(INCORRECT_ACCURACY_MESSAGE);
            } catch (InputMismatchException e){
                printer.printError(INVALID_NUMBER_FORMAT_MESSAGE);
            }
        }

        return accuracy;
    }

    private double enterLimit(String limitType){
        Scanner scanner;
        printer.printLimitInitMessage(limitType);
        double limit;

        while (true) {
            try {
                scanner = new Scanner(System.in);
                limit = scanner.nextDouble();
                if(Double.isInfinite(limit)) throw new InfinityValueException(INFINITY_VALUE_MESSAGE);

                if (limit < 0 && equationNumber == 4) throw new InvalidRangeException(INVALID_RANGE_MESSAGE);
                break;
            } catch (InputMismatchException e){
                printer.printError(INVALID_NUMBER_FORMAT_MESSAGE);
            } catch (InfinityValueException e) {
                printer.printError(e.getMessage());
            } catch (InvalidRangeException e) {
                printer.printError(e.getMessage());
            }
        }

        return limit;
    }
}
