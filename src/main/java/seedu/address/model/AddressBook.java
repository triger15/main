package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;
import seedu.address.model.TutorialGroup.TutorialGroup;
import seedu.address.model.TutorialGroup.TutorialGroupMaster;
import seedu.address.model.TutorialGroup.exceptions.TutorialGroupNotFoundException;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Grade;
import seedu.address.model.assignment.exceptions.AssignmentNotFoundException;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.student.exceptions.PersonNotFoundException;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniqueStudentList students;
    private final TutorialGroupMaster tutorialGroupMaster;

    /*
     * The 'unusual' code block below is an non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        tutorialGroupMaster = new TutorialGroupMaster();
    }

    public AddressBook() {}

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setPersons(students);
    }

    public void setTutorialGroups(List<TutorialGroup> tutorialGroups) {
        this.tutorialGroupMaster.setTutorialGroups(tutorialGroups);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);

        setStudents(newData.getPersonList());
        setTutorialGroups(newData.getTutorialGroupList());
    }

    //// student-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the address book.
     */
    public boolean hasPerson(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Returns true if a tutorial group with the same id as {@code tutorialGroup} exists in the directory.
     */
    public boolean hasTutorialGroup(String id) {
        return tutorialGroupMaster.contains(id);
    }

    public Optional<TutorialGroup> getTutorialGroup(String id) {
        return tutorialGroupMaster.getTutorialGroup(id);
    }

    public Optional<Student> getStudentWithId(StudentId studentId) {
        return students.getStudentWithId(studentId);
    }

    public void addStudentToTutorialGroup(TutorialGroup tg, Student st) {
        tg.addStudent(st);
    }

    /**
     * Adds a tutorial group to the directory.
     */
    public void addTutorialGroup(TutorialGroup tg) {
        tutorialGroupMaster.addTutorialGroup(tg);
    }

    /**
     * Replaces the given tutorial group {@code target} in the list with {@code edited}.
     * {@code target} must exist in the client.
     */
    public void updateTutorialGroup(TutorialGroup target, TutorialGroup edited) {
        requireNonNull(edited);

        tutorialGroupMaster.setTutorialGroup(target, edited);
    }

    /**
     * Removes a tutorial group from the directory.
     * The tutorial group must exist in the directory.
     */
    public void removeTutorialGroup(TutorialGroup key) {
        tutorialGroupMaster.removeTutorialGroup(key);
    }

    public void addAssignment(TutorialGroup tg, Assignment assignment) {
        requireNonNull(assignment);
        requireNonNull(tg);

        tg.addAssignment(assignment);
    }

    public void grade(Grade grade) {
        Optional<TutorialGroup> otg = tutorialGroupMaster.getTutorialGroup(grade.getTgId());
        if (!otg.isPresent()) {
            throw new TutorialGroupNotFoundException();
        }
        TutorialGroup tg = otg.get();
        Optional<Assignment> oas = tg.getAssignment(grade.getAsId());
        if (!oas.isPresent()) {
            throw new AssignmentNotFoundException();
        }
        Assignment as = oas.get();
        Optional<Student> ost = students.getStudentWithId(grade.getStId());
        if (!ost.isPresent()) {
            throw new PersonNotFoundException();
        }
        Student st = ost.get();
        as.grade(st.getStudentId(), grade.getMarks());
    }

    /**
     * Adds a student to the address book.
     * The student must not already exist in the address book.
     */
    public void addPerson(Student p) {
        students.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the address book.
     * The student identity of {@code editedStudent} must not be the same as another existing student in the address
     * book.
     */
    public void updatePerson(Student target, Student editedStudent) {
        requireNonNull(editedStudent);

        students.setPerson(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Student key) {
        students.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getPersonList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<TutorialGroup> getTutorialGroupList() {
        return tutorialGroupMaster.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                && students.equals(((AddressBook) other).students)
                && tutorialGroupMaster.equals(((AddressBook) other).tutorialGroupMaster));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
