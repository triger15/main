package seedu.superta.logic.parser;

import static seedu.superta.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_MAX_MARKS;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_NAME;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_GENERAL_TUTORIAL_GROUP_ID;

import seedu.superta.logic.commands.CreateAssignmentCommand;
import seedu.superta.logic.parser.exceptions.ParseException;
import seedu.superta.model.assignment.Assignment;

/**
 * Parser for the create-assignment command.
 */
public class CreateAssignmentCommandParser implements Parser<CreateAssignmentCommand> {
    @Override
    public CreateAssignmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args,
                                                                       PREFIX_GENERAL_TUTORIAL_GROUP_ID,
                                                                       PREFIX_ASSIGNMENT_NAME,
                                                                       PREFIX_ASSIGNMENT_MAX_MARKS);
        if (!ParserUtil.arePrefixesPresent(argumentMultimap,
                                           PREFIX_GENERAL_TUTORIAL_GROUP_ID,
                                           PREFIX_ASSIGNMENT_NAME,
                                           PREFIX_ASSIGNMENT_MAX_MARKS)
            || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                                   CreateAssignmentCommand.MESSAGE_USAGE));
        }

        String tgId = ParserUtil.parseTutorialGroupId(
            argumentMultimap.getValue(PREFIX_GENERAL_TUTORIAL_GROUP_ID).get()
        );

        String assignmentName = ParserUtil.parseString(
            argumentMultimap.getValue(PREFIX_ASSIGNMENT_NAME).get()
        );

        Double maxMarks = ParserUtil.parseDouble(
            argumentMultimap.getValue(PREFIX_ASSIGNMENT_MAX_MARKS).get()
        );

        Assignment assignment = new Assignment(assignmentName, maxMarks);
        return new CreateAssignmentCommand(tgId, assignment);
    }

}
