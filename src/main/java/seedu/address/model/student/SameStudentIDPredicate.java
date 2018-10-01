package seedu.address.model.student;

import java.util.function.Predicate;

public class SameStudentIDPredicate implements Predicate<Student> {
    private final String studentId;
    
    public SameStudentIDPredicate(String studentId) {
        this.studentId = studentId;
    }
    
    @Override
    public boolean test(Student student) {
        return studentId.equalsIgnoreCase(student.getStudentId().toString());
    }
    
    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof SameStudentIDPredicate // instanceof handles nulls
                && studentId.equals(((SameStudentIDPredicate) other).studentId)); // state check
    }
}
