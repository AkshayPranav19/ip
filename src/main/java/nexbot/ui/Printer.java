package nexbot.ui;

import nexbot.task.Task;

import java.util.ArrayList;

public class Printer {
    public static final String INDENT = "     ";
    public static final String DIVIDER_LINE = "____________________________________________________________";
    public static final String logo =
            "     " + "  _   _            ____        _   \n"
                    + "     " + " | \\ | | _____  __| __ )  ___ | |_ \n"
                    + "     " + " |  \\| |/ _ \\ \\/ /|  _ \\ / _ \\| __|\n"
                    + "     " + " | |\\  |  __/>  < | |_) | (_) | |_ \n"
                    + "     " + " |_| \\_|\\___/_/\\_\\|____/ \\___/ \\__|\n";

    public void showGreeting() {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + "Hello! I'm");
        System.out.println(logo);
        System.out.println(INDENT + "NexBot is your personal task manager. I'm here to help. What can I do for you?");
        System.out.println(INDENT + DIVIDER_LINE);
    }

    public void showBye() {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
        System.out.println(INDENT + "Shutting down NexBot...");
        System.out.println(INDENT + DIVIDER_LINE);
    }

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

    public void showAdded(Task task, int taskCount) {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + INDENT + task.toString());
        System.out.println(INDENT + "Now you have " + taskCount + " tasks in the list.");
        System.out.println(INDENT + DIVIDER_LINE);

    }

    public void showMarked(Task task) {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + INDENT + task.toString());
        System.out.println(INDENT + DIVIDER_LINE);
    }

    public void showUnmarked(Task task) {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + INDENT + task.toString());
        System.out.println(INDENT + DIVIDER_LINE);
    }

    public void showDefault() {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + DIVIDER_LINE);
    }

    public void showError(String message) {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + message);
        System.out.println(INDENT + DIVIDER_LINE);
    }

    public void showDeleted(Task task, int taskCount) {
        System.out.println(INDENT + DIVIDER_LINE);
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + INDENT + task.toString());
        System.out.println(INDENT + "Now you have " + taskCount + " tasks in the list.");
        System.out.println(INDENT + DIVIDER_LINE);
    }


}
