package seedu.superta.model.assignment;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static seedu.superta.logic.commands.CommandTestUtil.VALID_MAXMARKS_LAB;
import static seedu.superta.logic.commands.CommandTestUtil.VALID_TITLE_LAB;

import static seedu.superta.testutil.TypicalAssignments.LAB;
import static seedu.superta.testutil.TypicalAssignments.TUT;

import org.junit.Test;

import seedu.superta.testutil.Assert;
import seedu.superta.testutil.AssignmentBuilder;

public class AssignmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Assignment(null, null));
    }

    @Test
    public void isValidMaxMarks() {
        // null max marks
        Assert.assertThrows(NullPointerException.class, () -> Assignment.isValidMaxMarks(null));

        // valid max marks
        org.junit.Assert.assertTrue(Assignment.isValidMaxMarks(0.0)); // numbers only
    }


    @Test
    public void isSameAssignment() {
        // same object -> returns true
        assertTrue(TUT.isSameAssignment(TUT));

        // null -> returns false
        assertFalse(TUT.isSameAssignment(null));

        // different title and max marks -> returns false
        Assignment editedTut = new AssignmentBuilder(TUT).withTitle(VALID_TITLE_LAB)
            .withMaxMarks(VALID_MAXMARKS_LAB).build();
        assertFalse(TUT.isSameAssignment(editedTut));

        // different title -> returns false
        editedTut = new AssignmentBuilder(TUT).withTitle(VALID_TITLE_LAB).build();
        assertFalse(TUT.isSameAssignment(editedTut));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Assignment tutCopy = new AssignmentBuilder(TUT).build();
        assertTrue(TUT.equals(tutCopy));

        // same object -> returns true
        assertTrue(TUT.equals(TUT));

        // null -> returns false
        assertFalse(TUT.equals(null));

        // different type -> returns false
        assertFalse(TUT.equals(5));

        // different assignment -> returns false
        assertFalse(TUT.equals(LAB));

        // different title -> returns false
        Assignment editedAlice = new AssignmentBuilder(TUT).withTitle(VALID_TITLE_LAB).build();
        assertFalse(TUT.equals(editedAlice));

        // different max marks -> returns false
        editedAlice = new AssignmentBuilder(TUT).withMaxMarks(VALID_MAXMARKS_LAB).build();
        assertFalse(TUT.equals(editedAlice));

    }
}
