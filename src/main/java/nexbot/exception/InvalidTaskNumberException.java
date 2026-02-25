package nexbot.exception;

/**
 * Signals that the user provided an invalid task number.
 * <p>
 * Thrown when the specified task number is out of range or not valid.
 */
public class InvalidTaskNumberException extends NexBotException {

    /**
     * Constructs an {@code InvalidTaskNumberException}
     * with a predefined error message.
     */
    public InvalidTaskNumberException() {
        super("NexBot is upset! You Entered a wrong task number! ");
    }
}
