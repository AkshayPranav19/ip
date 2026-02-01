import javax.swing.plaf.PanelUI;
import java.util.Scanner;

public class NexBot {
    public static final String INDENT = "     ";
    public static final String DIVIDER_LINE = "____________________________________________________________";
    public static final int MAX_TASKS = 100;

    public static void printGreeting() {
        String logo =
                "     " + "  _   _            ____        _   \n"
                        + "     " + " | \\ | | _____  __| __ )  ___ | |_ \n"
                        + "     " + " |  \\| |/ _ \\ \\/ /|  _ \\ / _ \\| __|\n"
                        + "     " + " | |\\  |  __/>  < | |_) | (_) | |_ \n"
                        + "     " + " |_| \\_|\\___/_/\\_\\|____/ \\___/ \\__|\n";

        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + "Hello! I'm");
        System.out.println(logo);
        System.out.println(INDENT + "NexBot is your personal task manager. I'm here to help. What can I do for you?");
        System.out.println(INDENT + DIVIDER_LINE);
    }

    public static void printBye() {
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.println(INDENT + "Shutting down NexBot...");
        System.out.println(INDENT + DIVIDER_LINE);
    }

    public static void printTasks(Task[] tasks, int taskCount) {
        if (taskCount == 0) {
            System.out.println(INDENT + "No tasks for you. NexBot wants you to take a good rest");
        } else {
            for (int i = 0; i < taskCount; i += 1) {
                System.out.printf(INDENT + "%d. %s%n", i + 1, tasks[i].toString());
            }
        }
    }

    public static boolean isNumeric(String s) {
        if (s == null || s.isEmpty()) return false;
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAX_TASKS];
        int taskCount = 0;

        printGreeting();

        while (true) {
            String userInput = scanner.nextLine().trim();
            String[] splitInput = userInput.split(" ", 2);
            String firstCommand = splitInput[0].toLowerCase();
            String secondCommand = splitInput.length > 1 ? splitInput[1] : "";
            System.out.println(INDENT + DIVIDER_LINE);

            switch (firstCommand) {
            case "bye":
                printBye();
                scanner.close();
                return;

            case "list":
                printTasks(tasks, taskCount);
                break;

            case "mark":
                if (isNumeric(secondCommand)) {
                    int markIndex = Integer.parseInt(secondCommand) - 1;
                    tasks[markIndex].markTask();
                    System.out.println(INDENT + "Nice! I've marked this task as done:");
                    System.out.println(INDENT + INDENT + tasks[markIndex].toString());
                }
                break;

            case "unmark":
                if (isNumeric(secondCommand)) {
                    int unmarkIndex = Integer.parseInt(secondCommand) - 1;
                    tasks[unmarkIndex].unmarkTask();
                    System.out.println(INDENT + "OK, I've marked this task as not done yet:");
                    System.out.println(INDENT + INDENT + tasks[unmarkIndex].toString());
                }
                break;

            case "todo":
                tasks[taskCount] = new toDo(secondCommand);
                System.out.println(INDENT + "Got it. I've added this task:");
                System.out.println(INDENT + INDENT + tasks[taskCount].toString());
                System.out.println(INDENT + "Now you have " + taskCount + " tasks in the list.");
                taskCount += 1;
                break;

            case "deadline":
                String[] deadlineTask = secondCommand.split(" /by ", 2);
                tasks[taskCount] = new Deadline(deadlineTask[0], deadlineTask[1]);
                System.out.println(INDENT + "Got it. I've added this task:");
                System.out.println(INDENT + INDENT + tasks[taskCount].toString());
                System.out.println(INDENT + "Now you have " + taskCount + " tasks in the list.");
                taskCount += 1;
                break;

            case "event":
                String[] eventTask = secondCommand.split(" /from | /to ", 3);
                tasks[taskCount] = new Event(eventTask[0], eventTask[1], eventTask[2]);
                System.out.println(INDENT + "Got it. I've added this task:");
                System.out.println(INDENT + INDENT + tasks[taskCount].toString());
                System.out.println(INDENT + "Now you have " + taskCount + " tasks in the list.");
                taskCount += 1;
                break;

            default:
                break;
            }

            System.out.println(INDENT + DIVIDER_LINE);
        }


    }
}
