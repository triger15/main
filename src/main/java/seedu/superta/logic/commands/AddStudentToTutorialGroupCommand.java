package seedu.superta.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.superta.commons.util.CollectionUtil.requireAllNonNull;

import seedu.superta.logic.CommandHistory;
import seedu.superta.logic.commands.exceptions.CommandException;
import seedu.superta.model.Model;
import seedu.superta.model.student.StudentId;
import seedu.superta.model.student.exceptions.DuplicateStudentException;
import seedu.superta.model.student.exceptions.StudentNotFoundException;
import seedu.superta.model.tutorialgroup.exceptions.TutorialGroupNotFoundException;

/**
 * Command that adds student to a tutorial group.
 */
public class AddStudentToTutorialGroupCommand extends Command {
    public static final String COMMAND_WORD = "add-to-tutorial-group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to a tutorial group. "
        + "Parameters: "
        + "tg/TUTORIAL-GROUP-ID "
        + "st/STUDENT-ID"
        + "Example: " + COMMAND_WORD + " "
        + "tg/04a "
        + "st/A0166733Y";

    public static final String MESSAGE_SUCCESS = "Added a student to tutorial group.";

    private final String tgId;
    private final StudentId studentId;

    public AddStudentToTutorialGroupCommand(String tutorialGroupId, StudentId studentId) {
        requireAllNonNull(tutorialGroupId, studentId);
        tgId = tutorialGroupId;
        this.studentId = studentId;
    }


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        try {
            model.addStudentToTutorialGroup(tgId, studentId);
        } catch (TutorialGroupNotFoundException e) {
            throw new CommandException("No such tutorial group.");
        } catch (StudentNotFoundException e) {
            throw new CommandException("No such student.");
        } catch (DuplicateStudentException e) {
            throw new CommandException("Student is already in this tutorial group!");
        }
        model.commitSuperTaClient();
        return new CommandResult(MESSAGE_SUCCESS);

    }
}
