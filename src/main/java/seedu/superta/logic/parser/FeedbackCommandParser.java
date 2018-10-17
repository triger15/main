package seedu.superta.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.superta.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_FEEDBACK;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_GENERAL_STUDENT_ID;
import static seedu.superta.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.superta.logic.commands.FeedbackCommand;
import seedu.superta.logic.parser.exceptions.ParseException;
import seedu.superta.model.student.Feedback;
import seedu.superta.model.student.StudentId;

/**
 * Parser for the feedback command.
 */
public class FeedbackCommandParser implements Parser<FeedbackCommand> {
    @Override
    public FeedbackCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMap = ArgumentTokenizer.tokenize(
            args,
            PREFIX_GENERAL_STUDENT_ID,
            PREFIX_FEEDBACK
        );
        if (!arePrefixesPresent(
            argMap,
            PREFIX_GENERAL_STUDENT_ID,
            PREFIX_FEEDBACK
        ) || !argMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                MESSAGE_INVALID_COMMAND_FORMAT,
                FeedbackCommand.MESSAGE_USAGE));
        }

        StudentId stId = ParserUtil.parseStudentId(
            argMap.getValue(PREFIX_GENERAL_STUDENT_ID).get()
        );

        Feedback feedback = ParserUtil.parseFeedback(
                argMap.getValue(PREFIX_FEEDBACK).get()
        );

        return new FeedbackCommand(stId, feedback);
    }
}
