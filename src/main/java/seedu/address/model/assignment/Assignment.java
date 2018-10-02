package seedu.address.model.assignment;

/**
 * Represents an Assignment in the client.
 * Guarantees: immutable.
 */
public class Assignment {

    public String getName() {
        return name;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    private final String name;
    private final int maxMarks;

    /**
     * Constructs a {@code Assignment}.
     *
     * @param name The name of the assignment.
     *
     */
    public Assignment(String name, int maxMarks) {
        this.name = name;
        this.maxMarks = maxMarks;
    }

    @Override
    public String toString() {
        return "[Assignment]" + name + " [Max Marks: " + maxMarks
            + "]";
    }
}
