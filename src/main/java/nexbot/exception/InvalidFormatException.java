package nexbot.exception;

public class InvalidFormatException extends NexBotException {

    public InvalidFormatException(String usage) {
        super("You used Invalid command format. Learn the correct Format: \n " + usage);
    }
}
