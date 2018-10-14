package seedu.superta.storage;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

import seedu.superta.model.student.StudentId;

/**
 * An XML Representation of a Grade in a gradebook.
 */
public class XmlGrade {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Grade's %s field is missing!";

    @XmlElement(required = true)
    private String studentId;
    @XmlElement(required = true)
    private Double marks;

    public XmlGrade() { }

    public XmlGrade(String studentId, Double marks) {
        this.studentId = studentId;
        this.marks = marks;
    }

    public XmlGrade(Map.Entry<StudentId, Double> entry) {
        studentId = entry.getKey().toString();
        marks = entry.getValue();
    }

    public String getStudentId() {
        return studentId;
    }

    public Double getMarks() {
        return marks;
    }
}
