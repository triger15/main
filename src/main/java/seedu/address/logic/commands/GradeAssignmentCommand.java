package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.assignment.Grade;
import seedu.address.model.assignment.exceptions.AssignmentNotFoundException;
import seedu.address.model.student.exceptions.PersonNotFoundException;
import seedu.address.model.tutorialgroup.exceptions.TutorialGroupNotFoundException;

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
        } catch (PersonNotFoundException e) {
            throw new CommandException("Student is not in the specified tutorial group!");
        } catch (TutorialGroupNotFoundException e) {
            throw new CommandException("No such tutorial group.");
        } catch (AssignmentNotFoundException e) {
            throw new CommandException("No such assignment.");
        }

        model.commitAddressBook();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
