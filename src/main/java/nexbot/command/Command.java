package nexbot.command;

/**
 * Represents a parsed user command.
 * <p>
 * A {@code Command} contains the command type and up to three optional parameters
 * depending on the command being executed.
 *
 * @param type   The type of command.
 * @param param1 First parameter (meaning depends on command type).
 * @param param2 Second parameter (meaning depends on command type).
 * @param param3 Third parameter (meaning depends on command type).
 */
public record Command(CommandType type, String param1, String param2, String param3) {

    /**
     * Constructs a {@code Command} with two parameters.
     *
     * @param type   The type of command.
     * @param param1 First parameter.
     * @param param2 Second parameter.
     */
    public Command(CommandType type, String param1, String param2) {
        this(type, param1, param2, "");
    }

    /**
     * Constructs a {@code Command} with one parameter.
     *
     * @param type   The type of command.
     * @param param1 First parameter.
     */
    public Command(CommandType type, String param1) {
        this(type, param1, "", "");
    }

    /**
     * Constructs a {@code Command} with no parameters.
     *
     * @param type The type of command.
     */
    public Command(CommandType type) {
        this(type, "", "", "");
    }
}
