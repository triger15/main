package seedu.address.storage;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.assignment.Assignment;

public class XmlAdaptedAssignment implements XmlAdapted<Assignment> {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Assignment's %s field is missing!";
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private Integer maxMarks;

    public XmlAdaptedAssignment() { }

    public XmlAdaptedAssignment(String name, int maxMarks) {
        this.name = name;
        this.maxMarks = maxMarks;
    }

    public XmlAdaptedAssignment(Assignment source) {
        name = source.getName();
        maxMarks = source.getMaxMarks();
    }

    @Override
    public Assignment toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        if (maxMarks == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "ID"));
        }

        return new Assignment(name, maxMarks);

    }
}
