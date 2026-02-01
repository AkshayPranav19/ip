import java.util.Scanner;

public class NexBot {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Executor executor = new Executor();

        do {
            String userInput = scanner.nextLine().trim();
            Command command = Parser.toCommandType(userInput);
            executor.execute(command);

        } while (!executor.shouldExit());

    }
}