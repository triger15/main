package seedu.superta.logic.parser;

import static seedu.superta.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_STUDENT_ID;
import static seedu.superta.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.superta.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.Test;

import seedu.superta.logic.commands.ViewStudentFeedbackCommand;
import seedu.superta.model.student.SameStudentIdPredicate;

public class ViewStudentFeedbackCommandParserTest {

    private ViewStudentFeedbackCommandParser parser = new ViewStudentFeedbackCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewStudentFeedbackCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_inValidArg_throwsParseException() {
        //wrong prefix
        assertParseFailure(parser, PREFIX_NAME + "A0123456Z",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewStudentFeedbackCommand.MESSAGE_USAGE));

        //invalid student id
        assertParseFailure(parser, PREFIX_STUDENT_ID + "A01234567",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewStudentFeedbackCommand.MESSAGE_USAGE));
        assertParseFailure(parser, PREFIX_STUDENT_ID + "01234567Z",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewStudentFeedbackCommand.MESSAGE_USAGE));

        //too many arguments
        assertParseFailure(parser, PREFIX_STUDENT_ID + "A0123456Z" + " " + PREFIX_STUDENT_ID + "B0123456Z",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewStudentFeedbackCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsViewCommand() {
        // no leading and trailing whitespaces
        ViewStudentFeedbackCommand expectedViewStudentFeedbackCommand =
                new ViewStudentFeedbackCommand(new SameStudentIdPredicate("A0123456Z"));

        assertParseSuccess(parser, PREFIX_STUDENT_ID + "A0123456Z", expectedViewStudentFeedbackCommand);
    }
}
