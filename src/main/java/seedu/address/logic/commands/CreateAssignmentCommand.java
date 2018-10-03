package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.tutorialgroup.exceptions.TutorialGroupNotFoundException;

/**
 * Command that creates an assignment.
 */
public class CreateAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "create-assignment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an assignment."
        + "Parameters: "
        + "tg/TUTORIAL-GROUP-ID "
        + "n/NAME "
        + "m/MAXMARKS "
        + "Example: " + COMMAND_WORD + " "
        + "tg/04a "
        + "n/Take Home Lab 1 "
        + "m/40";

    public static final String MESSAGE_SUCCESS = "New assignment created: %1$s";

    private final Assignment toAdd;
    private final String tgId;

    public CreateAssignmentCommand(String tutorialGroupId, Assignment assignment) {
        requireNonNull(assignment);
        requireNonNull(tutorialGroupId);
        tgId = tutorialGroupId;
        toAdd = assignment;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        try {
            model.addAssignment(tgId, toAdd);
        } catch (TutorialGroupNotFoundException e) {
            throw new CommandException("No such tutorial group.");
        }
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
