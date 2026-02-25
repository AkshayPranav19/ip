package nexbot.task;

import nexbot.exception.InvalidTaskNumberException;

import java.util.ArrayList;

/**
 * Represents a collection of {@link Task} objects.
 * <p>
 * Provides 1-based access to tasks as displayed to the user.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} using an existing list of tasks.
     *
     * @param tasks List of tasks to initialise with. If null, an empty list is created.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = (tasks == null) ? new ArrayList<>() : tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task corresponding to the given 1-based task number.
     *
     * @param taskNumber 1-based task number shown to the user.
     * @return The task at the specified position.
     * @throws InvalidTaskNumberException If the task number is out of range.
     */
    public Task get(int taskNumber) throws InvalidTaskNumberException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        return tasks.get(index);
    }

    /**
     * Removes and returns the task corresponding to the given 1-based task number.
     *
     * @param taskNumber 1-based task number shown to the user.
     * @return The removed task.
     * @throws InvalidTaskNumberException If the task number is out of range.
     */
    public Task remove(int taskNumber) throws InvalidTaskNumberException {
        int index = taskNumber - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new InvalidTaskNumberException();
        }
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Total number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}