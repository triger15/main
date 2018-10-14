package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEEDBACK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FEEDBACK_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STUDENT_ID_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.FeedbackCommand.MESSAGE_SUCCESS;

import org.junit.Rule;
import org.junit.Test;

import seedu.address.commons.events.ui.ExitAppRequestEvent;
import seedu.address.logic.CommandHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.student.Feedback;
import seedu.address.model.student.StudentId;
import seedu.address.ui.testutil.EventsCollectorRule;

public class FeedbackCommandTest {
    @Rule
    public final EventsCollectorRule eventsCollectorRule = new EventsCollectorRule();

    private Model model = new ModelManager();
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_feedback_success() {
        FeedbackCommand feedbackCommand = new FeedbackCommand(new StudentId(VALID_STUDENT_ID_AMY), new Feedback(VALID_FEEDBACK_AMY));
        String createdFeedback = String.format(MESSAGE_SUCCESS, VALID_FEEDBACK_AMY);
        assertCommandSuccess(feedbackCommand, model, commandHistory, createdFeedback, model);
        //assertTrue(eventsCollectorRule.eventsCollector.getMostRecent() instanceof ExitAppRequestEvent);
        //assertTrue(eventsCollectorRule.eventsCollector.getSize() == 1);
    }

    @Test
    public void equals() {
        StudentId amy = new StudentId(VALID_STUDENT_ID_AMY);
        Feedback amyFeedback = new Feedback(VALID_FEEDBACK_AMY);
        StudentId bob = new StudentId(VALID_STUDENT_ID_BOB);
        Feedback bobFeedback = new Feedback(VALID_FEEDBACK_BOB);

        final FeedbackCommand standardCommand = new FeedbackCommand(amy, amyFeedback);
        // same values -> returns true
        FeedbackCommand commandWithSameValues = new FeedbackCommand(amy, amyFeedback);
        assertTrue(standardCommand.equals(commandWithSameValues));
        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));
        // null -> returns false
        assertFalse(standardCommand.equals(null));
        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));
        // different student -> returns false
        assertFalse(standardCommand.equals(new FeedbackCommand(bob, amyFeedback)));
        // different remark -> returns false
        assertFalse(standardCommand.equals(new FeedbackCommand(amy, bobFeedback)));
    }
}
