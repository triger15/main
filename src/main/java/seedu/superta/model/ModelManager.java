package seedu.superta.model;

import static java.util.Objects.requireNonNull;
import static seedu.superta.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.superta.commons.core.ComponentManager;
import seedu.superta.commons.core.LogsCenter;
import seedu.superta.commons.events.model.SuperTaClientChangedEvent;
import seedu.superta.model.assignment.Assignment;
import seedu.superta.model.assignment.Grade;
import seedu.superta.model.student.Feedback;
import seedu.superta.model.student.Student;
import seedu.superta.model.student.StudentId;
import seedu.superta.model.student.exceptions.StudentNotFoundException;
import seedu.superta.model.tutorialgroup.TutorialGroup;
import seedu.superta.model.tutorialgroup.exceptions.TutorialGroupNotFoundException;

/**
 * Represents the in-memory model of the SuperTA client data.
 */
public class ModelManager extends ComponentManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedSuperTaClient versionedSuperTaClient;
    private final FilteredList<Student> filteredStudents;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlySuperTaClient addressBook, UserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        versionedSuperTaClient = new VersionedSuperTaClient(addressBook);
        filteredStudents = new FilteredList<>(versionedSuperTaClient.getStudentList());
    }

    public ModelManager() {
        this(new SuperTaClient(), new UserPrefs());
    }

    @Override
    public void resetData(ReadOnlySuperTaClient newData) {
        versionedSuperTaClient.resetData(newData);
        indicateSuperTaClientChanged();
    }

    @Override
    public ReadOnlySuperTaClient getSuperTaClient() {
        return versionedSuperTaClient;
    }

    /** Raises an event to indicate the model has changed */
    private void indicateSuperTaClientChanged() {
        raise(new SuperTaClientChangedEvent(versionedSuperTaClient));
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return versionedSuperTaClient.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        versionedSuperTaClient.removeStudent(target);
        indicateSuperTaClientChanged();
    }

    @Override
    public void addStudent(Student student) {
        versionedSuperTaClient.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        indicateSuperTaClientChanged();
    }

    @Override
    public void updateStudent(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);

        versionedSuperTaClient.updateStudent(target, editedStudent);
        indicateSuperTaClientChanged();
    }

    @Override
    public void addTutorialGroup(TutorialGroup tutorialGroup) {
        versionedSuperTaClient.addTutorialGroup(tutorialGroup);
        indicateSuperTaClientChanged();
    }

    @Override
    public void addStudentToTutorialGroup(String tgId, StudentId studentId) {
        Optional<TutorialGroup> tg = versionedSuperTaClient.getTutorialGroup(tgId);
        Optional<Student> st = versionedSuperTaClient.getStudentWithId(studentId);
        if (!tg.isPresent()) {
            throw new TutorialGroupNotFoundException();
        }
        if (!st.isPresent()) {
            throw new StudentNotFoundException();
        }
        TutorialGroup tutorialGroup = tg.get();
        Student student = st.get();
        versionedSuperTaClient.addStudentToTutorialGroup(tutorialGroup, student);
        indicateSuperTaClientChanged();
    }

    @Override
    public void deleteTutorialGroup(TutorialGroup tutorialGroup) {
        if (!versionedSuperTaClient.hasTutorialGroup(tutorialGroup.getId())) {
            throw new TutorialGroupNotFoundException();
        }
        versionedSuperTaClient.removeTutorialGroup(tutorialGroup);
        indicateSuperTaClientChanged();
    }

    @Override
    public void addAssignment(String tgId, Assignment assignment) {
        Optional<TutorialGroup> tg = versionedSuperTaClient.getTutorialGroup(tgId);
        if (!tg.isPresent()) {
            throw new TutorialGroupNotFoundException();
        }
        TutorialGroup t = tg.get();
        versionedSuperTaClient.addAssignment(t, assignment);
        indicateSuperTaClientChanged();
    }

    @Override
    public void grade(Grade grade) {
        versionedSuperTaClient.grade(grade);
        indicateSuperTaClientChanged();
    }

    @Override
    public void addFeedback(Feedback feedback, StudentId studentId) {
        versionedSuperTaClient.addFeedback(feedback, studentId);
        indicateSuperTaClientChanged();
    }

    @Override
    public boolean hasTutorialGroup(String id) {
        return versionedSuperTaClient.hasTutorialGroup(id);
    }

    @Override
    public Optional<TutorialGroup> getTutorialGroup(String id) {
        return versionedSuperTaClient.getTutorialGroup(id);
    }

    @Override
    public void updateTutorialGroup(TutorialGroup edited) {
        versionedSuperTaClient.updateTutorialGroup(edited);
        indicateSuperTaClientChanged();
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedSuperTaClient}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return FXCollections.unmodifiableObservableList(filteredStudents);
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoSuperTaClient() {
        return versionedSuperTaClient.canUndo();
    }

    @Override
    public boolean canRedoSuperTaClient() {
        return versionedSuperTaClient.canRedo();
    }

    @Override
    public void undoSuperTaClient() {
        versionedSuperTaClient.undo();
        indicateSuperTaClientChanged();
    }

    @Override
    public void redoSuperTaClient() {
        versionedSuperTaClient.redo();
        indicateSuperTaClientChanged();
    }

    @Override
    public void commitSuperTaClient() {
        versionedSuperTaClient.commit();
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
        return versionedSuperTaClient.equals(other.versionedSuperTaClient)
                && filteredStudents.equals(other.filteredStudents);
    }

}
