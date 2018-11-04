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
    private final Session LAB1 = new Session("Lab 1");
    private Session LAB1_ATTENDED;
    private final Session TUT1 = new Session("Tut 1");
    private Set<Attendance> attSet;
    private final Attendance attendance = new Attendance(new StudentId(VALID_STUDENT_ID_AMY), Presence.PRESENT);

    @Before
    public void setUp() {
        attSet = new HashSet<>();
        attSet.add(attendance);
        LAB1_ATTENDED = new Session("Lab 1", attSet);
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Session(null, null));
    }

    @Test
    public void isSameAssignment() {
        // same object -> returns true
        assertTrue(LAB1.isSameSession(LAB1));

        // null -> returns false
        assertFalse(LAB1.isSameSession(null));

        // same name, different attendance list -> returns true
        assertTrue(LAB1.isSameSession(LAB1_ATTENDED));

        // different session -> returns false
        assertFalse(LAB1.isSameSession(TUT1));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Session sessionCopy = new Session(LAB1.getSessionName());
        assertTrue(LAB1.equals(sessionCopy));

        // same object -> returns true
        assertTrue(LAB1.equals(LAB1));

        // null -> returns false
        assertFalse(LAB1.equals(null));

        // different type -> returns false
        assertFalse(LAB1.equals(5));

        // different name -> returns false
        assertFalse(LAB1.equals(TUT1));

        // different attendance list -> returns false
        assertFalse(LAB1.equals(LAB1_ATTENDED));
    }
}
