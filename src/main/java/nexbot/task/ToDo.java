package nexbot.task;

public class ToDo extends Task {

    public ToDo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return "[T]" + getStatusIcon() + " " + getTaskDescription();
    }
}
