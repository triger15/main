package seedu.superta.logic.commands;

import static java.util.Objects.requireNonNull;

<<<<<<< HEAD:src/main/java/seedu/address/logic/commands/FindCommand.java
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.student.NameContainsKeywordsPredicate;
=======
import seedu.superta.commons.core.Messages;
import seedu.superta.logic.CommandHistory;
import seedu.superta.model.Model;
import seedu.superta.model.student.NameContainsKeywordsPredicate;
>>>>>>> master:src/main/java/seedu/superta/logic/commands/FindCommand.java

/**
 * Finds and lists all persons in the SuperTA client whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final Predicate predicate;

    public FindCommand(Predicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredStudentList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
