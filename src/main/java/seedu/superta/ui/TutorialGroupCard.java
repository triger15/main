package seedu.superta.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.superta.model.tutorialgroup.TutorialGroup;

// @@author caephler
/**
 * A UI component that displays information of a {@code TutorialGroup}
 */
public class TutorialGroupCard extends UiPart<Region> {
    private static final String FXML = "TutorialGroupCard.fxml";

    public final TutorialGroup tutorialGroup;

    @javafx.fxml.FXML
    private Label name;

    @javafx.fxml.FXML
    private Label id;

    public TutorialGroupCard(TutorialGroup tutorialGroup) {
        super(FXML);
        this.tutorialGroup = tutorialGroup;

        name.setText(String.format("Name: " + tutorialGroup.getName()));
        id.setText(String.format("ID: " + tutorialGroup.getId()));
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
