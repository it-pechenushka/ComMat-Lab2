package service.message;

import service.message.helper.MessageColor;

public class EquationMessagePrinter extends MessagePrinter {

    public void printLimitInitMessage(String type){
        System.out.println("Введите " + MessageColor.CYAN + type + MessageColor.RESET + " предел интегрирования.");
    }

    public void printLimits(double up, double low){
        printConsoleBorder();
        printInformation("Введенные пределы.");

        printInformation(MessageColor.BLUE + "Верхний: " + MessageColor.YELLOW + up + MessageColor.RESET +
                            "\n" + MessageColor.BLUE + "Нижний: " + MessageColor.YELLOW + low + MessageColor.RESET);
    }

    public void printEquationExpression(String type){
        printInformation("Выбранное уравнение: " + MessageColor.RED + type + MessageColor.RESET);
    }

    public void printAccuracy(double accuracy){
        printConsoleBorder();

        printInformation("Точность: " + MessageColor.YELLOW + accuracy + MessageColor.RESET);
        printConsoleBorder();
    }
}
