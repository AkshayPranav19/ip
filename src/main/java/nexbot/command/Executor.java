package nexbot.command;

import nexbot.exception.NexBotException;
import nexbot.storage.Storage;
import nexbot.task.*;
import nexbot.ui.Printer;
import nexbot.util.DateTimeUtil;

import java.time.LocalDate;
import java.util.ArrayList;

public class Executor {

    private static final Printer printer = new Printer();
    private final Storage storage = new Storage();
    private final TaskList tasks;
    private boolean shouldExit;

    public Executor() {
        this.shouldExit = false;
        this.tasks = new TaskList(storage.load());
        printer.showGreeting();
    }

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

        default:
            printer.showDefault();
            break;
        }

    }

    public boolean shouldExit() {
        return shouldExit;
    }

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

    private void addTask(Task task) throws NexBotException {
        tasks.add(task);
        storage.save(tasks.getTasks());
        printer.showAdded(task, tasks.size());
    }

    private void deleteTask(Command command) throws NexBotException {
        int taskNumber = Integer.parseInt(command.param1());
        Task removed = tasks.remove(taskNumber);
        storage.save(tasks.getTasks());
        printer.showDeleted(removed, tasks.size());
    }

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
}
