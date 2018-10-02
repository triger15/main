package seedu.address.model.TutorialGroup;

import static java.util.Objects.requireNonNull;

import java.util.stream.Collectors;

import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentList;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;

public class TutorialGroup {
    private final String id;
    private final String name;
    private final UniqueStudentList students;
    private final AssignmentList assignments;

    public TutorialGroup(String id, String name) {
        requireNonNull(id);
        this.id = id;
        this.name = name;
        this.students = new UniqueStudentList();
        this.assignments = new AssignmentList();
    }

    public TutorialGroup(TutorialGroup toClone) {
        this.id = toClone.id;
        this.name = toClone.name;
        this.students = toClone.students.clone();
        this.assignments = toClone.assignments.clone();
    }

    public TutorialGroup(String id, String name,
                         UniqueStudentList students,
                         AssignmentList assignments) {
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

    public AssignmentList getAssignments() {
        return assignments;
    }

    public boolean isSameAs(TutorialGroup tg) {
        return id.equals(tg.id);
    }

    @Override
    public String toString() {
        return "[Tutorial Group] ID: " + id + ", Name: " + name + "\n"
            + "Students: "
            + students.asUnmodifiableObservableList()
                .stream()
                .map(st -> st.getStudentId().toString())
                .collect(Collectors.joining(", "))
            +"\n"
            + "Assignments: "
            + assignments;
    }
}
