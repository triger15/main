package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Command that adds feedback for a student.
 */
public class FeedbackCommand extends Command {
    public static final String COMMAND_WORD = "feedback";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a feedback to a student."
        + "Parameters: "
        + "s/STUDENT-ID"
        + "f/FEEDBACK"
        + "Example: " + COMMAND_WORD + " "
        + "s/A01234566T"
        + "f/Is generally attentive during class. However, needs to speak up more.";

    public static final String MESSAGE_SUCCESS = "New feedback created: ";//%1$s";

    //private final TutorialGroup toAdd;

    public FeedbackCommand() {
        //requireNonNull(tg);
        //toAdd = tg;
    }


    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        //model.addTutorialGroup(toAdd);
        model.commitAddressBook();
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
    /*
    @Override
    public boolean equals(Object other) {
        return other == this
            || (other instanceof FeedbackCommand
            && toAdd.equals(((FeedbackCommand) other).toAdd));
    }*/
}
