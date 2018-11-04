package seedu.superta.model.attendance;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static seedu.superta.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import seedu.superta.model.student.StudentId;
import seedu.superta.testutil.Assert;

// @@author triger15
public class SessionTest {
    private final Session Lab1 = new Session("Lab 1");
    private Session Lab1_attended;
    private final Session Tut1 = new Session("Tut 1");
    private Set<Attendance> attSet;
    private final Attendance attendance = new Attendance(new StudentId(VALID_STUDENT_ID_AMY), Presence.PRESENT);

    @Before
    public void setUp() {
        attSet = new HashSet<>();
        attSet.add(attendance);
        Lab1_attended = new Session("Lab 1", attSet);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Session(null, null));
    }

    @Test
    public void isSameAssignment() {
        // same object -> returns true
        assertTrue(Lab1.isSameSession(Lab1));

        // null -> returns false
        assertFalse(Lab1.isSameSession(null));

        // same name, different attendance list -> returns true
        assertTrue(Lab1.isSameSession(Lab1_attended));

        // different session -> returns false
        assertFalse(Lab1.isSameSession(Tut1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Session sessionCopy = new Session(Lab1.getSessionName());
        assertTrue(Lab1.equals(sessionCopy));

        // same object -> returns true
        assertTrue(Lab1.equals(Lab1));

        // null -> returns false
        assertFalse(Lab1.equals(null));

        // different type -> returns false
        assertFalse(Lab1.equals(5));

        // different name -> returns false
        assertFalse(Lab1.equals(Tut1));

        // different attendance list -> returns false
        assertFalse(Lab1.equals(Lab1_attended));
    }

    @Test
    public void contains() {
        assertTrue(Lab1_attended.contains(attendance));
    }
}
