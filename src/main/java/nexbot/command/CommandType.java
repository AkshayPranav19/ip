package nexbot.command;

/**
 * Enumerates all supported user command types.
 * <p>
 * Each value corresponds to a command recognised by the parser and executed by the application.
 */
public enum CommandType {
    BYE,
    LIST,
    MARK,
    UNMARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    FILTER,
    FIND
}
