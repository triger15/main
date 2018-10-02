package seedu.address.model.assignment;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import seedu.address.model.student.StudentId;

public class GradeBook {
    private final HashMap<StudentId, Integer> internalHashmap = new HashMap<>();

    public void addGrade(StudentId stId, int grade) {
        internalHashmap.put(stId, grade);
    }

    public int getGradeFor(StudentId stId) {
        return internalHashmap.get(stId);
    }

    public Stream<Map.Entry<StudentId, Integer>> stream() {
        return internalHashmap.entrySet().stream()
            .sorted(Comparator.comparing(o -> o.getKey().toString()));
    }
}
