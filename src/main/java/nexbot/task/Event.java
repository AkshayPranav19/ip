package nexbot.task;

import nexbot.command.Parser;
import nexbot.exception.InvalidFormatException;
import nexbot.util.DateTimeUtil;

import java.time.LocalDateTime;

/**
 * Represents a task that occurs over a time interval.
 * <p>
 * An {@code Event} task has a description, a start date/time, and an end date/time.
 */

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an {@code Event} task with the given description and time interval.
     *
     * @param taskDescription Description of the event.
     * @param from            Start date/time string in the required format.
     * @param to              End date/time string in the required format.
     * @throws InvalidFormatException If either date/time does not match the expected format.
     */

    public Event(String taskDescription, String from, String to) throws InvalidFormatException {
        super(taskDescription);
        this.from = DateTimeUtil.parseStrictDateTime(from, Parser.EVENT_FORMAT);
        this.to = DateTimeUtil.parseStrictDateTime(to, Parser.EVENT_FORMAT);
    }

    /**
     * Returns the start date/time of this event.
     *
     * @return The {@link LocalDateTime} representing the start of the event.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end date/time of this event.
     *
     * @return The {@link LocalDateTime} representing the end of the event.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns a user-friendly string representation of this event task.
     *
     * @return A formatted string including type, status, description, start, and end times.
     */
    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + getTaskDescription()
                + " (from: " + from.format(DateTimeUtil.OUTPUT_DATE_TIME)
                + " to: " + to.format(DateTimeUtil.OUTPUT_DATE_TIME) + ")";
    }

    /**
     * Returns a string representation of this task suitable for storage.
     *
     * @return A formatted string used for saving this task.
     */
    @Override
    public String toStorageString() {
        String doneFlag = isDone() ? "1" : "0";
        return "E | " + doneFlag + " | " + getTaskDescription()
                + " | " + from.format(DateTimeUtil.INPUT_DATE_TIME)
                + " | " + to.format(DateTimeUtil.INPUT_DATE_TIME);
    }

}
