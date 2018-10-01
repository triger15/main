package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.TutorialGroup.TutorialGroup;

public class CreateTutorialGroupCommand extends Command {
    public static final String COMMAND_WORD = "create-tutorial-group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a tutorial group."
        + "Parameters: "
        + "n/NAME"
        + "id/IDENTIFIER"
        + "Example: " + COMMAND_WORD + " "
        + "n/CS1101S Studio 04A"
        + "id/04a";

    public static final String MESSAGE_SUCCESS = "New tutorial group created: %1$s";

    private final TutorialGroup toAdd;

    public CreateTutorialGroupCommand(TutorialGroup tg) {
        requireNonNull(tg);
        toAdd = tg;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        model.addTutorialGroup(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof CreateTutorialGroupCommand
            && toAdd.equals(((CreateTutorialGroupCommand) other).toAdd));
    }
}
