package seedu.address.model.TutorialGroup;

import static java.util.Objects.requireNonNull;

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

    public TutorialGroup addStudent(Student student) {
        TutorialGroup result = new TutorialGroup(this);
        result.students.add(student);
        return result;
    }

    public TutorialGroup removeStudent(Student student) {
        TutorialGroup result = new TutorialGroup(this);
        result.students.remove(student);
        return result;
    }

    public TutorialGroup addAssignment(Assignment assignment) {
        TutorialGroup result = new TutorialGroup(this);
        result.assignments.add(assignment);
        return result;
    }

    public TutorialGroup removeAssignment(Assignment assignment) {
        TutorialGroup result = new TutorialGroup(this);
        result.assignments.remove(assignment);
        return result;
    }

    public TutorialGroup setName(String name) {
        return new TutorialGroup(id, name,
             this.students.clone(), this.assignments.clone());
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
        return "[Tutorial Group] ID: " + id + ", Name: " + name;
    }
}
