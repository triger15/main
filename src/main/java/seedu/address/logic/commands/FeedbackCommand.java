package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Feedback;
import seedu.address.model.student.StudentId;

/**
 * Command that adds feedback for a student.
 */
public class FeedbackCommand extends Command {
    public static final String COMMAND_WORD = "feedback";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a feedback to a student. "
        + "Parameters: "
        + "s/STUDENT-ID "
        + "f/FEEDBACK\n"
        + "Example: " + COMMAND_WORD + " "
        + "s/A01234566T "
        + "f/Is generally attentive during class. However, needs to speak up more.";

    public static final String MESSAGE_SUCCESS = "New feedback created: %1$s";

    private final StudentId studentId;
    private final Feedback feedback;

    public FeedbackCommand(StudentId studentId, Feedback feedback) {
        requireAllNonNull(studentId, feedback);
        this.studentId = studentId;
        this.feedback = feedback;
    }


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        model.addFeedback(feedback, studentId);

        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS, feedback));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FeedbackCommand)) {
            return false;
        }

        // state check
        FeedbackCommand e = (FeedbackCommand) other;
        return studentId.equals(e.studentId)
                && feedback.equals(e.feedback);
    }
}
