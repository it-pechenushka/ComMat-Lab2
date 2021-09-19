package service.message.helper;

public class MessageHolder {
    public static final String UPPER = "верхний";
    public static final String LOWER = "нижний";
    public static final String NO = "нет";
    public static final String YES = "да";
    public static final String INVALID_NUMBER_FORMAT_MESSAGE = "Запись не должна содержать буквы и должна сожержать единственное число!\nДесятичные числа разделяются запятой!";
    public static final String INFINITY_VALUE_MESSAGE = "Заданное число интерпретируется как бесконечность! Попробуйте ввести другое число приближенное к данному.";
    public static final String INVALID_RANGE_MESSAGE = "Введенный предел не подходит к заданной функции по ОДЗ! Введите другой.";
    public static final String ENTER_THE_ACCURACY_MESSAGE = "Введите точность вычисления. " + MessageColor.RED + "Максимум до 6 знаков после запятой!" + MessageColor.RESET;
    public static final String INCORRECT_ACCURACY_MESSAGE = "Задайте точность в пределах от [1, 0.000001]!";
    public static final String WELCOME_MESSAGE = "Добро пожаловать!\nДанная программа выполняет численное интегрирование функции.";
    public static final String CONFIRM_CONTINUE_MESSAGE = "Будут выполнены некотрые преобразования. Хотите применить их? ({да} {нет})";
    public static final String ENTER_CORRECT_INPUT_MESSAGE = "Введите корректный ответ.";
    public static final String ERROR_CONVERGE_INTEGRAL_MESSAGE = "Интеграл неопределен на заданном промежутке!";
    public static final String PROGRAM_EXIT_MESSAGE = "Завершение программы.";
    public static final String WRONG_EQUATION_NUMBER_MESSAGE = "Проверьте корректность введенных данных! Запись должна содержать едиственную цифру {1, 2, 3, 4}.";
    public static final String CHOOSE_EQUATION_MESSAGE = "Выберете уравнение для интегрирования(укажите цифру):" +
            "\n1).      x^2" +
            "\n2).      1/x" +
            "\n3).      sin(x)" +
            "\n4).      ln(x)";
}
