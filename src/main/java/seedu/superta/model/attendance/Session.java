package seedu.superta.model.attendance;

import static java.util.Objects.requireNonNull;
import static seedu.superta.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Session class in the client.
 *
 */
public class Session {
    private final String name;
    private final Set<Attendance> attendanceList;

    /**
     * Constructs an {@code Session}.
     *
     * @param name A valid session class name.
     */
    public Session(String name) {
        requireNonNull(name);
        this.name = name;
        this.attendanceList = new HashSet<>();
    }

    /**
     * Constructs an {@code Session}.
     *
     * @param name A valid session class name.
     * @param attendanceList A valid attendance list.
     */
    public Session(String name, Set<Attendance> attendanceList) {
        requireAllNonNull(name, attendanceList);
        this.name = name;
        this.attendanceList = attendanceList;
    }

    public String getSessionName() {
        return name;
    }

    public Set<Attendance> getAttendanceList() {
        return attendanceList;
    }

    /**
     * Returns true if both sessions of the same title have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two sessions.
     */
    public boolean isSameSession(Session otherSession) {
        if (otherSession == this) {
            return true;
        }

        return otherSession != null
                && otherSession.getSessionName().equals(getSessionName());
    }

    /**
     * Returns true if both sessions have the same identity and attendance list.
     * This defines a stronger notion of equality between two sessions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Session)) {
            return false;
        }

        Session otherSession = (Session) other;
        return otherSession.getSessionName().equals(getSessionName())
                && otherSession.getAttendanceList().equals(getAttendanceList());
    }
}
