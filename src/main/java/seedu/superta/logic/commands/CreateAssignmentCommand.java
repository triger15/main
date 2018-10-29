package seedu.superta.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.superta.logic.CommandHistory;
import seedu.superta.logic.commands.exceptions.CommandException;
import seedu.superta.model.Model;
import seedu.superta.model.assignment.Assignment;
import seedu.superta.model.assignment.exceptions.DuplicateAssignmentException;
import seedu.superta.model.tutorialgroup.exceptions.TutorialGroupNotFoundException;

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
    public static final String MESSAGE_DUPLICATE_ASSIGNMENT = "This assignment already exists in the database.";

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
        } catch (DuplicateAssignmentException e) {
            throw new CommandException(MESSAGE_DUPLICATE_ASSIGNMENT);
        }
        model.commitSuperTaClient();
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }
}
