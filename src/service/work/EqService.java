package service.work;

import exceptions.InfinityValueException;
import exceptions.InvalidRangeException;
import method.TrapezoidMethod;
import service.Service;
import service.message.EqMessageService;

import javax.xml.soap.SAAJResult;
import java.util.HashMap;
import java.util.Scanner;

public class EqService implements Service {
    private int eqNum;
    private static String type;
    private double upperLim;
    private double lowerLim;
    private double accuracy;
    private EqMessageService ms;
    private boolean limStatus;

    public EqService(int eqNum){

        this.eqNum = eqNum;
        type = getFormula(eqNum);
        ms = new EqMessageService();
        this.eqNum = eqNum;

        ms.selectedEquationMessage(type);
    }

    @Override
    public void doing() {
        setLowerLim();
        setUpperLim();
        setAccuracy();
//        if(upperLim == lowerLim) {
//            System.err.println();
//            System.exit(0);
//        }


        limStatus = getLimStatus();

        ms.showLimits(upperLim, lowerLim, limStatus);
        ms.showAccuracy(accuracy);

        new TrapezoidMethod(eqNum, upperLim, lowerLim, accuracy).solve();
    }

    public void setAccuracy(){
        Scanner scanner = new Scanner(System.in);
        ms.initAccuracyMessage(false);

        while (true){
            try {
                accuracy = Double.parseDouble(scanner.nextLine().trim());

                if(accuracy >= 0.000001 && accuracy <= 1) break;
                else ms.initAccuracyMessage(true);
            } catch (NumberFormatException e){
                ms.invalidNumberFormatMessage();
            }
        }
    }

    public void setUpperLim(){
        Scanner scanner = new Scanner(System.in);
        ms.initLimitMessage("верхний");

        while (true) {
            try {
                upperLim = Double.parseDouble(scanner.nextLine().trim());
                if(Double.isInfinite(upperLim))
                    throw new InfinityValueException();
                if (upperLim < 0 && eqNum == 4) throw new InvalidRangeException();
                break;
            } catch (NumberFormatException e){
                ms.invalidNumberFormatMessage();
            } catch (InfinityValueException e) {
                ms.infinityValueMessage();
            } catch (InvalidRangeException e) {
                ms.invalidRangeMessage();
            }
        }
    }

    public void setLowerLim(){
        Scanner scanner = new Scanner(System.in);
        ms.initLimitMessage("нижний");

        while (true){
            try {
                lowerLim = Double.parseDouble(scanner.nextLine().trim());
                if(Double.isInfinite(lowerLim))
                    throw new InfinityValueException();
                if (lowerLim < 0 && eqNum == 4) throw new InvalidRangeException();
                break;
            } catch (NumberFormatException e){
                ms.invalidNumberFormatMessage();
            } catch (InfinityValueException e) {
                ms.infinityValueMessage();
            } catch (InvalidRangeException e) {
                ms.invalidRangeMessage();
            }
        }
    }

    private boolean getLimStatus(){
        if(upperLim < lowerLim){
            double tmp = lowerLim;
            lowerLim = upperLim;
            upperLim = tmp;
            return true;
        }

        return false;
    }

    private String getFormula(int num){
        switch (num){
            case 1:
                return "x^2";
            case 2:
                return "1/x";
            case 3:
                return "sin(x)";
            case 4:
                return "ln(x)";
            default:
                return null;
        }
    }
}
