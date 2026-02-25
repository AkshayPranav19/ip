package nexbot.exception;

/**
 * Signals that the user entered an unknown or unsupported command.
 */
public class InvalidCommandException extends NexBotException {

    /**
     * Constructs an {@code InvalidCommandException} with a predefined error message.
     */
    public InvalidCommandException() {
        super("NexBot thinks this is an unknown syntax. Give correct Syntax! ");
    }
}
