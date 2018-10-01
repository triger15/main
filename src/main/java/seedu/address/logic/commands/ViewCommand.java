package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.ListIterator;

import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.student.SameStudentIdPredicate;
import seedu.address.model.student.Student;

/**
 * Lists the previous feedback given to the student identified.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_SUCCESS = "Student record displayed.\n";
    public static final String MESSAGE_FAILURE = "Student not found!\n";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Retrieves the feedback given to the student"
            + "indicated by Student ID and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD\n"
            + "Example: " + COMMAND_WORD + " A0123456Z";

    private final SameStudentIdPredicate predicate;

    public ViewCommand(SameStudentIdPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) {
        requireNonNull(model);
        List<Student> students = model.getFilteredPersonList();

        ListIterator<Student> iterator = students.listIterator();
        Student other;
        while (iterator.hasNext()) {
            other = iterator.next();
            if (predicate.test(other)) {
                return new CommandResult(String.format("%s\n %s\n", MESSAGE_SUCCESS, other.getFeedback()));
            }
        }

        return new CommandResult(MESSAGE_FAILURE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ViewCommand) // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate); // state check
    }

}
