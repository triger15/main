package seedu.address.model.assignment;

import seedu.address.model.TutorialGroup.TutorialGroup;

/**
 * Represents an Assignment in the client.
 * Guarantees: immutable.
 */
public class Assignment {

    public final String name;
    public final TutorialGroup tutorialGroup;

    /**
     * Constructs a {@code Assignment}.
     *
     * @param name The name of the assignment.
     *
     */
    public Assignment(String name, TutorialGroup tg) {
        this.name = name;
        this.tutorialGroup = tg;
    }
}
