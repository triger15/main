package seedu.address.model.assignment;

import java.util.stream.Collectors;

import seedu.address.model.student.StudentId;

/**
 * Represents an Assignment in the client.
 * Guarantees: immutable.
 */
public class Assignment {
    private final String name;
    private final int maxMarks;
    private final GradeBook gradebook;

    public String getName() {
        return name;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public GradeBook getGradebook() {
        return gradebook;
    }

    /**
     * Constructs a {@code Assignment}.
     *
     * @param name The name of the assignment.
     *
     */
    public Assignment(String name, int maxMarks) {
        this.name = name;
        this.maxMarks = maxMarks;
        this.gradebook = new GradeBook();
    }

    public Assignment(String name, int maxMarks, GradeBook gradebook) {
        this.name = name;
        this.maxMarks = maxMarks;
        this.gradebook = gradebook;
    }

    public Assignment(Assignment toClone) {
        this.name = toClone.name;
        this.maxMarks = toClone.maxMarks;
        gradebook = new GradeBook();
        toClone.gradebook.stream()
            .forEach(e -> gradebook.addGrade(e.getKey(), e.getValue()));
    }

    public void grade(StudentId studentId, int marks) {
        // TODO: Enforce marks < maxMarks, if not throw exception
        gradebook.addGrade(studentId, marks);
    }

    @Override
    public String toString() {
        return "[Assignment]" + name
            + " [Max Marks: " + maxMarks + "]\n"
            + gradebook.stream()
                .map(entry -> entry.getKey().toString() + ": " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }
}
