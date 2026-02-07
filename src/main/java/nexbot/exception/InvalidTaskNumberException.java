package nexbot.exception;

public class InvalidTaskNumberException extends NexBotException{
    public InvalidTaskNumberException(){
        super("nexbot.NexBot is upset! You Entered a wrong task number! ");
    }
}
