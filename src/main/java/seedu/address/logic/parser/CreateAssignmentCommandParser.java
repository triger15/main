package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_MAX_MARKS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_TUTORIAL_GROUP_ID;

import seedu.address.logic.commands.CreateAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Assignment;

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

        int maxMarks = ParserUtil.parseInt(
            argumentMultimap.getValue(PREFIX_ASSIGNMENT_MAX_MARKS).get()
        );

        Assignment assignment = new Assignment(assignmentName, maxMarks);
        return new CreateAssignmentCommand(tgId, assignment);
    }

}
