package nexbot.exception;

public class TaskLimitException extends NexBotException {

    public TaskLimitException(int limit) {
        super("nexbot.task.Task limit reached. nexbot.NexBot can only store up to " + limit + "tasks.");
    }
}
