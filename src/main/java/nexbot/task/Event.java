package nexbot.task;

public class Event extends Task {
    private final String from;
    private final String to;

    public Event(String taskDescription, String from, String to) {
        super(taskDescription);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + getTaskDescription() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
