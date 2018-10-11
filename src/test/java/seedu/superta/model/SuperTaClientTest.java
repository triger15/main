package seedu.superta.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.superta.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.superta.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.superta.testutil.TypicalStudents.ALICE;
import static seedu.superta.testutil.TypicalStudents.getTypicalSuperTaClient;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.superta.model.student.Student;
import seedu.superta.model.student.exceptions.DuplicateStudentException;
import seedu.superta.model.tutorialgroup.TutorialGroup;
import seedu.superta.testutil.StudentBuilder;

public class SuperTaClientTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final SuperTaClient superTaClient = new SuperTaClient();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), superTaClient.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        superTaClient.resetData(null);
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        SuperTaClient newData = getTypicalSuperTaClient();
        superTaClient.resetData(newData);
        assertEquals(newData, superTaClient);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        SuperTaClientStub newData = new SuperTaClientStub(newStudents);

        thrown.expect(DuplicateStudentException.class);
        superTaClient.resetData(newData);
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        superTaClient.hasStudent(null);
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(superTaClient.hasStudent(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        superTaClient.addStudent(ALICE);
        assertTrue(superTaClient.hasStudent(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        superTaClient.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(superTaClient.hasStudent(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        superTaClient.getStudentList().remove(0);
    }

    /**
     * A stub ReadOnlySuperTaClient whose students list can violate interface constraints.
     */
    private static class SuperTaClientStub implements ReadOnlySuperTaClient {
        private final ObservableList<Student> students = FXCollections.observableArrayList();

        SuperTaClientStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<TutorialGroup> getTutorialGroupList() {
            return null;
        }
    }

}
