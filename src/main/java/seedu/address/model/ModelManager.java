package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.address.commons.core.ComponentManager;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.events.model.AddressBookChangedEvent;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Grade;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.exceptions.PersonNotFoundException;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.model.tutorialgroup.exceptions.TutorialGroupNotFoundException;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedAddressBook versionedAddressBook;
    private final FilteredList<Student> filteredStudents;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyAddressBook addressBook, UserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        versionedAddressBook = new VersionedAddressBook(addressBook);
        filteredStudents = new FilteredList<>(versionedAddressBook.getPersonList());
    }

    public ModelManager() {
        this(new AddressBook(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlyAddressBook newData) {
        versionedAddressBook.resetData(newData);
        indicateAddressBookChanged();
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return versionedAddressBook;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateAddressBookChanged() {
        raise(new AddressBookChangedEvent(versionedAddressBook));
    }

    @Override
    public boolean hasPerson(Student student) {
        requireNonNull(student);

        return versionedAddressBook.hasPerson(student);
    }

    //=========== Student Accessors =================================================================================

    @Override
    public void deleteStudent(Student target) {
        versionedAddressBook.removePerson(target);
        indicateAddressBookChanged();
    }

    @Override
    public void addPerson(Student student) {
        versionedAddressBook.addPerson(student);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        indicateAddressBookChanged();
    }

    @Override
    public void updatePerson(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        versionedAddressBook.updatePerson(target, editedStudent);
        indicateAddressBookChanged();
    }

    //=========== Tutorial Group Accessors ==========================================================================

    @Override
    public void addTutorialGroup(TutorialGroup tutorialGroup) {
        versionedAddressBook.addTutorialGroup(tutorialGroup);
        indicateAddressBookChanged();
    }

    @Override
    public void deleteTutorialGroup(TutorialGroup tutorialGroup) {
        versionedAddressBook.removeTutorialGroup(tutorialGroup);
        indicateAddressBookChanged();
    }

    @Override
    public boolean hasTutorialGroup(String id) {
        return versionedAddressBook.hasTutorialGroup(id);
    }

    @Override
    public Optional<TutorialGroup> getTutorialGroup(String id) {
        return versionedAddressBook.getTutorialGroup(id);
    }

    @Override
    public void updateTutorialGroup(TutorialGroup target, TutorialGroup edited) {

    }

    @Override
    public void addStudentToTutorialGroup(String tutorialGroupId, StudentId studentId) {
        Optional<TutorialGroup> tutorialGroupOptional = versionedAddressBook.getTutorialGroup(tutorialGroupId);
        Optional<Student> studentOptional = versionedAddressBook.getStudentWithId(studentId);
        if (!tutorialGroupOptional.isPresent()) {
            throw new TutorialGroupNotFoundException();
        }
        if (!studentOptional.isPresent()) {
            throw new PersonNotFoundException();
        }
        TutorialGroup tutorialGroup = tutorialGroupOptional.get();
        Student student = studentOptional.get();
        versionedAddressBook.addStudentToTutorialGroup(tutorialGroup, student);
        indicateAddressBookChanged();
    }

    //=========== Assignment Accessors ===================================================================

    @Override
    public void addAssignment(String tutorialGroupId, Assignment assignment) {
        Optional<TutorialGroup> tutorialGroupOptional = versionedAddressBook.getTutorialGroup(tutorialGroupId);
        if (!tutorialGroupOptional.isPresent()) {
            throw new TutorialGroupNotFoundException();
        }
        TutorialGroup tutorialGroup = tutorialGroupOptional.get();
        versionedAddressBook.addAssignment(tutorialGroup, assignment);
        indicateAddressBookChanged();
    }

    //=========== Grade Accessors =========================================================================

    @Override
    public void grade(Grade grade) {
        versionedAddressBook.grade(grade);
        indicateAddressBookChanged();
    }


    //=========== Filtered Student List Accessors ==========================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Student> getFilteredPersonList() {
        return FXCollections.unmodifiableObservableList(filteredStudents);
    }

    @Override
    public void updateFilteredPersonList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoAddressBook() {
        return versionedAddressBook.canUndo();
    }

    @Override
    public boolean canRedoAddressBook() {
        return versionedAddressBook.canRedo();
    }

    @Override
    public void undoAddressBook() {
        versionedAddressBook.undo();
        indicateAddressBookChanged();
    }

    @Override
    public void redoAddressBook() {
        versionedAddressBook.redo();
        indicateAddressBookChanged();
    }

    @Override
    public void commitAddressBook() {
        versionedAddressBook.commit();
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedAddressBook.equals(other.versionedAddressBook)
                && filteredStudents.equals(other.filteredStudents);
    }

}
