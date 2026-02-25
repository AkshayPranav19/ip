package nexbot.command;

import nexbot.exception.InvalidCommandException;
import nexbot.exception.InvalidFormatException;
import nexbot.exception.InvalidTaskNumberException;
import nexbot.exception.NexBotException;


/**
 * Parses raw user input into executable {@link Command} objects.
 * <p>
 * Also defines user-facing format hints used when reporting invalid command formats.
 */
public class Parser {
    public static final String INDENT = "     ";
    public static final String TODO_FORMAT = INDENT + "todo <description>";
    public static final String DEADLINE_FORMAT = INDENT + "deadline <description> /by dd MM yyyy HHmm";
    public static final String EVENT_FORMAT = INDENT + "event <description> /from dd MM yyyy HHmm /to dd MM yyyy HHmm";
    public static final String MARK_FORMAT = INDENT + "mark <task number>";
    public static final String UNMARK_FORMAT = INDENT + "unmark <task number>";
    public static final String DELETE_FORMAT = INDENT + "delete <task number>";
    public static final String FILTER_FORMAT = INDENT + "filter dd MM yyyy";
    public static final String FIND_FORMAT = INDENT + "find <keyword>";

    private static final String BY_DELIMITER = " /by ";
    private static final String FROM_DELIMITER = " /from ";
    private static final String TO_DELIMITER = " /to ";
    private static final int EXPECTED_DEADLINE_PARTS = 2;
    private static final int EXPECTED_EVENT_PARTS = 3;

    /**
     * Parses a full user input line into a {@link Command}.
     *
     * @param input Full user input line.
     * @return The corresponding {@code Command}.
     * @throws NexBotException If the command word is unknown or the arguments are invalid.
     */
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
            validateNotBlank(arguments, MARK_FORMAT);
            validateNumeric(arguments);
            return new Command(CommandType.MARK, arguments);

        case "unmark":
            validateNotBlank(arguments, UNMARK_FORMAT);
            validateNumeric(arguments);
            return new Command(CommandType.UNMARK, arguments);

        case "todo":
            validateNotBlank(arguments, TODO_FORMAT);
            return new Command(CommandType.TODO, arguments);

        case "deadline":
            return parseDeadline(arguments);

        case "event":
            return parseEvent(arguments);

        case "delete":
            return parseDelete(arguments);

        case "filter":
            validateNotBlank(arguments, FILTER_FORMAT);
            return new Command(CommandType.FILTER, arguments);

        case "find":
            validateNotBlank(arguments, FIND_FORMAT);
            return new Command(CommandType.FIND, arguments);

        default:
            throw new InvalidCommandException();
        }
    }

    /**
     * Parses deadline command arguments into a {@link Command}.
     *
     * @param arguments Text after the {@code deadline} command word.
     * @return A {@code DEADLINE} command containing description and deadline string.
     * @throws NexBotException If the arguments are missing or do not match the required format.
     */
    private static Command parseDeadline(String arguments) throws NexBotException {
        validateNotBlank(arguments, DEADLINE_FORMAT);
        String[] parts = arguments.split(BY_DELIMITER, 2);
        if (parts.length < EXPECTED_DEADLINE_PARTS) {
            throw new InvalidFormatException(DEADLINE_FORMAT);
        }
        validateNotBlank(parts, DEADLINE_FORMAT);
        return new Command(CommandType.DEADLINE, parts[0].trim(), parts[1].trim());
    }

    /**
     * Parses event command arguments into a {@link Command}.
     *
     * @param arguments Text after the {@code event} command word.
     * @return An {@code EVENT} command containing description, start time, and end time strings.
     * @throws NexBotException If the arguments are missing or do not match the required format.
     */
    private static Command parseEvent(String arguments) throws NexBotException {
        validateNotBlank(arguments, EVENT_FORMAT);
        String[] parts = arguments.split(FROM_DELIMITER + "|" + TO_DELIMITER, 3);
        if (parts.length < EXPECTED_EVENT_PARTS) {
            throw new InvalidFormatException(EVENT_FORMAT);
        }
        validateNotBlank(parts, EVENT_FORMAT);
        return new Command(CommandType.EVENT, parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    /**
     * Parses delete command arguments into a {@link Command}.
     *
     * @param arguments Text after the {@code delete} command word.
     * @return A {@code DELETE} command containing the task number.
     * @throws NexBotException If the task number is missing or not a valid integer.
     */
    private static Command parseDelete(String arguments) throws NexBotException {
        validateNotBlank(arguments, DELETE_FORMAT);
        validateNumeric(arguments);
        return new Command(CommandType.DELETE, arguments);
    }

    /**
     * Ensures the given string is not blank.
     *
     * @param input  Input to validate.
     * @param format Format hint shown to the user when validation fails.
     * @throws InvalidFormatException If the input is null or blank.
     */
    private static void validateNotBlank(String input, String format)
            throws InvalidFormatException {

        if (input == null || input.trim().isEmpty()) {
            throw new InvalidFormatException(format);
        }
    }

    /**
     * Ensures all strings in the array are not blank.
     *
     * @param inputs Inputs to validate.
     * @param format Format hint shown to the user when validation fails.
     * @throws InvalidFormatException If any input is null or blank.
     */
    private static void validateNotBlank(String[] inputs, String format)
            throws InvalidFormatException {

        for (String input : inputs) {
            if (input == null || input.trim().isEmpty()) {
                throw new InvalidFormatException(format);
            }
        }
    }

    /**
     * Ensures the given string is a valid integer.
     *
     * @param input Input to validate.
     * @throws InvalidTaskNumberException If the input cannot be parsed as an integer.
     */
    private static void validateNumeric(String input) throws InvalidTaskNumberException {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

}