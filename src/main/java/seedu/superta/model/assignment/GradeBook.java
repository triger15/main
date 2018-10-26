package seedu.superta.model.assignment;

import java.util.Comparator;
import java.util.stream.Stream;

import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import seedu.superta.model.student.Student;
import seedu.superta.model.student.StudentId;

/**
 * Model for a grade book.
 */
public class GradeBook {
    private final ObservableMap<StudentId, GradeEntry> internalMap = FXCollections.observableHashMap();

    /**
     * Adds a grade to the internal hashmap
     * @param stId the student ID.
     * @param grade the grade value
     */
    public void addGrade(StudentId stId, Double grade) {
        internalMap.put(stId, new GradeEntry(stId, grade));
    }

    /**
     * Gets a grade for a student
     * @param stId the student ID.
     * @return his grade for this assignment
     */
    public Double getGradeFor(StudentId stId) {
        return internalMap.get(stId).marks;
    }

    /**
     * Checks if a student has a grade in this grade book.
     * @param student the student who we want to check.
     */
    public boolean isStudentIn(Student student) {
        return internalMap.containsKey(student.getStudentId());
    }

    /**
     * Removes a student's grade from the grade book, if present.
     * @param student the student who we want its reference removed from.
     */
    public void removeStudentReference(Student student) {
        if (isStudentIn(student)) {
            internalMap.remove(student.getStudentId());
        }
    }

    /**
     * Method to streamify this object. Also, orders it in lexicographical order of Student IDs.
     * @return a Stream of entries in lexicographical order.
     */
    public Stream<GradeEntry> stream() {
        return internalMap.values().stream()
            .sorted(Comparator.comparing(o -> o.marks));
    }

    /**
     * Returns an unmodifiable view of this gradebook.
     */
    public ObservableList<GradeEntry> asUnmodifiableObservableList() {
        ObservableList<GradeEntry> list = FXCollections.observableArrayList();
        list.addAll(internalMap.values());
        internalMap.addListener((MapChangeListener<? super StudentId, ? super GradeEntry>) change -> {
            if (change.wasAdded()) {
                list.add(change.getValueAdded());
            } else if (change.wasRemoved()) {
                list.remove(change.getValueRemoved());
            }
        });
        return list;
    }
}
