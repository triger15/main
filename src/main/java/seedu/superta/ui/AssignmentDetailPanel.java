package seedu.superta.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.superta.commons.core.LogsCenter;
import seedu.superta.model.assignment.Assignment;
import seedu.superta.model.assignment.GradeEntry;
import seedu.superta.model.tutorialgroup.TutorialGroup;

// @@author Caephler
/**
 * UI Component to display assignment details.
 */
public class AssignmentDetailPanel extends UiPart<Region> {
    private static final String FXML = "AssignmentDetailPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AssignmentDetailPanel.class.getSimpleName());

    @FXML
    private Label tutorialGroupId;

    @FXML
    private Label title;

    @FXML
    private Label maxMarks;

    @FXML
    private Label average;

    @FXML
    private Label median;

    @FXML
    private Label projectedDifficulty;

    @FXML
    private ListView<GradeEntry> grades;

    private TutorialGroup tutorialGroup;
    private Assignment assignment;

    public AssignmentDetailPanel(TutorialGroup tutorialGroup, Assignment assignment) {
        super(FXML);
        this.tutorialGroup = tutorialGroup;
        this.assignment = assignment;

        tutorialGroupId.setText("belongs to: " + tutorialGroup.getId());
        title.setText(assignment.getTitle().assignmentTitle);
        maxMarks.setText("Max marks: " + assignment.getMaxMarks());
        average.setText("Average: " + String.format("%.2f", assignment.getAverage()));
        median.setText("Median: " + String.format("%.2f", assignment.getMedian()));
        projectedDifficulty.setText("Projected Difficulty: "
                                + String.format("%.2f", assignment.getProjectedDifficulty())
                                + " / 10");

        grades.setItems(assignment.getGradebook().asUnmodifiableObservableList());
        grades.setCellFactory(listView -> new ListCell<>() {
            @Override
            public void updateItem(GradeEntry entry, boolean empty) {
                super.updateItem(entry, empty);

                if (empty || entry == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(new GradeEntryCard(entry).getRoot());
                }
            }
        });
    }
}
