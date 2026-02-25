package nexbot.task;
/**
 * Represents a simple task without any date or time constraint.
 * <p>
 * A {@code ToDo} task contains only a description and a completion status.
 */
public class ToDo extends Task {

    /**
     * Constructs a {@code ToDo} task with the given description.
     *
     * @param taskDescription Description of the task.
     */
    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * Returns a user-friendly string representation of this todo task.
     *
     * @return A formatted string including type, status, and description.
     */
    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + getTaskDescription();
    }

    /**
     * Returns a string representation of this task suitable for storage.
     *
     * @return A formatted string used for saving this task.
     */
    @Override
    public String toStorageString() {
        String doneFlag = isDone() ? "1" : "0";
        return "T | " + doneFlag + " | " + getTaskDescription();
    }

}
