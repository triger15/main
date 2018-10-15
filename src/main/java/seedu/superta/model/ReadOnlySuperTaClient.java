package seedu.superta.model;

import javafx.collections.ObservableList;
import seedu.superta.model.student.Student;
import seedu.superta.model.tutorialgroup.TutorialGroup;

/**
 * Unmodifiable view of a SuperTA client.
 */
public interface ReadOnlySuperTaClient {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    /**
     * Returns an unmodifiable view of the tutorial group list.
     */
    ObservableList<TutorialGroup> getTutorialGroupList();
}
