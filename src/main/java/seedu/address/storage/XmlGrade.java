package seedu.address.storage;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.model.student.StudentId;

/**
 * An XML Representation of a Grade in a gradebook.
 */
public class XmlGrade {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Grade's %s field is missing!";

    @XmlElement(required = true)
    private String studentId;
    @XmlElement(required = true)
    private Integer marks;

    public XmlGrade() { }

    public XmlGrade(String studentId, int marks) {
        this.studentId = studentId;
        this.marks = marks;
    }

    public XmlGrade(Map.Entry<StudentId, Integer> entry) {
        studentId = entry.getKey().toString();
        marks = entry.getValue();
    }

    public String getStudentId() {
        return studentId;
    }

    public Integer getMarks() {
        return marks;
    }
}
