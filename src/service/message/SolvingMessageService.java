package service.message;

public class SolvingMessageService extends MessageService {
    public void showResult(double res){
        System.out.println("***********************************************************");
        System.out.println("Значение интеграла: " + MessageColor.YELLOW + res + MessageColor.RESET);
    }

    public void showNumOfSplits(int n){
        System.out.println("***********************************************************");
        System.out.println("Количество разбиений: " + MessageColor.YELLOW + n + MessageColor.RESET);
    }

    public void showFault(double fault){
        System.out.println("***********************************************************");
        System.out.println("Погрешность: " + MessageColor.YELLOW + fault + MessageColor.RESET + " || " + MessageColor.YELLOW + fault * 100.0 + "%" + MessageColor.RESET);
        System.out.println("***********************************************************");
    }

    public void funcGapMessage(String type, double newVal, int gap) throws InterruptedException {
        System.err.println("Во введенном вами " + MessageColor.BLUE + type + MessageColor.RESET + " пределе функция терпит разрыв " + gap +"-го рода");
        Thread.sleep(100);
        System.out.println("Предел будет заменен на приближенное к заданному числу значение: " + MessageColor.YELLOW + newVal + MessageColor.RESET);
    }

    public void askAboutContinueMessage(){
        System.out.println("Будут выполнены некотрые преобразования. Хотите применить их? ({да} {нет})");
    }

    public void inputCorrectMessage(){
        System.err.println("Введите корректный ответ.");
    }

    public void notConvergeIntegralMessage(){
        System.err.println("Интеграл неопределен на заданном промежутке!");
    }
}
