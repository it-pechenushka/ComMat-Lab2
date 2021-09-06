import service.message.MessageService;
import service.work.EqService;
import java.util.Scanner;

public class Controller {
    private String equationNumber;
    private boolean dataStatus;
    private MessageService messageService;

    public Controller(){
        this.messageService = new MessageService();
        messageService.doing();
    }

    public void setEquationNumber(){
        Scanner inp = new Scanner(System.in);
        equationNumber = inp.nextLine().trim().toLowerCase();
    }


    public void start(){
        messageService.showEquationsMessage();

        do{
            setEquationNumber();
            dataStatus = equationNumber.equals("1") || equationNumber.equals("2") || equationNumber.equals("3") || equationNumber.equals("4");

            if(!dataStatus) messageService.wrongEquationNumberMessage();
        } while (!dataStatus);

        new EqService(Integer.parseInt(equationNumber)).doing();
    }
}
