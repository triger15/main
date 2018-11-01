package seedu.superta.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.superta.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_MAX_MARKS;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_GENERAL_ASSIGNMENT_TITLE;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_GENERAL_TUTORIAL_GROUP_ID;

import seedu.superta.logic.CommandHistory;
import seedu.superta.logic.commands.exceptions.CommandException;
import seedu.superta.model.Model;
import seedu.superta.model.assignment.Assignment;
import seedu.superta.model.assignment.exceptions.DuplicateAssignmentException;
import seedu.superta.model.tutorialgroup.exceptions.TutorialGroupNotFoundException;

/**
 * Update existing assignment details in the SuperTA client.
 */
public class UpdateAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "update-assignment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Update assignment details. "
            + "Parameters: "
            + PREFIX_GENERAL_TUTORIAL_GROUP_ID + "TUTORIAL-GROUP-ID "
            + PREFIX_GENERAL_ASSIGNMENT_TITLE + "OLD-ASSIGNMENT-TITLE"
            + PREFIX_ASSIGNMENT_MAX_MARKS + "OLD-ASSIGNMENT-MAX-MARKS"
            + "[" + PREFIX_GENERAL_ASSIGNMENT_TITLE + "NEW-ASSIGNMENT-TITLE] "
            + "[" + PREFIX_ASSIGNMENT_MAX_MARKS + "NEW-ASSIGNMENT-MAX-MARKS] "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GENERAL_TUTORIAL_GROUP_ID + "04a "
            + PREFIX_GENERAL_ASSIGNMENT_TITLE + "Lab 1"
            + PREFIX_ASSIGNMENT_MAX_MARKS + "40"
            + PREFIX_GENERAL_ASSIGNMENT_TITLE + "Lab 2";

    public static final String MESSAGE_SUCCESS = "Updated tutorial $s assignment maximum marks to: $s";
    public static final String MESSAGE_FAILURE_NO_TUTORIAL_GROUP = "Tutorial group does not exist.";
    public static final String MESSAGE_FAILURE_NO_ASSIGNMENT = "Assignment does not exist.";

    private final String tutorialGroupId;
    private final Assignment toChange;

    public UpdateAssignmentCommand(String tutorialGroupId, Assignment assignment) {
        requireAllNonNull(tutorialGroupId, assignment);
        this.tutorialGroupId = tutorialGroupId;
        this.toChange = assignment;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        try {
            model.updateAssignment(tutorialGroupId, toChange);
        } catch (TutorialGroupNotFoundException e) {
            throw new CommandException(MESSAGE_FAILURE_NO_TUTORIAL_GROUP);
        } catch (DuplicateAssignmentException e) {
            throw new CommandException(MESSAGE_FAILURE_NO_ASSIGNMENT);
        }

        model.commitSuperTaClient();
        return new CommandResult(String.format(MESSAGE_SUCCESS, tutorialGroupId, toChange));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UpdateAssignmentCommand)) {
            return false;
        }

        // state check
        UpdateAssignmentCommand e = (UpdateAssignmentCommand) other;
        return tutorialGroupId.equals(e.tutorialGroupId)
                && toChange.equals(e.toChange);
    }
}
