package seedu.superta.model.tutorialgroup;

import static java.util.Objects.requireNonNull;

import java.util.Optional;
import java.util.stream.Collectors;

import seedu.superta.model.assignment.Assignment;
import seedu.superta.model.assignment.Title;
import seedu.superta.model.assignment.UniqueAssignmentList;
import seedu.superta.model.student.Student;
import seedu.superta.model.student.UniqueStudentList;

/**
 * Model for Tutorial Group.
 */
public class TutorialGroup {
    private final String id;
    private final String name;
    private final UniqueStudentList students;
    private final UniqueAssignmentList assignments;

    public TutorialGroup(String id, String name) {
        requireNonNull(id);
        this.id = id;
        this.name = name;
        this.students = new UniqueStudentList();
        this.assignments = new UniqueAssignmentList();
    }

    public TutorialGroup(TutorialGroup toClone) {
        this.id = toClone.id;
        this.name = toClone.name;
        this.students = toClone.students.clone();
        this.assignments = toClone.assignments.clone();
    }

    public TutorialGroup(String id, String name,
                         UniqueStudentList students,
                         UniqueAssignmentList assignments) {
        this.id = id;
        this.name = name;
        this.students = students;
        this.assignments = assignments;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void addAssignment(Assignment assignment) {
        assignments.add(assignment);
    }

    public void removeAssignment(Assignment assignment) {
        assignments.remove(assignment);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public UniqueStudentList getStudents() {
        return students;
    }

    public UniqueAssignmentList getAssignments() {
        return assignments;
    }

    public Optional<Assignment> getAssignment(Title title) {
        return assignments.asUnmodifiableObservableList()
            .stream()
            .filter(as -> as.getTitle().equals(title))
            .findFirst();
    }

    @Override
    public boolean equals(Object other) {
        return id.equals(((TutorialGroup) other).id);
    }

    @Override
    public String toString() {
        return "[Tutorial Group] ID: " + id + ", Name: " + name + "\n"
            + "Students: "
            + students.asUnmodifiableObservableList()
                .stream()
                .map(st -> st.getStudentId().toString())
                .collect(Collectors.joining(", "))
            + "\n"
            + "Assignments: \n"
            + assignments;
    }
}
