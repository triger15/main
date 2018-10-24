package seedu.superta.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.superta.commons.core.LogsCenter;
import seedu.superta.commons.events.ui.TutorialGroupSelectedEvent;
import seedu.superta.model.tutorialgroup.TutorialGroup;

// @@author caephler
/**
 * Panel containing the list of tutorial groups.
 */
public class TutorialGroupListPanel extends UiPart<Region> {
    private static final String FXML = "TutorialGroupListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TutorialGroupListPanel.class);

    @javafx.fxml.FXML
    private ListView<TutorialGroup> tutorialGroupListView;

    public TutorialGroupListPanel(ObservableList<TutorialGroup> tutorialGroups) {
        super(FXML);
        setConnections(tutorialGroups);
        registerAsAnEventHandler(this);
    }

    private void setConnections(ObservableList<TutorialGroup> tutorialGroups) {
        tutorialGroupListView.setItems(tutorialGroups);
        tutorialGroupListView.setCellFactory(listView -> new TutorialGroupListViewCell());
        setEventHandlerForSelectionChangeEvent();
    }

    private void setEventHandlerForSelectionChangeEvent() {
        tutorialGroupListView.getSelectionModel().selectedItemProperty()
            .addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    logger.fine("Selection in tutorial group list changed.");
                    raise(new TutorialGroupSelectedEvent(newValue));
                }
            });
    }

    class TutorialGroupListViewCell extends ListCell<TutorialGroup> {
        @Override
        protected void updateItem(TutorialGroup tutorialGroup, boolean empty) {
            super.updateItem(tutorialGroup, empty);

            if (empty || tutorialGroup == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TutorialGroupCard(tutorialGroup).getRoot());
            }
        }
    }
}
