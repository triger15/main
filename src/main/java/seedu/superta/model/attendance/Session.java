package seedu.superta.model.attendance;

import static java.util.Objects.requireNonNull;

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
}
