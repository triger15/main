package seedu.superta.storage;

import static junit.framework.TestCase.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.superta.commons.exceptions.IllegalValueException;
import seedu.superta.model.attendance.Session;

public class XmlAdaptedSessionTest {
    private final String valid_session_name = "Lab 1";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void toModelType_success() throws IllegalValueException {
        Session source = new Session(valid_session_name);
        XmlAdaptedSession xmlSession = new XmlAdaptedSession(source);
        assertTrue(xmlSession.toModelType().equals(source));
    }

    @Test
    public void toModelType_failure() throws IllegalValueException {
        XmlAdaptedSession xmlSession = new XmlAdaptedSession();
        thrown.expect(IllegalValueException.class);
        xmlSession.toModelType();
    }
}
