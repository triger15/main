package systemtests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static seedu.superta.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.superta.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.superta.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.superta.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.superta.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.superta.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.superta.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.superta.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.superta.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.superta.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.superta.logic.commands.CommandTestUtil.STUDENT_ID_DESC_AMY;
import static seedu.superta.logic.commands.CommandTestUtil.STUDENT_ID_DESC_BOB;
import static seedu.superta.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.superta.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.superta.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.superta.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.superta.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.superta.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.superta.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.superta.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.superta.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.superta.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.superta.testutil.TypicalSuperTaClient.AMY;
import static seedu.superta.testutil.TypicalSuperTaClient.BOB;
import static seedu.superta.testutil.TypicalSuperTaClient.KEYWORD_MATCHING_MEIER;

import org.junit.Test;

import seedu.superta.commons.core.Messages;
import seedu.superta.commons.core.index.Index;
import seedu.superta.logic.commands.EditCommand;
import seedu.superta.logic.commands.RedoCommand;
import seedu.superta.logic.commands.UndoCommand;
import seedu.superta.model.Model;
import seedu.superta.model.student.Email;
import seedu.superta.model.student.Name;
import seedu.superta.model.student.Phone;
import seedu.superta.model.student.Student;
import seedu.superta.model.tag.Tag;
import seedu.superta.testutil.StudentBuilder;
import seedu.superta.testutil.StudentUtil;

public class EditCommandSystemTest extends SuperTaClientSystemTest {

    @Test
    public void edit() {
        Model model = getModel();

        /* ----------------- Performing edit operation while an unfiltered list is being shown ---------------------- */

        /* Case: edit all fields, command with leading spaces, trailing spaces and multiple spaces between each field
         * -> edited
         */
        Index index = INDEX_FIRST_PERSON;
        String command = " " + EditCommand.COMMAND_WORD + "  " + index.getOneBased() + "  " + NAME_DESC_BOB + "  "
            + PHONE_DESC_BOB + " " + EMAIL_DESC_BOB + "  "
            + STUDENT_ID_DESC_BOB + " " + TAG_DESC_HUSBAND + " ";
        Student editedStudent = new StudentBuilder(BOB).withTags(VALID_TAG_HUSBAND).build();
        assertCommandSuccess(command, index, editedStudent);

        /* Case: undo editing the last student in the list -> last student restored */
        command = UndoCommand.COMMAND_WORD;
        String expectedResultMessage = UndoCommand.MESSAGE_SUCCESS;
        assertCommandSuccess(command, model, expectedResultMessage);

        /* Case: redo editing the last student in the list -> last student edited again */
        command = RedoCommand.COMMAND_WORD;
        expectedResultMessage = RedoCommand.MESSAGE_SUCCESS;
        model.updateStudent(
            getModel().getFilteredStudentList().get(INDEX_FIRST_PERSON.getZeroBased()), editedStudent);
        assertCommandSuccess(command, model, expectedResultMessage);

        /* Case: edit a student with new values same as existing values -> edited */
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + STUDENT_ID_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND;
        assertCommandSuccess(command, index, BOB);

        /* Case: edit a student with new values same as another student's values but with different name and
          student ID -> edited */
        assertTrue(getModel().getSuperTaClient().getStudentList().contains(BOB));
        index = INDEX_SECOND_PERSON;
        assertNotEquals(getModel().getFilteredStudentList().get(index.getZeroBased()), BOB);
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_AMY + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + STUDENT_ID_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND;
        editedStudent = new StudentBuilder(BOB).withName(VALID_NAME_AMY).withStudentId(BOB.getStudentId().studentId)
                .build();

        /* Case: edit a student with new values same as another student's values but with different phone and email
         * -> edited
         */
        index = INDEX_SECOND_PERSON;
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_BOB + PHONE_DESC_AMY + EMAIL_DESC_AMY
            + STUDENT_ID_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND;
        editedStudent = new StudentBuilder(BOB).withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).build();

        /* Case: clear tags -> cleared */
        index = INDEX_FIRST_PERSON;
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + " " + PREFIX_TAG.getPrefix();
        Student studentToEdit = getModel().getFilteredStudentList().get(index.getZeroBased());
        editedStudent = new StudentBuilder(studentToEdit).withTags().build();

        /* ------------------ Performing edit operation while a filtered list is being shown ------------------------ */

        /* Case: filtered student list, edit index within bounds of address book and student list -> edited */
        showPersonsWithName(KEYWORD_MATCHING_MEIER);
        index = INDEX_FIRST_PERSON;
        assertTrue(index.getZeroBased() < getModel().getFilteredStudentList().size());
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + " " + NAME_DESC_BOB;
        studentToEdit = getModel().getFilteredStudentList().get(index.getZeroBased());
        editedStudent = new StudentBuilder(studentToEdit).withName(VALID_NAME_BOB).build();

        /* Case: filtered student list, edit index within bounds of address book but out of bounds of student list
         * -> rejected
         */
        showPersonsWithName(KEYWORD_MATCHING_MEIER);
        int invalidIndex = getModel().getSuperTaClient().getStudentList().size();
        assertCommandFailure(EditCommand.COMMAND_WORD + " " + invalidIndex + NAME_DESC_BOB,
                             Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        /* ------------------- Performing edit operation while a student card is selected -------------------------- */

        /* Case: selects first card in the student list, edit a student -> edited, card selection remains unchanged but
         * browser url changes
         */
        showAllPersons();
        index = INDEX_FIRST_PERSON;
        selectPerson(index);
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
            + STUDENT_ID_DESC_AMY + TAG_DESC_FRIEND;
        // this can be misleading: card selection actually remains unchanged but the
        // browser's url is updated to reflect the new student's name
        assertCommandSuccess(command, index, AMY, index);

        /* ------------------------------- Performing invalid edit operation -------------------------------------- */

        /* Case: invalid index (0) -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + " 0" + NAME_DESC_BOB,
                             String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));

        /* Case: invalid index (-1) -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + " -1" + NAME_DESC_BOB,
                             String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));

        /* Case: invalid index (size + 1) -> rejected */
        invalidIndex = getModel().getFilteredStudentList().size() + 1;
        assertCommandFailure(EditCommand.COMMAND_WORD + " " + invalidIndex + NAME_DESC_BOB,
                             Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        /* Case: missing index -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + NAME_DESC_BOB,
                             String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE));

        /* Case: missing all fields -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased(),
                             EditCommand.MESSAGE_NOT_EDITED);

        /* Case: invalid name -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + INVALID_NAME_DESC,
                             Name.MESSAGE_NAME_CONSTRAINTS);

        /* Case: invalid phone -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + INVALID_PHONE_DESC,
                             Phone.MESSAGE_PHONE_CONSTRAINTS);

        /* Case: invalid email -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + INVALID_EMAIL_DESC,
                             Email.MESSAGE_EMAIL_CONSTRAINTS);

        /* Case: invalid tag -> rejected */
        assertCommandFailure(EditCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased() + INVALID_TAG_DESC,
                             Tag.MESSAGE_TAG_CONSTRAINTS);

        /* Case: edit a student with new values same as another student's values -> rejected */
        executeCommand(StudentUtil.getAddCommand(BOB));
        assertTrue(getModel().getSuperTaClient().getStudentList().contains(BOB));
        index = INDEX_FIRST_PERSON;
        assertFalse(getModel().getFilteredStudentList().get(index.getZeroBased()).equals(BOB));
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + STUDENT_ID_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND;
        assertCommandFailure(command, EditCommand.MESSAGE_DUPLICATE_PERSON);

        /* Case: edit a student with new values same as another student's values but with different tags -> rejected */
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
            + STUDENT_ID_DESC_BOB + TAG_DESC_HUSBAND;
        assertCommandFailure(command, EditCommand.MESSAGE_DUPLICATE_PERSON);

        /* Case: edit a student with new values same as another student's values but with different phone -> rejected */
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_BOB + PHONE_DESC_AMY + EMAIL_DESC_BOB
            + STUDENT_ID_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND;
        assertCommandFailure(command, EditCommand.MESSAGE_DUPLICATE_PERSON);

        /* Case: edit a student with new values same as another student's values but with different email -> rejected */
        command = EditCommand.COMMAND_WORD + " " + index.getOneBased() + NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_AMY
            + STUDENT_ID_DESC_BOB + TAG_DESC_FRIEND + TAG_DESC_HUSBAND;
        assertCommandFailure(command, EditCommand.MESSAGE_DUPLICATE_PERSON);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Index, Student, Index)} except that
     * the browser url and selected card remain unchanged.
     *
     * @param toEdit the index of the current model's filtered list
     * @see EditCommandSystemTest#assertCommandSuccess(String, Index, Student, Index)
     */
    private void assertCommandSuccess(String command, Index toEdit, Student editedStudent) {
        assertCommandSuccess(command, toEdit, editedStudent, null);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Model, String, Index)} and in addition,<br>
     * 1. Asserts that result display box displays the success message of executing {@code EditCommand}.<br>
     * 2. Asserts that the model related components are updated to reflect the student at index {@code toEdit} being
     * updated to values specified {@code editedStudent}.<br>
     *
     * @param toEdit the index of the current model's filtered list.
     * @see EditCommandSystemTest#assertCommandSuccess(String, Model, String, Index)
     */
    private void assertCommandSuccess(String command, Index toEdit, Student editedStudent,
                                      Index expectedSelectedCardIndex) {
        Model expectedModel = getModel();
        expectedModel.updateStudent(expectedModel.getFilteredStudentList().get(toEdit.getZeroBased()), editedStudent);
        expectedModel.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);

        assertCommandSuccess(command, expectedModel,
                             String.format(EditCommand.MESSAGE_EDIT_PERSON_SUCCESS, editedStudent),
                             expectedSelectedCardIndex);
    }

    /**
     * Performs the same verification as {@code assertCommandSuccess(String, Model, String, Index)} except that the
     * browser url and selected card remain unchanged.
     *
     * @see EditCommandSystemTest#assertCommandSuccess(String, Model, String, Index)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage) {
        assertCommandSuccess(command, expectedModel, expectedResultMessage, null);
    }

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays an empty string.<br>
     * 2. Asserts that the result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the browser url and selected card update accordingly depending on the card at
     * {@code expectedSelectedCardIndex}.<br>
     * 4. Asserts that the status bar's sync status changes.<br>
     * 5. Asserts that the command box has the default style class.<br>
     * Verifications 1 and 2 are performed by
     * {@code SuperTaClientSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     *
     * @see SuperTaClientSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     * @see SuperTaClientSystemTest#assertSelectedCardChanged(Index)
     */
    private void assertCommandSuccess(String command, Model expectedModel, String expectedResultMessage,
                                      Index expectedSelectedCardIndex) {
        executeCommand(command);
        expectedModel.updateFilteredStudentList(PREDICATE_SHOW_ALL_PERSONS);
        assertApplicationDisplaysExpected("", expectedResultMessage, expectedModel);
        assertCommandBoxShowsDefaultStyle();
        if (expectedSelectedCardIndex != null) {
            // pass
        } else {
            assertSelectedCardUnchanged();
        }
        assertStatusBarUnchangedExceptSyncStatus();
    }

    /**
     * Executes {@code command} and in addition,<br>
     * 1. Asserts that the command box displays {@code command}.<br>
     * 2. Asserts that result display box displays {@code expectedResultMessage}.<br>
     * 3. Asserts that the browser url, selected card and status bar remain unchanged.<br>
     * 4. Asserts that the command box has the error style.<br>
     * Verifications 1 and 2 are performed by
     * {@code SuperTaClientSystemTest#assertApplicationDisplaysExpected(String, String, Model)}.<br>
     *
     * @see SuperTaClientSystemTest#assertApplicationDisplaysExpected(String, String, Model)
     */
    private void assertCommandFailure(String command, String expectedResultMessage) {
        Model expectedModel = getModel();

        executeCommand(command);
        assertApplicationDisplaysExpected(command, expectedResultMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsErrorStyle();
        assertStatusBarUnchanged();
    }
}
