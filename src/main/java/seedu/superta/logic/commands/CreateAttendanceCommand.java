package seedu.superta.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.superta.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_GENERAL_STUDENT_ID;
import static seedu.superta.logic.parser.CliSyntax.PREFIX_GENERAL_TUTORIAL_GROUP_ID;

import seedu.superta.logic.CommandHistory;
import seedu.superta.logic.commands.exceptions.CommandException;
import seedu.superta.model.Model;
import seedu.superta.model.attendance.Session;
import seedu.superta.model.tutorialgroup.exceptions.TutorialGroupNotFoundException;

/**
 * Command that creates an attendance.
 */
public class CreateAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "create-attendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates an attendance."
        + "Parameters: "
        + PREFIX_GENERAL_TUTORIAL_GROUP_ID + "TUTORIAL-GROUP-ID "
        + "n/NAME "
        + PREFIX_GENERAL_STUDENT_ID + "STUDENT-ID\n"
        + "Example: " + COMMAND_WORD + " "
        + PREFIX_GENERAL_TUTORIAL_GROUP_ID + "1 "
        + "n/W4 Tutorial "
        + PREFIX_GENERAL_STUDENT_ID + "A0123456T" + " ";

    public static final String MESSAGE_SUCCESS = "New attendance created: %1$s";
    public static final String MESSAGE_DUPLICATE_ATTENDANCE = "";//"This assignment already exists in the database";

    private final String tgId;
    private final Session session;

    public CreateAttendanceCommand(String tutorialGroupId, Session session) {
        requireAllNonNull(tutorialGroupId, session);
        this.tgId = tutorialGroupId;
        this.session = session;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        try {
            //model.addAssignment(tgId, toAdd);
        } catch (TutorialGroupNotFoundException e) {
            throw new CommandException("No such tutorial group.");
        }
        model.commitSuperTaClient();
        return new CommandResult(String.format(MESSAGE_SUCCESS, "aaa"));
    }
}
