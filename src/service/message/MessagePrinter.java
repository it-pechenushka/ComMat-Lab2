package service.message;

public class MessagePrinter implements Printer {
    public void printConsoleBorder(){
        System.out.println(".................................................................");
    }

    @Override
    public void printInformation(String message) {
        System.out.println(message);
    }

    @Override
    public void printError(String message) {
        System.err.println(message);
    }
}

