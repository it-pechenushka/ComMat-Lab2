package service.message;

import service.message.helper.MessageColor;

public class SolvingMessagePrinter extends MessagePrinter {
    public void printResult(double res){
        printConsoleBorder();
        printInformation("Значение интеграла: " + MessageColor.YELLOW + res + MessageColor.RESET);
    }

    public void printNumberOfSplits(int n){
        printConsoleBorder();
        System.out.println("Количество разбиений: " + MessageColor.YELLOW + n + MessageColor.RESET);
    }

    public void printNoise(double noise){
        printConsoleBorder();
        printInformation("Погрешность: " + MessageColor.YELLOW + noise + MessageColor.RESET + " || " + MessageColor.YELLOW + noise * 100.0 + "%" + MessageColor.RESET);
        printConsoleBorder();
    }

    public void printFunctionGapMessage(String type, double newVal, int gap) throws InterruptedException {
        printInformation("Во введенном вами пределе {" + MessageColor.BLUE + type + MessageColor.RESET + "} - функция терпит разрыв " + gap +"-го рода");
        Thread.sleep(100);
        printInformation("Предел будет заменен на приближенное к заданному числу значение: " + MessageColor.YELLOW + newVal + MessageColor.RESET);
    }
}
