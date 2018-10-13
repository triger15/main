package seedu.superta.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.superta.commons.exceptions.IllegalValueException;
import seedu.superta.model.assignment.Assignment;

/**
 * Class for XML-Adapted Assignment
 */
public class XmlAdaptedAssignment implements XmlAdapted<Assignment> {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assignment's %s field is missing!";
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private Double maxMarks;
    @XmlElement
    private XmlAdaptedGradeBook gradebook;

    public XmlAdaptedAssignment() { }

    public XmlAdaptedAssignment(String name, Double maxMarks, XmlAdaptedGradeBook gradebook) {
        this.name = name;
        this.maxMarks = maxMarks;
        this.gradebook = gradebook;
    }

    public XmlAdaptedAssignment(Assignment source) {
        name = source.getName();
        maxMarks = source.getMaxMarks();
        gradebook = new XmlAdaptedGradeBook(source.getGradebook());
    }

    @Override
    public Assignment toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        if (maxMarks == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "ID"));
        }

        if (gradebook == null) {
            return new Assignment(name, maxMarks);
        }
        return new Assignment(name, maxMarks, gradebook.toModelType());
    }
}
