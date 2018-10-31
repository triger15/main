package seedu.superta.model.attendance;

import seedu.superta.model.student.StudentId;

/**
 * Represents an attendance for a particular student.
 *
 */
public class Attendance {
    private final StudentId stId;
    private Presence presence;

    /**
     * Constructs an {@code Attendance}.
     *
     * @param stId A valid student id.
     * @param presence A valid presence.
     */
    public Attendance(StudentId stId, Presence presence) {
        this.stId = stId;
        this.presence = presence;
    }

    public StudentId getStudentId() {
        return stId;
    }

    public Presence getPresence() {
        return presence;
    }

    /**
     * Returns true if both attendance belong to the same student id.
     * This defines a weaker notion of equality between two attendance.
     */
    public boolean isSameAttendance(Attendance otherAttendance) {
        if (otherAttendance == this) {
            return true;
        }

        return otherAttendance != null
                && otherAttendance.getStudentId().equals(getStudentId());
    }
}
