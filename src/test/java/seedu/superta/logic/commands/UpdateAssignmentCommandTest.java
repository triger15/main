package seedu.superta.logic.commands;

import seedu.superta.model.assignment.Assignment;

public class UpdateAssignmentCommandTest {

    private static final String TUTORIAL_GROUP = "04a";
    private static final String ASSIGNMENT_TITLE = "Lab 1";
    private static final Double MAX_MARKS = 40.0;
    private static Assignment assignment;

    /*@Test
    TODO: implement tests on update assignment command
    public void execute_assignmentAndTutorialGroupFound_success() {
        assignment = new AssignmentBuilder()
                .withTitle(ASSIGNMENT_TITLE)
                .withMaxMarks(MAX_MARKS)
                .build();
        TutorialGroup tutorialGroup = new TutorialGroupBuilder()
                .withId(TUTORIAL_GROUP)
                .build();

        Model model = new ModelManager(getTypicalSuperTaClient(), new UserPrefs());
        model.addTutorialGroup(tutorialGroup);
        model.addAssignment(TUTORIAL_GROUP, assignment);
        CommandHistory commandHistory = new CommandHistory();
        UpdateAssignmentCommand command = new UpdateAssignmentCommand(TUTORIAL_GROUP, assignment);

        Assignment assignmentCopy = new AssignmentBuilder()
                .withTitle(ASSIGNMENT_TITLE)
                .build();
        TutorialGroup tutorialGroupCopy = new TutorialGroupBuilder()
                .withId(TUTORIAL_GROUP)
                .build();

        Model expectedModel = new ModelManager(getTypicalSuperTaClient(), new UserPrefs());
        expectedModel.addTutorialGroup(tutorialGroupCopy);
        expectedModel.addAssignment(TUTORIAL_GROUP, assignmentCopy);
        expectedModel.updateAssignment(TUTORIAL_GROUP, assignment);
        expectedModel.commitSuperTaClient();

        String expectedMessage = String.format(MESSAGE_SUCCESS, TUTORIAL_GROUP, ASSIGNMENT_TITLE);

        assertCommandSuccess(command, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_assignmentNotFound_failure() {
        TutorialGroup tutorialGroup = new TutorialGroupBuilder()
                .withId(TUTORIAL_GROUP)
                .build();

        Model model = new ModelManager(getTypicalSuperTaClient(), new UserPrefs());
        model.addTutorialGroup(tutorialGroup);
        CommandHistory commandHistory = new CommandHistory();
        UpdateAssignmentCommand command = new UpdateAssignmentCommand(TUTORIAL_GROUP, "Lab 2");

        assertCommandFailure(command, model, commandHistory, MESSAGE_FAILURE_NO_ASSIGNMENT);
    }

    @Test
    public void execute_tutorialGroupNotFound_failure() {
        Model model = new ModelManager(getTypicalSuperTaClient(), new UserPrefs());
        CommandHistory commandHistory = new CommandHistory();
        UpdateAssignmentCommand command = new UpdateAssignmentCommand("04b", ASSIGNMENT_TITLE);

        assertCommandFailure(command, model, commandHistory, MESSAGE_FAILURE_NO_TUTORIAL_GROUP);
    }


    @Test
    public void equals() {
        UpdateAssignmentCommand firstCommand = new UpdateAssignmentCommand(TUTORIAL_GROUP, assignment);
        UpdateAssignmentCommand secondCommand = new UpdateAssignmentCommand(TUTORIAL_GROUP, "Lab 2");

        //same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        //same String object -> returns true
        UpdateAssignmentCommand firstCommandCopy = new UpdateAssignmentCommand(TUTORIAL_GROUP, ASSIGNMENT_TITLE);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different assignment -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }*/
}
