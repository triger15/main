package seedu.superta.model.assignment;

import java.util.stream.Collectors;

import seedu.superta.model.student.Student;
import seedu.superta.model.student.StudentId;

/**
 * Represents an Assignment in the client.
 * Guarantees: immutable.
 */
public class Assignment {
    private final Title title;
    private final Double maxMarks;
    private final GradeBook gradebook;

    /**
     * Constructs a {@code Assignment}.
     */
    public Assignment(Title title, Double maxMarks) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.gradebook = new GradeBook();
    }

    public Assignment(Title title, Double maxMarks, GradeBook gradebook) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.gradebook = gradebook;
    }

    public Assignment(Assignment toClone) {
        this.title = toClone.title;
        this.maxMarks = toClone.maxMarks;
        gradebook = new GradeBook();
        toClone.gradebook.stream()
            .forEach(e -> gradebook.addGrade(e.getKey(), e.getValue()));
    }


    public Title getTitle() {
        return title;
    }

    public Double getMaxMarks() {
        return maxMarks;
    }

    public GradeBook getGradebook() {
        return gradebook;
    }

    /**
     * Returns true if both assignments of the same title have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two assignments.
     */
    public boolean isSameAssignment(Assignment otherAssignment) {
        if (otherAssignment == this) {
            return true;
        }

        return otherAssignment != null
            && otherAssignment.getTitle().equals(getTitle());
    }

    /**
     * Grades a certain student with specified marks.
     * @param studentId the student id.
     * @param marks the marks given.
     */
    public void grade(StudentId studentId, Double marks) {
        // TODO: Enforce marks < maxMarks, if not throw exception
        gradebook.addGrade(studentId, marks);
    }

    /**
     * Removes a students reference from the grade book, if student is inside.
     */
    public void removeStudentReferences(Student target) {
        gradebook.removeStudentReference(target);
    }

    @Override
    public String toString() {
        return "[Assignment]" + title
            + " [Max Marks: " + maxMarks + "]\n"
            + gradebook.stream()
                .map(entry -> entry.getKey().toString() + ": " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }
}
