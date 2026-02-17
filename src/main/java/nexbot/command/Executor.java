package nexbot.command;

import nexbot.exception.InvalidTaskNumberException;
import nexbot.exception.NexBotException;
import nexbot.storage.Storage;
import nexbot.task.Deadline;
import nexbot.task.Event;
import nexbot.task.Task;
import nexbot.task.ToDo;
import nexbot.ui.Printer;

import java.util.ArrayList;

public class Executor {

    private static final Printer printer = new Printer();
    private final Storage storage = new Storage();
    private final ArrayList<Task> tasks;
    private boolean shouldExit;

    public Executor() {
        this.shouldExit = false;
        this.tasks = storage.load();
        printer.showGreeting();
    }

    public void execute(Command command) throws NexBotException {

        switch (command.type()) {
        case BYE:
            printer.showBye();
            this.shouldExit = true;
            break;

        case LIST:
            printer.showTasks(tasks);
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


        default:
            printer.showDefault();
            break;
        }

    }

    public boolean shouldExit() {
        return shouldExit;
    }

    private void updateMarkStatus(Command command, boolean isMark) throws NexBotException {
        int index = Integer.parseInt(command.param1()) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        Task task = tasks.get(index);
        if (isMark) {
            task.markTask();
            storage.save(tasks);
            printer.showMarked(task);
        } else {
            task.unmarkTask();
            storage.save(tasks);
            printer.showUnmarked(task);
        }
    }

    private void addTask(Task task) throws NexBotException {
        tasks.add(task);
        storage.save(tasks);
        printer.showAdded(task, tasks.size());
    }

    private void deleteTask(Command command) throws NexBotException {
        int index = Integer.parseInt(command.param1()) - 1;

        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }

        Task removed = tasks.remove(index);
        storage.save(tasks);
        printer.showDeleted(removed, tasks.size());
    }


}
