package nexbot.task;

public class Deadline extends Task {
    private final String by;

    public Deadline(String taskDescription, String deadline) {
        super(taskDescription);
        this.by = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + getTaskDescription() + " (by: " + this.by + ")";
    }

    @Override
    public String toStorageString() {
        String doneFlag = isDone() ? "1" : "0";
        return "D | " + doneFlag + " | " + getTaskDescription() + " | " + by;
    }

}
