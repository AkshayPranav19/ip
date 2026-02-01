public class Parser {

    public static Command toCommandType(String s) {
        String[] splitInput = s.split(" ", 2);
        String firstCommand = splitInput[0].toLowerCase();
        String secondCommand = splitInput.length > 1 ? splitInput[1] : "";

        switch (firstCommand) {
        case "bye":
            return new Command(CommandType.BYE);

        case "list":
            return new Command(CommandType.LIST);

        case "mark":
            return new Command(CommandType.MARK, secondCommand);


        case "unmark":
            return new Command(CommandType.UNMARK, secondCommand);


        case "todo":
            return new Command(CommandType.TODO, secondCommand);

        case "deadline":
            String[] deadlineTask = secondCommand.split(" /by ", 2);
            return new Command(CommandType.DEADLINE, deadlineTask[0], deadlineTask[1]);

        case "event":
            String[] eventTask = secondCommand.split(" /from | /to ", 3);
            return new Command(CommandType.EVENT, eventTask[0], eventTask[1], eventTask[2]);

        default:
            return new Command(CommandType.UNKNOWN, "", "");
        }

    }


}
