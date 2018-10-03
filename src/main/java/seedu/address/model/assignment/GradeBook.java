package seedu.address.model.assignment;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import seedu.address.model.student.StudentId;

/**
 * Model for a grade book.
 */
public class GradeBook {
    private final HashMap<StudentId, Integer> internalHashmap = new HashMap<>();

    /**
     * Adds a grade to the internal hashmap
     * @param stId the student ID.
     * @param grade the grade value
     */
    public void addGrade(StudentId stId, int grade) {
        internalHashmap.put(stId, grade);
    }

    /**
     * Gets a grade for a student
     * @param stId the student ID.
     * @return his grade for this assignment
     */
    public int getGradeFor(StudentId stId) {
        return internalHashmap.get(stId);
    }

    /**
     * Method to streamify this object. Also, orders it in lexicographical order of Student IDs.
     * @return a Stream of entries in lexicographical order.
     */
    public Stream<Map.Entry<StudentId, Integer>> stream() {
        return internalHashmap.entrySet().stream()
            .sorted(Comparator.comparing(o -> o.getKey().toString()));
    }
}
