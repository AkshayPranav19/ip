import java.util.Scanner;

public class NexBot {
    private static final String INDENT = "     ";
    private static final String LINE = "____________________________________________________________";

    public static void main(String[] args) {
        String logo =
                 "     "+"  _   _            ____        _   \n"
                +"     "        + " | \\ | | _____  __| __ )  ___ | |_ \n"
                +"     "        + " |  \\| |/ _ \\ \\/ /|  _ \\ / _ \\| __|\n"
                +"     "        + " | |\\  |  __/>  < | |_) | (_) | |_ \n"
                +"     "        + " |_| \\_|\\___/_/\\_\\|____/ \\___/ \\__|\n";

        System.out.println(INDENT + LINE);
        System.out.println(INDENT+"Hello! I'm");
        System.out.println(logo);
        System.out.println(INDENT + "What can I do for you?");
        System.out.println(INDENT + LINE);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();

            System.out.println(INDENT + LINE);

            if (line.equalsIgnoreCase("bye")) {
                System.out.println(INDENT + "bye");
                System.out.println(INDENT + LINE);
                break;
            }

            System.out.println(INDENT + line);
            System.out.println(INDENT + LINE);
        }

        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.println(INDENT + "Shutting down NexBot...");
        System.out.println(INDENT + LINE);

        scanner.close();

    }
}
