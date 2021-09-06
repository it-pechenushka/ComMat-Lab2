package service.message;

public class EqMessageService extends MessageService {
    public void initLimitMessage(String type){
        System.out.println("Введите " + MessageColor.CYAN + type + MessageColor.RESET + " предел интегрирования.");
    }

    public void invalidNumberFormatMessage(){
        System.err.println("Запись не должна содержать буквы и должна сожержать единственное число!");
    }

    public void infinityValueMessage(){
        System.err.println("Заданное число интерпретируется как бесконечность! Попробуйте ввести другое число приближенное к данному. ");
    }

    public void invalidRangeMessage(){
        System.err.println("Введенный предел не подходит к заданной функции по ОДЗ! Введите другой.");
    }

    public void showLimits(double up, double low, boolean status){
        System.out.println(".................................................................");

        if(status)
            System.out.println("Пределы после преобразования.");
        else
            System.out.println("Введенные пределы.");

        System.out.println(MessageColor.BLUE + "Верхний: " + MessageColor.YELLOW + up + MessageColor.RESET +
                            "\n" + MessageColor.BLUE + "Нижний: " + MessageColor.YELLOW + low + MessageColor.RESET);
    }

    public void initAccuracyMessage(boolean ws){
        if (!ws) System.out.println("Введите точность вычисления. " + MessageColor.RED + "Максимум до 6 знаков после запятой!" + MessageColor.RESET);
        else System.err.println("Задайте точность в пределах от [1, 0.000001]!");
    }

    public void showAccuracy(double accuracy){
        System.out.println(".................................................................");

        System.out.println("Точность: " + MessageColor.YELLOW + accuracy + MessageColor.RESET);

        System.out.println(".................................................................");
    }

}
