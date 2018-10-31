package seedu.superta.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import seedu.superta.model.student.Feedback;

public class XmlAdaptedFeedbackTest {
    @Test
    public void feedback_equals_itself_success() {
        XmlAdaptedFeedback feedback = new XmlAdaptedFeedback(new Feedback("Hello"));
        assertEquals(feedback, feedback);
    }

    @Test
    public void feedback_not_equals_success() {
        Feedback source = new Feedback("Hello");
        Feedback source_two = new Feedback("Not hello");
        XmlAdaptedFeedback feedback = new XmlAdaptedFeedback(source);
        XmlAdaptedFeedback feedback_two = new XmlAdaptedFeedback(source_two);
        assertNotEquals(feedback, feedback_two);
    }

    @Test
    public void feedback_toModelType_success() {
        Feedback source = new Feedback("Hello");
        XmlAdaptedFeedback feedback = new XmlAdaptedFeedback(source);
        assertEquals(feedback.toModelType(), source);
    }
}
