package seedu.superta.model.assignment;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import seedu.superta.model.student.Student;
import seedu.superta.model.student.StudentId;

/**
 * Model for a grade book.
 */
public class GradeBook {
    private final HashMap<StudentId, Double> internalHashmap = new HashMap<>();

    /**
     * Adds a grade to the internal hashmap
     * @param stId the student ID.
     * @param grade the grade value
     */
    public void addGrade(StudentId stId, Double grade) {
        internalHashmap.put(stId, grade);
    }

    /**
     * Gets a grade for a student
     * @param stId the student ID.
     * @return his grade for this assignment
     */
    public Double getGradeFor(StudentId stId) {
        return internalHashmap.get(stId);
    }

    /**
     * Checks if a student has a grade in this grade book.
     * @param student the student who we want to check.
     */
    public boolean isStudentIn(Student student) {
        return internalHashmap.containsKey(student.getStudentId());
    }

    /**
     * Removes a student's grade from the grade book, if present.
     * @param student the student who we want its reference removed from.
     */
    public void removeStudentReference(Student student) {
        if (isStudentIn(student)) {
            internalHashmap.remove(student.getStudentId());
        }
    }

    /**
     * Method to streamify this object. Also, orders it in lexicographical order of Student IDs.
     * @return a Stream of entries in lexicographical order.
     */
    public Stream<Map.Entry<StudentId, Double>> stream() {
        return internalHashmap.entrySet().stream()
            .sorted(Comparator.comparing(o -> o.getKey().toString()));
    }
}
