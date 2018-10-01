package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIAL_GROUP_ID;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class ViewTutorialGroupCommand extends Command {
    public static final String COMMAND_WORD = "view-tutorial-group";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views information about a specific tutorial group. "
        + "Parameters: "
        + PREFIX_TUTORIAL_GROUP_ID + "TUTORIAL_GROUP_ID"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_TUTORIAL_GROUP_ID + "04a";

    public static final String MESSAGE_SUCCESS = "Displayed the tutorial group: %1$s";
    public static final String MESSAGE_NO_SUCH_TUTORIAL_GROUP = "There is no such tutorial group with this ID.";

    private final String tutorialGroupId;

    public ViewTutorialGroupCommand(String id) {
        tutorialGroupId = id;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        if (!model.hasTutorialGroup(tutorialGroupId)) {
            throw new CommandException(MESSAGE_NO_SUCH_TUTORIAL_GROUP);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getTutorialGroup(tutorialGroupId).get()));
    }
}
