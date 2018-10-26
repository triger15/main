package seedu.superta.model.assignment;

import static java.util.Objects.requireNonNull;
import static seedu.superta.commons.util.AppUtil.checkArgument;
import static seedu.superta.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.stream.Collectors;

import seedu.superta.model.assignment.exceptions.GradeException;
import seedu.superta.model.student.Student;
import seedu.superta.model.student.StudentId;

/**
 * Represents an Assignment in the client.
 * Guarantees: immutable.
 */
public class Assignment {

    public static final String MESSAGE_MAXMARKS_CONSTRAINTS =
        "Max marks should only contain numbers, and it should not be blank";

    public static final String MESSAGE_MARKS_CONSTRAINTS =
        "Marks should be within max marks, and it should not be blank";

    public static final String MAXMARKS_VALIDATION_REGEX = "[0-9]*[.]?[0-9]*";

    private final Title title;
    private final Double maxMarks;
    private final GradeBook gradebook;

    /**
     * Constructs a {@code Assignment}.
     */
    public Assignment(Title title, Double maxMarks) {
        requireAllNonNull(title, maxMarks);
        checkArgument(isValidMaxMarks(maxMarks), MESSAGE_MAXMARKS_CONSTRAINTS);
        this.title = title;
        this.maxMarks = maxMarks;
        this.gradebook = new GradeBook();
    }

    public Assignment(Title title, Double maxMarks, GradeBook gradebook) {
        requireAllNonNull(title, maxMarks, gradebook);
        checkArgument(isValidMaxMarks(maxMarks), MESSAGE_MAXMARKS_CONSTRAINTS);
        this.title = title;
        this.maxMarks = maxMarks;
        this.gradebook = gradebook;
    }

    public Assignment(Assignment toClone) {
        requireNonNull(toClone);
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
     * Returns true if a given string is a valid number.
     */
    public static boolean isValidMaxMarks(Double test) {
        return test.toString().matches(MAXMARKS_VALIDATION_REGEX);
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
        requireAllNonNull(studentId, marks);

        if (marks > maxMarks) {
            throw new GradeException(MESSAGE_MARKS_CONSTRAINTS);
        }

        gradebook.addGrade(studentId, marks);
    }

    /**
     * Removes a students reference from the grade book, if student is inside.
     */
    public void removeStudentReferences(Student target) {
        gradebook.removeStudentReference(target);
    }


    /**
     * Returns true if both assignments have the same identity and data fields.
     * This defines a stronger notion of equality between two assignments.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Assignment)) {
            return false;
        }

        Assignment otherStudent = (Assignment) other;
        return otherStudent.getTitle().equals(getTitle())
            && otherStudent.getMaxMarks().equals(getMaxMarks())
            && otherStudent.getGradebook().equals(getGradebook());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, maxMarks, gradebook);
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
