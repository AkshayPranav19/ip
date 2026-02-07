import java.util.Scanner;

public class NexBot {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();
        Executor executor = new Executor();

        do {
            String userInput = scanner.nextLine().trim();
            try{
                Command command = Parser.parseCommand(userInput);
                executor.execute(command);
            } catch (NexBotException e){
                printer.showError(e.getMessage());
            }

        } while (!executor.shouldExit());

    }
}