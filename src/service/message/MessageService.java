package service.message;

import service.Service;

public class MessageService implements Service {
    class MessageColor {
        public static final String RESET = "\u001B[0m";
        public static final String BLACK = "\u001B[30m";
        public static final String RED = "\u001B[31m";
        public static final String GREEN = "\u001B[32m";
        public static final String YELLOW = "\u001B[33m";
        public static final String BLUE = "\u001B[34m";
        public static final String PURPLE = "\u001B[35m";
        public static final String CYAN = "\u001B[36m";
        public static final String WHITE = "\u001B[37m";
    }

    public void showEquationsMessage(){
        System.out.println("Выберете уравнение для интегрирования(укажите цифру):" +
                "\n1).      x^2" +
                "\n2).      1/x" +
                "\n3).      sin(x)" +
                "\n4).      ln(x)");
    }

    public void wrongEquationNumberMessage(){
        System.err.println("Проверьте корректность введенных данных! Запись должна содержать едиственную цифру {1, 2, 3, 4}.");
    }

    public void selectedEquationMessage(String type){
        System.out.println("Выбранное уравнение: " + MessageColor.RED + type + MessageColor.RESET);
    }


    @Override
    public void doing() {
        System.out.println("Добро пожаловать!\nДанная программа выполняет численное интегрирование функции.");
    }
}
