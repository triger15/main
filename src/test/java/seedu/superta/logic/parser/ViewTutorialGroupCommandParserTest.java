package seedu.superta.logic.parser;

import static seedu.superta.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_MAX_MARKS;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_NEW_MAX_MARKS;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_TITLE;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_GENERAL_ASSIGNMENT_TITLE;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_GENERAL_TUTORIAL_GROUP_ID;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP_ID;
import static seedu.superta.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.superta.logic.parser.CommandParserTestUtil.assertParseSuccess;

import javax.swing.text.View;

import org.junit.Test;

import seedu.superta.logic.commands.CreateAssignmentCommand;
import seedu.superta.logic.commands.ViewTutorialGroupCommand;
import seedu.superta.model.assignment.Assignment;
import seedu.superta.model.assignment.Title;

public class ViewTutorialGroupCommandParserTest {
    private ViewTutorialGroupCommandParser parser = new ViewTutorialGroupCommandParser();

    @Test
    public void parse_validArg_success(){
        String userInput = " " + PREFIX_TUTORIAL_GROUP_ID + "01a";
        ViewTutorialGroupCommand expectedCommand = new ViewTutorialGroupCommand("01a");

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidArg_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTutorialGroupCommand.MESSAGE_USAGE);

        //invalid tutorial group prefix
        String userInput = " " + PREFIX_GENERAL_TUTORIAL_GROUP_ID + "01a";
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewTutorialGroupCommand.MESSAGE_USAGE);
        String userInput = " ";

        assertParseFailure(parser, userInput, expectedMessage);
    }
}
