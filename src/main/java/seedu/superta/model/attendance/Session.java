package seedu.superta.model.attendance;

import static java.util.Objects.requireNonNull;
import static seedu.superta.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import seedu.superta.model.attendance.exceptions.DuplicateAttendanceException;

/**
 * Represents a Session class in the client.
 *
 */
public class Session {
    private final String name;
    private final ObservableSet<Attendance> internalSet = FXCollections.observableSet();

    /**
     * Constructs an {@code Session}.
     *
     * @param name A valid session class name.
     */
    public Session(String name) {
        requireNonNull(name);
        this.name = name;
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
        internalSet.addAll(attendanceList);
    }

    /**
     * Returns true if the list contains an equivalent attendance as the given argument.
     */
    public boolean contains(Attendance toCheck) {
        requireNonNull(toCheck);
        return internalSet.stream().anyMatch(toCheck::isSameAttendance);
    }

    public String getSessionName() {
        return name;
    }

    /**
     * Returns the backing set as an unmodifiable {@code ObservableSet}.
     */
    public ObservableSet<Attendance> asUnmodifiableObservableSet() {
        return FXCollections.unmodifiableObservableSet(internalSet);
    }

    /**
     * Adds the given attendance to the current attendance session.
     * @param attendance A valid attendance.
     */
    public void addToSession(Attendance attendance) {
        requireNonNull(attendance);
        if (contains(attendance)) {
            throw new DuplicateAttendanceException();
        }
        internalSet.add(attendance);
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
                && otherSession.internalSet.equals(internalSet);
    }

    @Override
    public String toString() {
        return "[Session] " + name + ": "
            + internalSet.stream().map(att -> att.toString()).collect(Collectors.joining(", "));
    }
}
