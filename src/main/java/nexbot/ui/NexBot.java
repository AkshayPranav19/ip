package nexbot.ui;

import nexbot.command.Command;
import nexbot.command.Executor;
import nexbot.command.Parser;
import nexbot.exception.NexBotException;

import java.util.Scanner;

/**
 * Entry point of the NexBot application.
 * <p>
 * Handles user input, delegates command parsing to {@link Parser},
 * and executes commands using {@link Executor}.
 */
public class NexBot {

    /**
     * Starts the NexBot application and continuously processes user input
     * until an exit command is issued.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Printer printer = new Printer();
        Executor executor = new Executor();

        do {
            String userInput = scanner.nextLine().trim();
            try {
                Command command = Parser.parseCommand(userInput);
                executor.execute(command);
            } catch (NexBotException e) {
                printer.showError(e.getMessage());
            }

        } while (!executor.shouldExit());

    }
}