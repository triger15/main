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

    /**
     * Enumerations for attendance. Allows future extensibility.
     */
    public enum Presence {
        PRESENT,
        ABSENT,
        EXCUSED
    }
}
