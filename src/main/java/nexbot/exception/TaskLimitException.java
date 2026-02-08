package nexbot.exception;

public class TaskLimitException extends NexBotException {

    public TaskLimitException(int limit) {
        super("Task limit reached. NexBot can only store up to " + limit + "tasks.");
    }
}
