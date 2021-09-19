import service.message.MessagePrinter;
import service.equation.EquationService;
import service.message.helper.MessageHolder;

import java.util.InputMismatchException;
import java.util.Scanner;

import static service.message.helper.MessageHolder.*;

public class Controller {
    private int equationNumber;
    private MessagePrinter printer;

    public Controller(){
        printer = new MessagePrinter();
        printer.printInformation(WELCOME_MESSAGE);
    }

    public void enterEquationNumber(){
        Scanner input = new Scanner(System.in);
        equationNumber = input.nextInt();
    }

    public void start(){
        printer.printInformation(CHOOSE_EQUATION_MESSAGE);
        boolean correctDataStatus = false;

        while (!correctDataStatus){
            try {
                enterEquationNumber();
                correctDataStatus = equationNumber >= 1 && equationNumber <= 4 ? true : false;

                if(!correctDataStatus)
                    printer.printError(WRONG_EQUATION_NUMBER_MESSAGE);

            }catch (InputMismatchException e){
                printer.printError(WRONG_EQUATION_NUMBER_MESSAGE);
            }
        }

        new EquationService(equationNumber).doing();
    }
}
