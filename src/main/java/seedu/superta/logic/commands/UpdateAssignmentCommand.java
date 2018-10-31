package seedu.superta.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.superta.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_GENERAL_ASSIGNMENT_TITLE;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_GENERAL_TUTORIAL_GROUP_ID;

import java.util.Optional;

import seedu.superta.logic.CommandHistory;
import seedu.superta.logic.commands.exceptions.CommandException;
import seedu.superta.model.Model;
import seedu.superta.model.assignment.Assignment;
import seedu.superta.model.assignment.Title;
import seedu.superta.model.tutorialgroup.TutorialGroup;

/**
 * Update existing assignment details in the SuperTA client.
 */
public class UpdateAssignmentCommand extends Command {

    public static final String COMMAND_WORD = "update-assignment";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Update assignment details. "
            + "Parameters: "
            + PREFIX_GENERAL_TUTORIAL_GROUP_ID + "TUTORIAL-GROUP-ID "
            + PREFIX_GENERAL_ASSIGNMENT_TITLE + "ASSIGNMENT-TITLE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_GENERAL_TUTORIAL_GROUP_ID + "04a "
            + PREFIX_GENERAL_ASSIGNMENT_TITLE + "Lab 1";

    public static final String MESSAGE_SUCCESS = "Updated tutorial $s assignment: $s";
    public static final String MESSAGE_FAILURE_NO_TUTORIAL_GROUP = "Tutorial group does not exist.";
    public static final String MESSAGE_FAILURE_NO_ASSIGNMENT = "Assignment does not exist.";

    private final String tutorialGroupId;
    private final String assignmentTitle;

    public UpdateAssignmentCommand(String tutorialGroupId, String assignmentTitle) {
        requireAllNonNull(tutorialGroupId, assignmentTitle);
        this.tutorialGroupId = tutorialGroupId;
        this.assignmentTitle = assignmentTitle;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        Optional<TutorialGroup> tutorialGroup = model.getTutorialGroup(tutorialGroupId);
        if (!tutorialGroup.isPresent()) {
            throw new CommandException(MESSAGE_FAILURE_NO_TUTORIAL_GROUP);
        } else {
            Optional<Assignment> assignment = tutorialGroup.get().getAssignment(new Title(assignmentTitle));
            if (!assignment.isPresent()) {
                throw new CommandException(MESSAGE_FAILURE_NO_ASSIGNMENT);
            }
        }

        model.updateAssignment(tutorialGroupId, assignmentTitle);
        model.commitSuperTaClient();
        return new CommandResult(String.format(MESSAGE_SUCCESS, tutorialGroupId, assignmentTitle));
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
                && assignmentTitle.equals(e.assignmentTitle);
    }
}
