package nexbot.command;

import nexbot.exception.NexBotException;
import nexbot.storage.Storage;
import nexbot.task.*;
import nexbot.ui.Printer;
import nexbot.util.DateTimeUtil;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Executes parsed {@link Command} objects.
 * <p>
 * Handles task creation, updates, deletion, filtering, and user interaction
 * through {@link Printer} while ensuring tasks are persisted using {@link Storage}.
 */
public class Executor {

    private static final Printer printer = new Printer();
    private final Storage storage = new Storage();
    private final TaskList tasks;
    private boolean shouldExit;

    /**
     * Constructs an {@code Executor}, loads saved tasks from storage,
     * and displays the greeting message.
     */
    public Executor() {
        this.shouldExit = false;
        this.tasks = new TaskList(storage.load());
        printer.showGreeting();
    }

    /**
     * Executes the given command and performs the corresponding action.
     *
     * @param command Command to execute.
     * @throws NexBotException If execution fails due to invalid task number or input.
     */
    public void execute(Command command) throws NexBotException {

        switch (command.type()) {
        case BYE:
            printer.showBye();
            this.shouldExit = true;
            break;

        case LIST:
            printer.showTasks(tasks.getTasks());
            break;

        case MARK:
            updateMarkStatus(command, true);
            break;

        case UNMARK:
            updateMarkStatus(command, false);
            break;

        case TODO:
            addTask(new ToDo(command.param1()));
            break;

        case DEADLINE:
            addTask(new Deadline(command.param1(), command.param2()));
            break;

        case EVENT:
            addTask(new Event(command.param1(), command.param2(), command.param3()));
            break;

        case DELETE:
            deleteTask(command);
            break;

        case FILTER:
            filterByDate(command);
            break;

        case FIND:
            filterByKeyword(command);
            break;

        default:
            printer.showDefault();
            break;
        }

    }

    /**
     * Indicates whether the application should terminate.
     *
     * @return {@code true} if the last executed command requested exit.
     */
    public boolean shouldExit() {
        return shouldExit;
    }

    /**
     * Updates the completion status of a task.
     *
     * @param command Command containing the task number.
     * @param isMark  {@code true} to mark as done, {@code false} to unmark.
     * @throws NexBotException If the task number is invalid.
     */
    private void updateMarkStatus(Command command, boolean isMark) throws NexBotException {
        int taskNumber = Integer.parseInt(command.param1());
        Task task = tasks.get(taskNumber);
        if (isMark) {
            task.markTask();
            storage.save(tasks.getTasks());
            printer.showMarked(task);
        } else {
            task.unmarkTask();
            storage.save(tasks.getTasks());
            printer.showUnmarked(task);
        }
    }

    /**
     * Adds a new task and saves the updated task list.
     *
     * @param task Task to be added.
     * @throws NexBotException If saving fails.
     */
    private void addTask(Task task) throws NexBotException {
        tasks.add(task);
        storage.save(tasks.getTasks());
        printer.showAdded(task, tasks.size());
    }

    /**
     * Deletes a task by its task number.
     *
     * @param command Command containing the task number.
     * @throws NexBotException If the task number is invalid.
     */
    private void deleteTask(Command command) throws NexBotException {
        int taskNumber = Integer.parseInt(command.param1());
        Task removed = tasks.remove(taskNumber);
        storage.save(tasks.getTasks());
        printer.showDeleted(removed, tasks.size());
    }

    /**
     * Filters tasks that occur on a specific date and displays the results.
     *
     * @param command Command containing the date to filter by.
     * @throws NexBotException If the date format is invalid.
     */
    private void filterByDate(Command command) throws NexBotException {
        LocalDate date = DateTimeUtil.parseDateOnly(command.param1(), Parser.FILTER_FORMAT);
        ArrayList<Task> matches = new ArrayList<>();
        for (Task t : tasks.getTasks()) {
            if (t instanceof Deadline d && d.getBy().toLocalDate().equals(date)) {
                matches.add(t);
            } else if (t instanceof Event e
                    && (e.getFrom().toLocalDate().equals(date) || e.getTo().toLocalDate().equals(date))) {
                matches.add(t);
            }
        }
        printer.showTasks(matches);
    }

    /**
     * Filters tasks that contain a given keyword in their description and displays matches.
     *
     * @param command Command containing the keyword to search for.
     */
    private void filterByKeyword(Command command) {
        String keyword = command.param1().toLowerCase();
        ArrayList<Task> matches = new ArrayList<>();
        for (Task t : tasks.getTasks()) {
            if (t.getTaskDescription().toLowerCase().contains(keyword)) {
                matches.add(t);
            }
        }
        printer.showMatchingTasks(matches);
    }
}
