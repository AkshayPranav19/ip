package nexbot.task;

import nexbot.command.Parser;
import nexbot.exception.InvalidFormatException;
import nexbot.util.DateTimeUtil;

import java.time.LocalDateTime;

/**
 * Represents a task with a deadline.
 * <p>
 * A {@code Deadline} task contains a description and a date/time by which
 * the task should be completed.
 */
public class Deadline extends Task {
    private final LocalDateTime by;

    /**
     * Constructs a {@code Deadline} task using the given description and deadline string.
     *
     * @param taskDescription Description of the task.
     * @param deadline        Deadline string in the required format.
     * @throws InvalidFormatException If the deadline string does not match the expected format.
     */

    public Deadline(String taskDescription, String deadline) throws InvalidFormatException {
        super(taskDescription);
        this.by = DateTimeUtil.parseStrictDateTime(deadline, Parser.DEADLINE_FORMAT);
    }

    /**
     * Returns the deadline date/time of this task.
     *
     * @return The {@link LocalDateTime} representing the deadline.
     */

    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns a user-friendly string representation of this deadline task.
     *
     * @return A formatted string including type, status, description, and deadline.
     */

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + getTaskDescription()
                + " (by: " + by.format(DateTimeUtil.OUTPUT_DATE_TIME) + ")";
    }

    /**
     * Returns a string representation of this task suitable for storage.
     *
     * @return A formatted string used for saving this task.
     */

    @Override
    public String toStorageString() {
        String doneFlag = isDone() ? "1" : "0";
        return "D | " + doneFlag + " | " + getTaskDescription() + " | "
                + by.format(DateTimeUtil.INPUT_DATE_TIME);
    }

}
