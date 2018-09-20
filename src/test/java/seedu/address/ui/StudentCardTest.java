package seedu.address.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.ui.testutil.GuiTestAssert.assertCardDisplaysPerson;

import org.junit.Test;

import guitests.guihandles.PersonCardHandle;
import seedu.address.model.student.Student;
import seedu.address.testutil.PersonBuilder;

public class StudentCardTest extends GuiUnitTest {

    @Test
    public void display() {
        // no tags
        Student studentWithNoTags = new PersonBuilder().withTags(new String[0]).build();
        PersonCard personCard = new PersonCard(studentWithNoTags, 1);
        uiPartRule.setUiPart(personCard);
        assertCardDisplay(personCard, studentWithNoTags, 1);

        // with tags
        Student studentWithTags = new PersonBuilder().build();
        personCard = new PersonCard(studentWithTags, 2);
        uiPartRule.setUiPart(personCard);
        assertCardDisplay(personCard, studentWithTags, 2);
    }

    @Test
    public void equals() {
        Student student = new PersonBuilder().build();
        PersonCard personCard = new PersonCard(student, 0);

        // same student, same index -> returns true
        PersonCard copy = new PersonCard(student, 0);
        assertTrue(personCard.equals(copy));

        // same object -> returns true
        assertTrue(personCard.equals(personCard));

        // null -> returns false
        assertFalse(personCard.equals(null));

        // different types -> returns false
        assertFalse(personCard.equals(0));

        // different student, same index -> returns false
        Student differentStudent = new PersonBuilder().withName("differentName").build();
        assertFalse(personCard.equals(new PersonCard(differentStudent, 0)));

        // same student, different index -> returns false
        assertFalse(personCard.equals(new PersonCard(student, 1)));
    }

    /**
     * Asserts that {@code personCard} displays the details of {@code expectedStudent} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(PersonCard personCard, Student expectedStudent, int expectedId) {
        guiRobot.pauseForHuman();

        PersonCardHandle personCardHandle = new PersonCardHandle(personCard.getRoot());

        // verify id is displayed correctly
        assertEquals(Integer.toString(expectedId) + ". ", personCardHandle.getId());

        // verify student details are displayed correctly
        assertCardDisplaysPerson(expectedStudent, personCardHandle);
    }
}
