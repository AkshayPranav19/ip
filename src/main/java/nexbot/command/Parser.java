package nexbot.command;

import nexbot.exception.InvalidCommandException;
import nexbot.exception.InvalidFormatException;
import nexbot.exception.InvalidTaskNumberException;
import nexbot.exception.NexBotException;

public class Parser {
    public static final String INDENT = "     ";
    public static final String TODO_FORMAT = INDENT + "todo <description>";
    public static final String DEADLINE_FORMAT = INDENT + "deadline <description> /by <time>";
    public static final String EVENT_FORMAT = INDENT + "event <description> /from <time> /to <time>";
    public static final String MARK_FORMAT = INDENT + "mark <task number>";
    public static final String UNMARK_FORMAT = INDENT + "unmark <task number>";

    private static final String BY_DELIMITER = " /by ";
    private static final String FROM_DELIMITER = " /from ";
    private static final String TO_DELIMITER = " /to ";
    private static final int EXPECTED_DEADLINE_PARTS = 2;
    private static final int EXPECTED_EVENT_PARTS = 3;

    public static Command parseCommand(String input) throws NexBotException {
        String[] splitInput = input.trim().split(" ", 2);
        String command = splitInput[0].toLowerCase();
        String arguments = splitInput.length > 1 ? splitInput[1].trim() : "";

        switch (command) {
        case "bye":
            return new Command(CommandType.BYE);

        case "list":
            return new Command(CommandType.LIST);

        case "mark":
            validateNonEmpty(arguments, MARK_FORMAT);
            validateNumeric(arguments);
            return new Command(CommandType.MARK, arguments);

        case "unmark":
            validateNonEmpty(arguments, UNMARK_FORMAT);
            validateNumeric(arguments);
            return new Command(CommandType.UNMARK, arguments);

        case "todo":
            validateNonEmpty(arguments, TODO_FORMAT);
            return new Command(CommandType.TODO, arguments);

        case "deadline":
            return parseDeadline(arguments);

        case "event":
            return parseEvent(arguments);

        default:
            throw new InvalidCommandException();
        }
    }

    private static Command parseDeadline(String arguments) throws NexBotException {
        validateNonEmpty(arguments, DEADLINE_FORMAT);
        String[] parts = arguments.split(BY_DELIMITER, 2);
        if (parts.length < EXPECTED_DEADLINE_PARTS || hasEmptyPart(parts)) {
            throw new InvalidFormatException(DEADLINE_FORMAT);
        }
        return new Command(CommandType.DEADLINE, parts[0].trim(), parts[1].trim());
    }

    private static Command parseEvent(String arguments) throws NexBotException {
        validateNonEmpty(arguments, EVENT_FORMAT);
        String[] parts = arguments.split(FROM_DELIMITER + "|" + TO_DELIMITER, 3);
        if (parts.length < EXPECTED_EVENT_PARTS || hasEmptyPart(parts)) {
            throw new InvalidFormatException(EVENT_FORMAT);
        }
        return new Command(CommandType.EVENT, parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    private static void validateNonEmpty(String input, String format) throws InvalidFormatException {
        if (input.isEmpty()) {
            throw new InvalidFormatException(format);
        }
    }

    private static void validateNumeric(String input) throws InvalidTaskNumberException {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

    private static boolean hasEmptyPart(String[] parts) {
        for (String part : parts) {
            if (part.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}