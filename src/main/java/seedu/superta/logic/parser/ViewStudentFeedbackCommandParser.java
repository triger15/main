package seedu.superta.logic.parser;

import static seedu.superta.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.superta.model.student.StudentId.isValidStudentId;

import seedu.superta.logic.commands.ViewStudentFeedbackCommand;
import seedu.superta.logic.parser.exceptions.ParseException;
import seedu.superta.model.student.StudentId;

/**
 * Parses input arguments and creates a new ViewStudentFeedbackCommand object.
 */
public class ViewStudentFeedbackCommandParser implements Parser<ViewStudentFeedbackCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ViewStudentFeedbackCommand
     * and returns an ViewStudentFeedbackCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewStudentFeedbackCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewStudentFeedbackCommand.MESSAGE_USAGE));
        }

        String[] studentId = trimmedArgs.split("\\s+");
        if (studentId.length != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewStudentFeedbackCommand.MESSAGE_USAGE));
        }

        if (!studentId[0].startsWith(PREFIX_STUDENT_ID.toString())) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewStudentFeedbackCommand.MESSAGE_USAGE));
        }

        String trimmedStudentId = studentId[0].substring(3);
        if (!isValidStudentId(trimmedStudentId)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewStudentFeedbackCommand.MESSAGE_USAGE));
        }

        return new ViewStudentFeedbackCommand(new StudentId(trimmedStudentId));
    }
}
