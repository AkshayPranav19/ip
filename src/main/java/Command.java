public class Command {
    private final CommandType type;
    private final String param1;
    private final String param2;
    private final String param3;


    public Command(CommandType type, String param1, String param2, String param3) {
        this.type = type;
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
    }

    public Command(CommandType type, String param1, String param2) {
        this(type, param1, param2, "");
    }

    public Command(CommandType type, String param1) {
        this(type, param1, "", "");
    }

    public Command(CommandType type) {
        this(type, "", "", "");
    }


    public CommandType getType() {
        return type;
    }

    public String getParam1() {
        return param1;
    }

    public String getParam2() {
        return param2;
    }

    public String getParam3() {
        return param3;
    }
}
