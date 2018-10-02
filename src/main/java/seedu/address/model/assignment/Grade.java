package seedu.address.model.assignment;

import seedu.address.model.student.StudentId;

public class Grade {
    private final String tgId;
    private final String asId;
    private final StudentId stId;
    private final Integer marks;

    public Grade(String tgId, String asId, StudentId stId, Integer marks) {
        this.tgId = tgId;
        this.asId = asId;
        this.stId = stId;
        this.marks = marks;
    }

    public String getTgId() {
        return tgId;
    }

    public String getAsId() {
        return asId;
    }

    public StudentId getStId() {
        return stId;
    }

    public Integer getMarks() {
        return marks;
    }
}
