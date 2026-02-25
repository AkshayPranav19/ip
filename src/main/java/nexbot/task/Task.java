package nexbot.task;

/**
 * Represents a generic task in the task list.
 * <p>
 * A {@code Task} has a description and a completion status. Specific task types
 * such as {@code ToDo}, {@code Deadline}, and {@code Event} extend this class.
 */
public abstract class Task {
    private final String taskDescription;
    private boolean isDone;

    /**
     * Constructs a {@code Task} with the given description.
     *
     * @param taskDescription Description of the task.
     */
    public Task(String taskDescription) {
        this.isDone = false;
        this.taskDescription = taskDescription;
    }

    /**
     * Marks this task as completed.
     */
    public void markTask() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void unmarkTask() {
        this.isDone = false;
    }

    /**
     * Returns whether this task has been completed.
     *
     * @return {@code true} if the task is done, otherwise {@code false}.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the description of this task.
     *
     * @return Task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Returns the status icon representing completion state.
     *
     * @return {@code [X]} if completed, otherwise {@code [ ]}.
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns a user-friendly string representation of this task.
     *
     * @return Formatted string for display.
     */
    @Override
    public abstract String toString();

    /**
     * Returns a string representation of this task suitable for storage.
     *
     * @return Formatted string used for saving this task.
     */
    public abstract String toStorageString();
}