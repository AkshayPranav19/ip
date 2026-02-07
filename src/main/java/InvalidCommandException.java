public class InvalidCommandException extends NexBotException{
    public InvalidCommandException(){
        super("NexBot thinks this is an unknown syntax. Give correct Syntax! ");
    }
}
