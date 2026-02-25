package nexbot.task;

import nexbot.command.Parser;
import nexbot.exception.InvalidFormatException;
import nexbot.util.DateTimeUtil;

import java.time.LocalDateTime;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String taskDescription, String from, String to) throws InvalidFormatException {
        super(taskDescription);
        this.from = DateTimeUtil.parseStrictDateTime(from, Parser.EVENT_FORMAT);
        this.to = DateTimeUtil.parseStrictDateTime(to, Parser.EVENT_FORMAT);
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + getStatusIcon() + " " + getTaskDescription()
                + " (from: " + from.format(DateTimeUtil.OUTPUT_DATE_TIME)
                + " to: " + to.format(DateTimeUtil.OUTPUT_DATE_TIME) + ")";
    }


    @Override
    public String toStorageString() {
        String doneFlag = isDone() ? "1" : "0";
        return "E | " + doneFlag + " | " + getTaskDescription()
                + " | " + from.format(DateTimeUtil.INPUT_DATE_TIME)
                + " | " + to.format(DateTimeUtil.INPUT_DATE_TIME);
    }

}
