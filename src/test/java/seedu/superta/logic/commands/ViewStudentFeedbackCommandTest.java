package seedu.superta.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.superta.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.superta.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.superta.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.superta.testutil.TypicalStudents.getTypicalSuperTaClient;

import org.junit.Test;

import seedu.superta.logic.CommandHistory;
import seedu.superta.model.Model;
import seedu.superta.model.ModelManager;
import seedu.superta.model.UserPrefs;
import seedu.superta.model.student.Student;
import seedu.superta.model.student.StudentId;
import seedu.superta.testutil.StudentBuilder;

public class ViewStudentFeedbackCommandTest {
    private Model model = new ModelManager(getTypicalSuperTaClient(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalSuperTaClient(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    private StudentId amy = new StudentId(VALID_STUDENT_ID_AMY);
    private StudentId bob = new StudentId(VALID_STUDENT_ID_BOB);

    @Test
    public void equals() {

        ViewStudentFeedbackCommand firstViewStudentFeedbackCommand = new ViewStudentFeedbackCommand(amy);
        ViewStudentFeedbackCommand secondViewStudentFeedbackCommand = new ViewStudentFeedbackCommand(bob);

        // same object -> returns true
        assertTrue(firstViewStudentFeedbackCommand.equals(firstViewStudentFeedbackCommand));

        // same values -> returns true
        ViewStudentFeedbackCommand firstViewStudentFeedbackCommandCopy = new ViewStudentFeedbackCommand(amy);
        assertTrue(firstViewStudentFeedbackCommand.equals(firstViewStudentFeedbackCommandCopy));

        // different types -> returns false
        assertFalse(firstViewStudentFeedbackCommand.equals(1));

        // null -> returns false
        assertFalse(firstViewStudentFeedbackCommand.equals(null));

        // different student -> returns false
        assertFalse(firstViewStudentFeedbackCommand.equals(secondViewStudentFeedbackCommand));
    }

    @Test
    public void execute_viewStudentFeedbackCommand_success() {
        Student expectedStudent = new StudentBuilder()
                .withName("Alice Pauline")
                .withEmail("alice@example.com")
                .withPhone("94351253")
                .withStudentId("A0166733Y")
                .withTags("friends").build();
        // TODO:FIX
        assertCommandSuccess(new ViewStudentFeedbackCommand(new StudentId("A0166733Y")),
                model,
                commandHistory,
                String.format("%s\n %s\n", ViewStudentFeedbackCommand.MESSAGE_SUCCESS, expectedStudent.getFeedback()),
                expectedModel);
    }

    @Test
    public void execute_viewStudentFeedbackCommand_failure() {
        assertCommandSuccess(new ViewStudentFeedbackCommand(new StudentId("A0166733Y")),
                model,
                commandHistory,
                ViewStudentFeedbackCommand.MESSAGE_FAILURE,
                expectedModel);
    }
}
