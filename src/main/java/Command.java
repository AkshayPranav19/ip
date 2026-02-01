public class Command {
    private final CommandType type;
    private final String arg1;
    private final String arg2;
    private final String arg3;


    public Command(CommandType type) {
        this.type = type;
        this.arg1 = "";
        this.arg2 = "";
        this.arg3 = "";
    }

    public Command(CommandType type, String arg1) {
        this.type = type;
        this.arg1 = arg1;
        this.arg2 = "";
        this.arg3 = "";
    }

    public Command(CommandType type, String arg1, String arg2) {
        this.type = type;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = "";
    }

    public Command(CommandType type, String arg1, String arg2, String arg3) {
        this.type = type;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.arg3 = arg3;
    }

    public CommandType getType() {
        return type;
    }

    public String getArg1() {
        return arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public String getArg3() {
        return arg3;
    }
}
