package seedu.superta.logic.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import javafx.collections.ObservableList;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static seedu.superta.commons.util.CollectionUtil.requireAllNonNull;
import seedu.superta.logic.CommandHistory;
import static seedu.superta.logic.commands.CommandTestUtil.assertCommandSuccess;
import seedu.superta.logic.commands.exceptions.CommandException;
import seedu.superta.model.Model;
import seedu.superta.model.ModelManager;
import seedu.superta.model.ReadOnlySuperTaClient;
import seedu.superta.model.SuperTaClient;
import seedu.superta.model.UserPrefs;
import seedu.superta.model.assignment.Assignment;
import seedu.superta.model.assignment.Grade;
import seedu.superta.model.student.Feedback;
import seedu.superta.model.student.Student;
import seedu.superta.model.student.StudentId;
import seedu.superta.model.tutorialgroup.TutorialGroup;
import seedu.superta.testutil.StudentBuilder;
import seedu.superta.testutil.TutorialGroupBuilder;
import static seedu.superta.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.superta.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.superta.testutil.TypicalSuperTaClient.ALICE;
import static seedu.superta.testutil.TypicalSuperTaClient.getTypicalSuperTaClient;

public class AddStudentToTutorialGroupCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    private Model model = new ModelManager(getTypicalSuperTaClient(), new UserPrefs());

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullTutorialGroupId_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        new AddStudentToTutorialGroupCommand(null,
                model.getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased()).getStudentId());
    }

    @Test
    public void constructor_nullStudentId_throwsNullPointerException() {
        thrown.expect(NullPointerException.class);
        TutorialGroup tutorialGroup = new TutorialGroupBuilder().build();
        new AddStudentToTutorialGroupCommand(tutorialGroup.getId(), null);
    }

    @Test
    public void execute_studentAcceptedByModel_addToTutorialGroupSuccessful() throws Exception {
        TutorialGroup validTutorialGroup = new TutorialGroupBuilder().build();
        model.addTutorialGroup(validTutorialGroup);
        Student studentToAdd = model.getFilteredStudentList().get(INDEX_SECOND_PERSON.getZeroBased());

        CommandResult commandResult = new AddStudentToTutorialGroupCommand(
            validTutorialGroup.getId(), studentToAdd.getStudentId()).execute(model, commandHistory);

        assertEquals(AddStudentToTutorialGroupCommand.MESSAGE_SUCCESS, commandResult.feedbackToUser);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }





    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void addStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void resetData(ReadOnlySuperTaClient newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlySuperTaClient getSuperTaClient() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStudent(Student student) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteStudent(Student target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateStudent(Student target, Student editedStudent) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTutorialGroup(TutorialGroup tutorialGroup) {

        }

        @Override
        public void addStudentToTutorialGroup(String tgId, StudentId studentId) {

        }

        @Override
        public void removeStudentFromTutorialGroup(String tgId, StudentId studentId) {

        }

        @Override
        public void deleteTutorialGroup(TutorialGroup tutorialGroup) {

        }

        @Override
        public void addAssignment(String tgId, Assignment assignment) {

        }

        @Override
        public void deleteAssignment(String tgId, String assignment) {

        }

        @Override
        public void grade(Grade grade) {

        }

        @Override
        public boolean hasTutorialGroup(String id) {
            return false;
        }

        @Override
        public Optional<TutorialGroup> getTutorialGroup(String id) {
            return Optional.empty();
        }

        @Override
        public void updateTutorialGroup(TutorialGroup edited) {

        }

        @Override
        public void addFeedback(Feedback feedback, StudentId studentId) {

        }

        @Override
        public List<Feedback> viewFeedback(StudentId studentId) {
            return Collections.emptyList();
        }

        @Override
        public ObservableList<Student> getFilteredStudentList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<TutorialGroup> getTutorialGroupList() {
            return null;
        }

        @Override
        public void updateFilteredStudentList(Predicate<Student> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoSuperTaClient() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoSuperTaClient() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoSuperTaClient() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoSuperTaClient() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitSuperTaClient() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single student.
     */
    private class ModelStubWithTutorialGroup extends ModelStub {
        private final TutorialGroup tutorialGroup;

        ModelStubWithTutorialGroup(TutorialGroup tutorialGroup) {
            requireNonNull(tutorialGroup);
            this.tutorialGroup = tutorialGroup;
        }


    }

    /**
     * A Model stub that always accept the student being added.
     */
    private class ModelStubAcceptingStudentIntoTutorialGroup extends ModelStub {
        final ArrayList<Student> studentsAdded = new ArrayList<>();

        @Override
        public void addStudentToTutorialGroup(String tutorialGroupId, StudentId studentId) {
            requireAllNonNull(tutorialGroupId, studentId);

        }

        @Override
        public void commitSuperTaClient() {
            // called by {@code AddCommand#execute()}
        }

        @Override
        public ReadOnlySuperTaClient getSuperTaClient() {
            return new SuperTaClient();
        }
    }
}
