package nexbot.util;

import nexbot.exception.InvalidFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

    public static final DateTimeFormatter INPUT_DATE_TIME = DateTimeFormatter.ofPattern("dd MM yyyy HHmm");
    public static final DateTimeFormatter INPUT_DATE_ONLY = DateTimeFormatter.ofPattern("dd MM yyyy");
    public static final DateTimeFormatter OUTPUT_DATE_TIME = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");

    public static LocalDateTime parseStrictDateTime(String input, String formatHint) throws InvalidFormatException {
        try {
            return LocalDateTime.parse(input.trim(), INPUT_DATE_TIME);
        } catch (Exception e) {
            throw new InvalidFormatException(formatHint);
        }
    }

    public static LocalDate parseDateOnly(String input, String formatHint) throws InvalidFormatException {
        try {
            return LocalDate.parse(input.trim(), INPUT_DATE_ONLY);
        } catch (Exception e) {
            throw new InvalidFormatException(formatHint);
        }
    }
}