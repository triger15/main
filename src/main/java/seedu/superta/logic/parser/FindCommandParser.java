package seedu.superta.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.superta.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_STUDENT_ID;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.superta.logic.commands.FindCommand;
import seedu.superta.logic.parser.exceptions.ParseException;
import seedu.superta.model.student.EmailContainsKeywordsPredicate;
import seedu.superta.model.student.NameContainsKeywordsPredicate;
import seedu.superta.model.student.PhoneContainsKeywordsPredicate;
import seedu.superta.model.student.StudentidContainsKeywordsPredicate;

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
        ArrayList<String> emailField = new ArrayList<>();
        ArrayList<String> studentidField = new ArrayList<>();

        if (args.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] nameKeywords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            nameField.addAll(Arrays.asList(nameKeywords));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String[] phoneKeywords = argMultimap.getValue(PREFIX_PHONE).get().split("\\s+");
            phoneField.addAll(Arrays.asList(phoneKeywords));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String[] emailKeywords = argMultimap.getValue(PREFIX_EMAIL).get().split("\\s+");
            emailField.addAll(Arrays.asList(emailKeywords));
        }
        if (argMultimap.getValue(PREFIX_STUDENT_ID).isPresent()) {
            String[] studentIdKeywords = argMultimap.getValue(PREFIX_STUDENT_ID).get().split("\\s+");
            studentidField.addAll(Arrays.asList(studentIdKeywords));
        }

        return new FindCommand(new NameContainsKeywordsPredicate(nameField)
                               .or(new PhoneContainsKeywordsPredicate(phoneField))
                               .or(new EmailContainsKeywordsPredicate(emailField))
                               .or(new StudentidContainsKeywordsPredicate(studentidField)));
    }

}
