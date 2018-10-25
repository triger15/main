package seedu.superta.logic.parser;

import static seedu.superta.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP_ID;
import static seedu.superta.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.List;

import seedu.superta.logic.commands.DeleteTutorialGroupCommand;
import seedu.superta.logic.parser.exceptions.ParseException;

public class DeleteTutorialGroupCommandParser implements Parser<DeleteTutorialGroupCommand> {

    public DeleteTutorialGroupCommand parse(String args) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(
                args,
                CliSyntax.PREFIX_TUTORIAL_GROUP_ID);
        if(!arePrefixesPresent(argumentMultimap,
                PREFIX_TUTORIAL_GROUP_ID
        ) || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteTutorialGroupCommand.MESSAGE_USAGE));
        }

        String tgId = argumentMultimap.getValue(CliSyntax.PREFIX_TUTORIAL_GROUP_ID).get();

        return new DeleteTutorialGroupCommand(tgId);
    }
}
