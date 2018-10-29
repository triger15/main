package seedu.superta.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.superta.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.superta.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.superta.logic.commands.DeleteAssignmentCommand.MESSAGE_FAILURE_ASS;
import static seedu.superta.logic.commands.DeleteAssignmentCommand.MESSAGE_FAILURE_TG;
import static seedu.superta.logic.commands.DeleteAssignmentCommand.MESSAGE_SUCCESS;
import static seedu.superta.testutil.TypicalSuperTaClient.getTypicalSuperTaClient;

import org.junit.Test;

import seedu.superta.logic.CommandHistory;
import seedu.superta.model.Model;
import seedu.superta.model.ModelManager;
import seedu.superta.model.UserPrefs;

public class DeleteAssignmentCommandTest {

    private static final String TUTORIAL_GROUP = "G01";
    private static final String ASS_TO_DELETE = "Lab 1";

    @Test
    public void execute_assignmentAndTutorialGroupFound_success() {
        Model model = new ModelManager(getTypicalSuperTaClient(), new UserPrefs());
        CommandHistory commandHistory = new CommandHistory();
        DeleteAssignmentCommand command = new DeleteAssignmentCommand(ASS_TO_DELETE, TUTORIAL_GROUP);

        Model expectedModel = new ModelManager(getTypicalSuperTaClient(), new UserPrefs());
        expectedModel.deleteAssignment(TUTORIAL_GROUP, ASS_TO_DELETE);
        expectedModel.commitSuperTaClient();

        String expectedMessage = String.format(MESSAGE_SUCCESS, ASS_TO_DELETE, TUTORIAL_GROUP);

        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_assignmentNotFound_throwsCommandException() {
        Model model = new ModelManager(getTypicalSuperTaClient(), new UserPrefs());
        CommandHistory commandHistory = new CommandHistory();
        DeleteAssignmentCommand command = new DeleteAssignmentCommand("Lab 2", TUTORIAL_GROUP);

        assertCommandFailure(command, model, commandHistory, MESSAGE_FAILURE_ASS);
    }

    @Test
    public void execute_tutorialGroupNotFound_throwsCommandException() {
        Model model = new ModelManager(getTypicalSuperTaClient(), new UserPrefs());
        CommandHistory commandHistory = new CommandHistory();
        DeleteAssignmentCommand command = new DeleteAssignmentCommand(ASS_TO_DELETE, "G02");

        assertCommandFailure(command, model, commandHistory, MESSAGE_FAILURE_TG);
    }


    @Test
    public void equals() {
        DeleteAssignmentCommand firstCommand = new DeleteAssignmentCommand(ASS_TO_DELETE, TUTORIAL_GROUP);
        DeleteAssignmentCommand secondCommand = new DeleteAssignmentCommand("T02", TUTORIAL_GROUP);

        //same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        //same String object -> returns true
        DeleteAssignmentCommand firstCommandCopy = new DeleteAssignmentCommand(ASS_TO_DELETE, TUTORIAL_GROUP);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different assignment -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
