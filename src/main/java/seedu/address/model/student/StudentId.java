package seedu.address.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's Student ID in the SuperTA client.
 * Guarantees: immutable; is valid as declared in {@link #isValidStudentId(String)}
 */
public class StudentId {

    private static final String STUDENT_ID_VALIDATION_REGEX = "[A-Z](\\d){7}[A-Z]";
    public static final String MESSAGE_STUDENT_ID_CONSTRAINTS = "Student IDs should follow the NUS " +
            "Student Card number.";
    public final String studentId;

    /**
     * Constructs a {@code Matriculation Number}.
     *
     * @param studentId A valid matriculation number.
     */
    public StudentId(String studentId) {
        requireNonNull(studentId);
        checkArgument(isValidStudentId(studentId), MESSAGE_STUDENT_ID_CONSTRAINTS);
        this.studentId = studentId;
    }

    /**
     * Returns true if a given string is a valid student ID.
     */
    public static boolean isValidStudentId(String test) {
        return test.matches(STUDENT_ID_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return studentId;
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof StudentId
                && studentId.equals(((StudentId) other).studentId));
    }

    @Override
    public int hashCode() {
        return studentId.hashCode();
    }
}
