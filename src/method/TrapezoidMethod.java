package method;

import exceptions.NotConvergeIntegralException;
import exceptions.NullResultException;
import javafx.scene.transform.Scale;
import service.message.SolvingMessageService;

import java.util.Scanner;

public class TrapezoidMethod {
    private int eqNum;
    private double high;
    private double low;
    private double accuracy;
    private SolvingMessageService ms;
    private int n = 2;
    private int sign = 1;
    private double step;
    private double preRes;
    private double postRes;

    public TrapezoidMethod(int eqNum, double upperLim, double lowerLim, double accuracy){
        this.eqNum = eqNum;
        this.high = upperLim;
        this.low = lowerLim;
        this.accuracy = accuracy;
        this.ms = new SolvingMessageService();
        this.step = (high - low) / (double)n;
    }

    public void solve(){
        try {
            switch (eqNum){
                case 1:
                    if (low == high) throw new NullResultException();
                    preRes = init1();
                    break;
                case 2:
                    checkConditionsFor2();
                    step = (high - low) / (double)n;
                    preRes = init2();
                    break;
                case 3:
                    if (low == high) throw new NullResultException();
                    preRes = init3();
                    break;
                case 4:
                    checkConditionsFor4();
                    step = (high - low) / (double)n;
                    preRes = init4();
                    break;
            }

            while (true) {
                n *= 2;
                step /= 2.0;

                switch (eqNum) {
                    case 1:
                        postRes = init1();
                        break;
                    case 2:
                        postRes = init2();
                        break;
                    case 3:
                        postRes = init3();
                        break;
                    case 4:
                        postRes = init4();
                        break;
                }

                if(Math.abs(postRes - preRes) / 3.0 <= accuracy) break;
                else preRes = postRes;
            }
        } catch (NotConvergeIntegralException e){
            ms.notConvergeIntegralMessage();
            System.exit(0);
        } catch (NullResultException e) {
            postRes = 0;
            preRes = 0;
            n = 0;
        } catch (InterruptedException e) { }


        ms.showResult(postRes);
        ms.showNumOfSplits(n);
        ms.showFault(Math.abs(Math.abs(postRes - preRes) / 3.0));

    }

    private double init1(){
        double res = 0;

        for(double x = low + step; x < high; x += step) res += x * x;

        return (step * 0.5 * (low * low + high * high + 2 * res));
    }

    private double init2() {
        double res = 0;

        for(double x = low + step; x < high; x += step) res += 1.0 / x;

        return (step * 0.5 * (1.0 / low + 1.0 / high + 2 * res)) * sign;
    }

    private void checkConditionsFor2() throws NotConvergeIntegralException, NullResultException, InterruptedException {
        if(low == 0 && high == 0) throw new NotConvergeIntegralException();
        if (low == high) throw new NullResultException();
        if (high == 0){
            high += 0.000001;
            ms.funcGapMessage("верхнем", high, 2);
        }

        if (low == 0){
            low -= 0.000001;
            ms.funcGapMessage("нижнем", low, 2);
        }

        if (Math.abs(low) == Math.abs(high)) throw new NullResultException();
        if(low < 0  && high > 0) {
            ms.notConvergeIntegralMessage();
            ms.askAboutContinueMessage();
            while (true){
                Scanner scanner = new Scanner(System.in);
                String ans = scanner.nextLine().trim();
                if(ans.equals("да")) break;
                else if(ans.equals("нет")) System.exit(0);
                else ms.inputCorrectMessage();
            }

            low = Math.abs(low);
            if (high < low){
                sign = -1;
                double tmp = low;
                low = high;
                high = tmp;
            }
        }

    }

    private double init3(){
        double res = 0;

        for(double x = low + step; x < high; x += step) res +=  Math.sin(x);

        return (step * 0.5 * (Math.sin(low) + Math.sin(high) + 2 * res));
    }

    private double init4(){
        double res = 0;

        for(double x = low + step; x < high; x += step) res +=  Math.log(x);

        return (step * 0.5 * (Math.log(low) + Math.log(high) + 2 * res));
    }

    private void checkConditionsFor4() throws NotConvergeIntegralException, NullResultException, InterruptedException {
        if(low == 0 && high == 0) throw new NotConvergeIntegralException();
        if (low == high) throw new NullResultException();
        
        if (low == 0){
            low += 0.0000001;
            ms.funcGapMessage("нижнем", low, 2);
        }

        if (Math.abs(low) == Math.abs(high)) throw new NullResultException();
    }
}
