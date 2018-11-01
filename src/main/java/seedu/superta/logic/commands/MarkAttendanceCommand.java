package seedu.superta.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.superta.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_GENERAL_STUDENT_ID;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_GENERAL_TUTORIAL_GROUP_ID;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_SESSION_NAME;

import java.util.Set;

import seedu.superta.logic.CommandHistory;
import seedu.superta.logic.commands.exceptions.CommandException;
import seedu.superta.model.Model;
import seedu.superta.model.attendance.Session;
import seedu.superta.model.attendance.exceptions.DuplicateAttendanceException;
import seedu.superta.model.attendance.exceptions.SessionNotFoundException;
import seedu.superta.model.student.StudentId;
import seedu.superta.model.student.exceptions.StudentNotFoundException;
import seedu.superta.model.tutorialgroup.exceptions.TutorialGroupNotFoundException;

/**
 * Command that marks students' attendance.
 */
public class MarkAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "mark-attendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks attendance of students."
        + " Parameters: "
        + PREFIX_GENERAL_TUTORIAL_GROUP_ID + "TUTORIAL-GROUP-ID "
        + PREFIX_SESSION_NAME + "NAME "
        + PREFIX_GENERAL_STUDENT_ID + "STUDENT-ID\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_GENERAL_TUTORIAL_GROUP_ID + "1 "
        + PREFIX_SESSION_NAME + "W4 Tutorial "
        + PREFIX_GENERAL_STUDENT_ID + "A1234567T "
        + PREFIX_GENERAL_STUDENT_ID + "A0123456Y ";

    public static final String MESSAGE_SUCCESS = "Attendance marked: %1$s";
    //public static final String MESSAGE_DUPLICATE_ATTENDANCE =
    //        "This attendance session already exists in the tutorial group.";

    private final String tgId;
    private final Session sessionName;
    private final Set<StudentId> studentIdSet;

    public MarkAttendanceCommand(String tutorialGroupId, Session session, Set<StudentId> stIdSet) {
        requireAllNonNull(tutorialGroupId, session, stIdSet);
        this.tgId = tutorialGroupId;
        this.sessionName = session;
        this.studentIdSet = stIdSet;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        try {
            model.markAttendance(tgId, sessionName, studentIdSet);
        } catch (TutorialGroupNotFoundException e) {
            throw new CommandException("No such tutorial group.");
        } catch (SessionNotFoundException e) {
            throw new CommandException(e.getMessage());
        } catch (StudentNotFoundException e) {
            throw new CommandException("Student not found in tutorial group.");
        } catch (DuplicateAttendanceException e) {
            // Nothing to do here. We allow duplicate marking.
        }
        model.commitSuperTaClient();
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentIdSet));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MarkAttendanceCommand)) {
            return false;
        }

        // state check
        MarkAttendanceCommand e = (MarkAttendanceCommand) other;
        return tgId.equals(e.tgId)
                && sessionName.equals(e.sessionName)
                && studentIdSet.equals(e.studentIdSet);
    }
}
