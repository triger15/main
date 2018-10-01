package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;

public class CreateAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "create-assignment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an assignment."
        + "Parameters: "
        + "n/NAME"
        + "tg/TUTORIAL-GROUP-ID"
        + "Example: " + COMMAND_WORD + " "
        + "n/Take Home Lab 1 "
        + "tg/1";

    public static final String MESSAGE_SUCCESS = "New assignment created: %1$s";

    private final Assignment toAdd;

    public CreateAssignmentCommand(Assignment assignment) {
        requireNonNull(assignment);
        toAdd = assignment;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        return new CommandResult("Hello");

    }
}
