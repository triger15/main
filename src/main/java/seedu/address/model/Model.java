package seedu.address.model;

import java.util.Optional;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Grade;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyAddressBook newData);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    boolean hasPerson(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the address book.
     */
    void deletePerson(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the address book.
     */
    void addPerson(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the
     * address book.
     */
    void updatePerson(Student target, Student editedStudent);

    /**
     * Adds the given tutorial group.
     * {@code tutorialGroup} must not already exist.
     */
    void addTutorialGroup(TutorialGroup tutorialGroup);

    /**
     * Adds the student to the tutorial group.
     */
    void addStudentToTutorialGroup(String tgId, StudentId studentId);

    /**
     * Deletes the given tutorial group.
     * The tutorial group must exist in the client.
     */
    void deleteTutorialGroup(TutorialGroup tutorialGroup);

    /**
     * Adds the given assignment to the tutorial group.
     */
    void addAssignment(String tgId, Assignment assignment);

    /**
     * Grades a student on an assignment.
     */
    void grade(Grade grade);

    /**
     * Returns true if a tutorial group with the given id exists.
     * @param id ID of the tutorial group.
     */
    boolean hasTutorialGroup(String id);

    /**
     * Returns the tutorial group with the given id, if present.
     */
    Optional<TutorialGroup> getTutorialGroup(String id);

    /**
     * Replaces the given tutorial group {@code target} with {@code edited}.
     * {@code target} must exist in the client.
     * The ID of {@code edited} must not be the same as another existing tutorial group
     * in the client.
     * @param target The tutorial group to be edited.
     * @param edited The tutorial group with edited details.
     */
    void updateTutorialGroup(TutorialGroup target, TutorialGroup edited);


    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredPersonList();

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Student> predicate);

    /**
     * Returns true if the model has previous address book states to restore.
     */
    boolean canUndoAddressBook();

    /**
     * Returns true if the model has undone address book states to restore.
     */
    boolean canRedoAddressBook();

    /**
     * Restores the model's address book to its previous state.
     */
    void undoAddressBook();

    /**
     * Restores the model's address book to its previously undone state.
     */
    void redoAddressBook();

    /**
     * Saves the current address book state for undo/redo.
     */
    void commitAddressBook();
}
