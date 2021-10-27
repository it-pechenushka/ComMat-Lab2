package method;

import exceptions.NotConvergeIntegralException;
import exceptions.NullResultException;
import method.equation.*;
import model.EquationEntity;
import service.message.SolvingMessagePrinter;

import java.util.Scanner;

import static service.message.helper.MessageHolder.*;

public class TrapezoidMethod {
    private EquationEntity equationEntity;
    private SolvingMessagePrinter printer;
    private double step;
    private int n = 2;
    private int numberSign = 1;

    public TrapezoidMethod(EquationEntity equationEntity){
        this.equationEntity = equationEntity;
        printer = new SolvingMessagePrinter();
        step = (equationEntity.getUpperLimit() - equationEntity.getLowerLimit()) / n;
    }

    public void solve(){
        double previewResult;
        double currentResult;
        int equationNumber = equationEntity.equationNumber;
        double lowerLimit = equationEntity.getLowerLimit();
        double upperLimit = equationEntity.getUpperLimit();
        Equation equation;

        try {

            if (equationNumber == 1 || equationNumber == 3) {
                if (lowerLimit == upperLimit)
                    throw new NullResultException();

                equation = equationNumber == 1 ? new FirstEquation() : new ThirdEquation();

            } else {
                if (equationNumber == 2)
                    checkConditionsFor2();
                else
                    checkConditionsFor4();

                upperLimit = equationEntity.getUpperLimit();
                lowerLimit = equationEntity.getLowerLimit();
                step = (upperLimit - lowerLimit) / n;

                equation = equationNumber == 2 ? new SecondEquation() : new FourthEquation();
            }

            previewResult = calculateNormalizeResult(lowerLimit, upperLimit, equation, equationNumber);

            while (true) {
                n *= 2;
                step /= 2.0;

                currentResult = calculateNormalizeResult(lowerLimit, upperLimit, equation, equationNumber);

                if(Math.abs(currentResult - previewResult) / 3.0 <= equationEntity.accuracy) break;
                else previewResult = currentResult;
            }

            printer.printResult(currentResult);
            printer.printNumberOfSplits(n);
            printer.printNoise(Math.abs(Math.abs(currentResult - previewResult) / 3.0));

        } catch (NotConvergeIntegralException e){
            printer.printError(e.getMessage());
            printer.printError(PROGRAM_EXIT_MESSAGE);

        } catch (NullResultException e) {
            printer.printResult(0);
            printer.printNumberOfSplits(0);
            printer.printNoise(0);

        } catch (InterruptedException ignore) { }
    }

    private double calculateNormalizeResult(double lowerLimit, double upperLimit, Equation equation, int equationNumber){
        double tmpResult = calculateEquation(lowerLimit, upperLimit, equation);

        return equationNumber == 2 ? tmpResult * numberSign : tmpResult;
    }

    private void checkConditionsFor2() throws NotConvergeIntegralException, NullResultException, InterruptedException {
        double lowerLimit = equationEntity.getLowerLimit();
        double upperLimit = equationEntity.getUpperLimit();

        if(lowerLimit == 0 && upperLimit == 0)
            throw new NotConvergeIntegralException(ERROR_CONVERGE_INTEGRAL_MESSAGE);

        if (lowerLimit == upperLimit)
            throw new NullResultException();

        if (upperLimit == 0){
            upperLimit += 0.000001;
            printer.printFunctionGapMessage(UPPER, upperLimit, 2);
        }

        if (lowerLimit == 0){
            lowerLimit -= 0.000001;
            printer.printFunctionGapMessage(LOWER, lowerLimit, 2);
        }

        if (Math.abs(lowerLimit) == Math.abs(upperLimit))
            throw new NullResultException();

        if(lowerLimit < 0  && upperLimit > 0) {
            printer.printError(ERROR_CONVERGE_INTEGRAL_MESSAGE);
            printer.printInformation(CONFIRM_CONTINUE_MESSAGE);

            Scanner scanner = new Scanner(System.in);

            while (true){
                String input = scanner.nextLine().trim();

                if(input.equals(YES))
                    break;
                else
                    if(input.equals(NO))
                        throw new NotConvergeIntegralException(ERROR_CONVERGE_INTEGRAL_MESSAGE);
                else
                    printer.printInformation(ENTER_CORRECT_INPUT_MESSAGE);
            }

            lowerLimit = Math.abs(lowerLimit);

            if (upperLimit < lowerLimit){
                numberSign = -1;
                equationEntity.setLowerLimit(upperLimit);
                equationEntity.setUpperLimit(lowerLimit);
            }
        }
    }

    private void checkConditionsFor4() throws NotConvergeIntegralException, NullResultException, InterruptedException {
        double lowerLimit = equationEntity.getLowerLimit();
        double upperLimit = equationEntity.getUpperLimit();

        if(lowerLimit == 0 && upperLimit == 0)
            throw new NotConvergeIntegralException(ERROR_CONVERGE_INTEGRAL_MESSAGE);

        if (lowerLimit == upperLimit)
            throw new NullResultException();

        if (lowerLimit == 0){
            lowerLimit += 0.0000001;
            printer.printFunctionGapMessage(LOWER, lowerLimit, 2);
        }

        if (Math.abs(lowerLimit) == Math.abs(upperLimit))
            throw new NullResultException();

        equationEntity.setLowerLimit(lowerLimit);
    }

    private double calculateEquation(double lowerLimit, double upperLimit, Equation equation){
        double tmpResult = 0.0;

        for(double x = lowerLimit + step; x < upperLimit; x += step) tmpResult +=  equation.getEquationResult(x);

        return (step * 0.5 * (equation.getEquationResult(lowerLimit) + equation.getEquationResult(upperLimit) + 2 * tmpResult));
    }
}
