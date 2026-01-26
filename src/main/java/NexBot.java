import javax.swing.plaf.PanelUI;
import java.util.Scanner;

public class NexBot {
    public static final String INDENT = "     ";
    public static final String LINE = "____________________________________________________________";
    public static final int MAX_TASKS = 100;

    public static void printGreeting(){
        String logo =
                "     "+"  _   _            ____        _   \n"
                        +"     "        + " | \\ | | _____  __| __ )  ___ | |_ \n"
                        +"     "        + " |  \\| |/ _ \\ \\/ /|  _ \\ / _ \\| __|\n"
                        +"     "        + " | |\\  |  __/>  < | |_) | (_) | |_ \n"
                        +"     "        + " |_| \\_|\\___/_/\\_\\|____/ \\___/ \\__|\n";

        System.out.println(INDENT + LINE);
        System.out.println(INDENT+"Hello! I'm");
        System.out.println(logo);
        System.out.println(INDENT + "NexBot is your personal task manager. I'm here to help. What can I do for you?");
        System.out.println(INDENT + LINE);
    }

    public static void printBye(){
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.println(INDENT + "Shutting down NexBot...");
        System.out.println(INDENT + LINE);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[MAX_TASKS];
        int taskCount = 0;

        printGreeting();

        while (true) {
            String userInput = scanner.nextLine().trim();
            System.out.println(INDENT + LINE);
            if (userInput.equalsIgnoreCase("bye")) {
                printBye();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                if (taskCount == 0){
                    System.out.println(INDENT + "No tasks for you. NexBot wants you to take a good rest");
                }else{
                    for (int i=0; i<taskCount; i+=1){
                        System.out.printf(INDENT + "%d. %s%n", i+1, tasks[i]);
                    }
                }

            }else if(userInput.isEmpty()){
                continue;
            }else{
                tasks[taskCount] = userInput;
                taskCount += 1;
                System.out.println(INDENT + "Added: " + userInput);
            }
            System.out.println(INDENT + LINE);
        }

        scanner.close();

    }
}
