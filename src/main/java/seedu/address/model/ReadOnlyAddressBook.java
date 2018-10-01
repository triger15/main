package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.TutorialGroup.TutorialGroup;
import seedu.address.model.student.Student;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getPersonList();

    /**
     * Returns an unmodifiable view of the tutorial group list.
     */
    ObservableList<TutorialGroup> getTutorialGroupList();
}
