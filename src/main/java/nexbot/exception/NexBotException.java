package nexbot.exception;

/**
 * Represents the base exception type for all NexBot-related errors.
 * <p>
 * Specific exception types extend this class to indicate different error conditions
 * such as invalid commands, formats, or task numbers.
 */
public class NexBotException extends Exception {

    /**
     * Constructs a {@code NexBotException} with the specified error message.
     *
     * @param message Description of the error.
     */
    public NexBotException(String message) {
        super(message);
    }
}
