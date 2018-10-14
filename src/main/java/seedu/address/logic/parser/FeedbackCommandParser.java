package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FEEDBACK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_STUDENT_ID;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.FeedbackCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Feedback;
import seedu.address.model.student.StudentId;

/**
 * Parser for the add-to-tutorial-group command.
 */
public class FeedbackCommandParser implements Parser<FeedbackCommand> {
    @Override
    public FeedbackCommand parse(String args) throws ParseException {
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
