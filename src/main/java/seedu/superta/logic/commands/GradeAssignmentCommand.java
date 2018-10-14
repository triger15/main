package seedu.superta.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.superta.logic.CommandHistory;
import seedu.superta.logic.commands.exceptions.CommandException;
import seedu.superta.model.Model;
import seedu.superta.model.assignment.Grade;
import seedu.superta.model.assignment.exceptions.AssignmentNotFoundException;
import seedu.superta.model.student.exceptions.StudentNotFoundException;
import seedu.superta.model.tutorialgroup.exceptions.TutorialGroupNotFoundException;

/**
 * Command that grades assignments.
 */
public class GradeAssignmentCommand extends Command {
    public static final String COMMAND_WORD = "grade";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Grades an assignment."
        + "Parameters: "
        + "tg/TUTORIAL-GROUP-ID "
        + "as/ASSIGNMENT-NAME "
        + "st/STUDENT-ID "
        + "m/MARKS "
        + "Example: " + COMMAND_WORD + " "
        + "tg/04a "
        + "as/lab1 "
        + "st/A0166733Y"
        + "m/40";

    public static final String MESSAGE_SUCCESS = "Student graded!";

    private final Grade grade;

    public GradeAssignmentCommand(Grade grade) {
        requireNonNull(grade);
        this.grade = grade;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        try {
            model.grade(grade);
        } catch (StudentNotFoundException e) {
            throw new CommandException("Student is not in the specified tutorial group!");
        } catch (TutorialGroupNotFoundException e) {
            throw new CommandException("No such tutorial group.");
        } catch (AssignmentNotFoundException e) {
            throw new CommandException("No such assignment.");
        }

        model.commitSuperTaClient();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
