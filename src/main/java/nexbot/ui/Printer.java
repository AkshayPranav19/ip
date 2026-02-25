package nexbot.ui;

import nexbot.task.Task;

import java.util.ArrayList;

/**
 * Handles all user interface output for the NexBot application.
 * <p>
 * Responsible for displaying messages, task lists, and feedback to the user.
 */
public class Printer {
    public static final String INDENT = "     ";
    public static final String DIVIDER_LINE = "____________________________________________________________";
    public static final String logo =
            "     " + "  _   _            ____        _   \n"
                    + "     " + " | \\ | | _____  __| __ )  ___ | |_ \n"
                    + "     " + " |  \\| |/ _ \\ \\/ /|  _ \\ / _ \\| __|\n"
                    + "     " + " | |\\  |  __/>  < | |_) | (_) | |_ \n"
                    + "     " + " |_| \\_|\\___/_/\\_\\|____/ \\___/ \\__|\n";

    /**
     * Displays the greeting message when the application starts.
     */
    public void showGreeting() {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + "Hello! I'm");
        System.out.println(logo);
        System.out.println(INDENT + "NexBot is your personal task manager. I'm here to help. What can I do for you?");
        System.out.println(INDENT + DIVIDER_LINE);
    }

    /**
     * Displays the farewell message and shutdown notice.
     */
    public void showBye() {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.println(INDENT + "Shutting down NexBot...");
        System.out.println(INDENT + DIVIDER_LINE);
    }

    /**
     * Displays a list of tasks.
     *
     * @param tasks List of tasks to display.
     */
    public void showTasks(ArrayList<Task> tasks) {
        int taskCount = tasks.size();
        System.out.println(INDENT + DIVIDER_LINE);
        if (taskCount == 0) {
            System.out.println(INDENT + "No tasks for you. NexBot wants you to take a good rest");
        } else {
            for (int i = 0; i < taskCount; i += 1) {
                System.out.printf(INDENT + "%d. %s%n", i + 1, tasks.get(i).toString());
            }
        }
        System.out.println(INDENT + DIVIDER_LINE);
    }

    /**
     * Displays confirmation that a task was added.
     *
     * @param task      The task that was added.
     * @param taskCount Current total number of tasks.
     */
    public void showAdded(Task task, int taskCount) {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + INDENT + task.toString());
        System.out.println(INDENT + "Now you have " + taskCount + " tasks in the list.");
        System.out.println(INDENT + DIVIDER_LINE);

    }

    /**
     * Displays confirmation that a task was marked as done.
     *
     * @param task The task that was marked.
     */
    public void showMarked(Task task) {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + INDENT + task.toString());
        System.out.println(INDENT + DIVIDER_LINE);
    }

    /**
     * Displays confirmation that a task was unmarked.
     *
     * @param task The task that was unmarked.
     */
    public void showUnmarked(Task task) {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + INDENT + task.toString());
        System.out.println(INDENT + DIVIDER_LINE);
    }

    /**
     * Displays a default separator when no specific output is required.
     */
    public void showDefault() {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + DIVIDER_LINE);
    }

    /**
     * Displays an error message to the user.
     *
     * @param message Error message to display.
     */
    public void showError(String message) {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + message);
        System.out.println(INDENT + DIVIDER_LINE);
    }

    /**
     * Displays confirmation that a task was deleted.
     *
     * @param task      The task that was removed.
     * @param taskCount Current total number of tasks.
     */
    public void showDeleted(Task task, int taskCount) {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + INDENT + task.toString());
        System.out.println(INDENT + "Now you have " + taskCount + " tasks in the list.");
        System.out.println(INDENT + DIVIDER_LINE);
    }

    /**
     * Displays tasks that match a search keyword.
     *
     * @param tasks List of matching tasks.
     */
    public void showMatchingTasks(ArrayList<Task> tasks) {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + "Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(INDENT + (i + 1) + "." + tasks.get(i));
        }
        System.out.println(INDENT + DIVIDER_LINE);
    }


}
