package seedu.superta.storage;

import static junit.framework.TestCase.assertTrue;
import static seedu.superta.testutil.TypicalSuperTaClient.BENSON;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.superta.commons.exceptions.IllegalValueException;
import seedu.superta.model.assignment.GradeEntry;

public class XmlGradeTest {
    @Rule
    private ExpectedException thrown;

    @Test
    public void toModelType_success() throws IllegalValueException {
        GradeEntry source = new GradeEntry(BENSON.getStudentId(), 10.0);
        XmlGrade grade = new XmlGrade(source);
        assertTrue(source.marks.equals(grade.getMarks()));
        assertTrue(source.studentId.equals(grade.getStudentId()));
    }
}
