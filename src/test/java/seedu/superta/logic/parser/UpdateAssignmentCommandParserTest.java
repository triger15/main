package seedu.superta.logic.parser;

import static seedu.superta.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_MAX_MARKS;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_TITLE;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_GENERAL_ASSIGNMENT_TITLE;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_GENERAL_TUTORIAL_GROUP_ID;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP_ID;
import static seedu.superta.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.Test;

import seedu.superta.logic.commands.UpdateAssignmentCommand;

public class UpdateAssignmentCommandParserTest {

    private UpdateAssignmentCommandParser parser = new UpdateAssignmentCommandParser();

    @Test
    public void parse_missingFields_throwsParseException() {
        //empty argument
        assertParseFailure(parser,
                "    ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateAssignmentCommand.MESSAGE_USAGE));

        //tutorial missing
        assertParseFailure(parser,
                " " + PREFIX_GENERAL_ASSIGNMENT_TITLE + "Lab 1 "
                + PREFIX_ASSIGNMENT_MAX_MARKS + "50.0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateAssignmentCommand.MESSAGE_USAGE));

        //assignment title missing
        assertParseFailure(parser,
                " " + PREFIX_GENERAL_TUTORIAL_GROUP_ID + "1 "
                        + PREFIX_ASSIGNMENT_MAX_MARKS + "50.0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateAssignmentCommand.MESSAGE_USAGE));

        //max marks missing
        assertParseFailure(parser,
                " " + PREFIX_GENERAL_ASSIGNMENT_TITLE + "Lab 1 "
                        + PREFIX_GENERAL_TUTORIAL_GROUP_ID + "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateAssignmentCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        //wrong tutorial prefix
        assertParseFailure(parser,
                " " + PREFIX_TUTORIAL_GROUP_ID + "T01 " + PREFIX_GENERAL_ASSIGNMENT_TITLE + "Lab 1 "
                + PREFIX_ASSIGNMENT_MAX_MARKS + "50.0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateAssignmentCommand.MESSAGE_USAGE));

        //wrong assignment title prefix
        assertParseFailure(parser,
                " " + PREFIX_GENERAL_TUTORIAL_GROUP_ID + "T01 " + PREFIX_ASSIGNMENT_TITLE + "Lab 1 "
                + PREFIX_ASSIGNMENT_MAX_MARKS + "50.0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateAssignmentCommand.MESSAGE_USAGE));

        //wrong max mark prefix
        assertParseFailure(parser,
                " " + PREFIX_GENERAL_TUTORIAL_GROUP_ID + "T01 " + PREFIX_GENERAL_ASSIGNMENT_TITLE + "Lab 1"
                + PREFIX_ASSIGNMENT_TITLE + "50.0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UpdateAssignmentCommand.MESSAGE_USAGE));

        //wrong new assignment title prefix

        //wrong new max mark prefix
    }

    /*@Test
    public void parse_validArg_returnUpdateAssignmentCommand() {
        Assignment assignment = new Assignment(new Title (VALID_TITLE_LAB), VALID_MAXMARKS_LAB);
        String tutorialGroup = VALID_TUTORIAL_GROUP_ID;
        UpdateAssignmentCommand expectedUpdateAssignmentCommand =
        new UpdateAssignmentCommand(tutorialGroup, assignment);

        String arguments = " " + PREFIX_GENERAL_TUTORIAL_GROUP_ID + VALID_TITLE_LAB + " "
                + PREFIX_GENERAL_ASSIGNMENT_TITLE + assignment;
        assertParseSuccess(parser , arguments, expectedUpdateAssignmentCommand);

        arguments = " " + PREFIX_GENERAL_ASSIGNMENT_TITLE + assignment + " "
                + PREFIX_GENERAL_TUTORIAL_GROUP_ID + VALID_TITLE_LAB;
        assertParseSuccess(parser , arguments, expectedUpdateAssignmentCommand);
    }*/
}
