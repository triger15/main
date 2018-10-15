package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.ArrayList;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.PhoneContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns an FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                                                                  PREFIX_NAME,
                PREFIX_PHONE,
                                                                  PREFIX_EMAIL,
                                                                  PREFIX_STUDENT_ID);
        ArrayList<String> nameField = new ArrayList<>();
        ArrayList<String> phoneField = new ArrayList<>();

        if (!ParserUtil.arePrefixesPresent(argMultimap,
                                           PREFIX_NAME,
                                           PREFIX_PHONE,
                                           PREFIX_EMAIL,
                                           PREFIX_STUDENT_ID)
            || args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            nameField.add(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()).fullName);
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            phoneField.add(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()).value);
        }

        return new FindCommand(new NameContainsKeywordsPredicate(nameField)
                               .or(new PhoneContainsKeywordsPredicate(phoneField)));
    }

}
