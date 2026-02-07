package nexbot.exception;

public class InvalidCommandException extends NexBotException{
    public InvalidCommandException(){
        super("nexbot.NexBot thinks this is an unknown syntax. Give correct Syntax! ");
    }
}
