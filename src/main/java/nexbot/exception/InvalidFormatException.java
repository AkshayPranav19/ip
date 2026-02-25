package nexbot.exception;

/**
 * Signals that a command was entered with an invalid format.
 * <p>
 * Provides a usage hint to guide the user toward the correct command format.
 */
public class InvalidFormatException extends NexBotException {

    /**
     * Constructs an {@code InvalidFormatException} with a usage message.
     *
     * @param usage The correct command usage format to be shown to the user.
     */
    public InvalidFormatException(String usage) {
        super("You used Invalid command format. Learn the correct Format: \n " + usage);
    }
}
