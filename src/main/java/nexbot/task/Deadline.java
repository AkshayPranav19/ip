package nexbot.task;

import nexbot.command.Parser;
import nexbot.exception.InvalidFormatException;
import nexbot.util.DateTimeUtil;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String taskDescription, String deadline) throws InvalidFormatException {
        super(taskDescription);
        this.by = DateTimeUtil.parseStrictDateTime(deadline, Parser.DEADLINE_FORMAT);
    }

    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + getTaskDescription()
                + " (by: " + by.format(DateTimeUtil.OUTPUT_DATE_TIME) + ")";
    }

    @Override
    public String toStorageString() {
        String doneFlag = isDone() ? "1" : "0";
        return "D | " + doneFlag + " | " + getTaskDescription() + " | "
                + by.format(DateTimeUtil.INPUT_DATE_TIME);
    }

}
