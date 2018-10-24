package seedu.superta.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.superta.model.tutorialgroup.TutorialGroup;

/**
 * A UI component that displays information of a {@code TutorialGroup}
 */
public class TutorialGroupCard extends UiPart<Region> {
    private static final String FXML = "TutorialGroupCard.fxml";

    public final TutorialGroup tutorialGroup;

    @javafx.fxml.FXML
    private Label name;

    public TutorialGroupCard(TutorialGroup tutorialGroup) {
        super(FXML);
        this.tutorialGroup = tutorialGroup;

        name.setText(tutorialGroup.getName());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TutorialGroupCard)) {
            return false;
        }

        TutorialGroupCard card = (TutorialGroupCard) other;
        return tutorialGroup.equals(card.tutorialGroup);
    }
}
