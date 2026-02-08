package nexbot.command;

public record Command(CommandType type, String param1, String param2, String param3) {

    public Command(CommandType type, String param1, String param2) {
        this(type, param1, param2, "");
    }

    public Command(CommandType type, String param1) {
        this(type, param1, "", "");
    }

    public Command(CommandType type) {
        this(type, "", "", "");
    }
}
