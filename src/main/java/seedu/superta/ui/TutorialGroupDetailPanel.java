package seedu.superta.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.superta.commons.core.LogsCenter;
import seedu.superta.commons.events.ui.AssignmentSelectedEvent;
import seedu.superta.model.assignment.Assignment;
import seedu.superta.model.student.Student;
import seedu.superta.model.tutorialgroup.TutorialGroup;

// @@author Caephler
/**
 * TutorialGroupDetailPanel to display details of tutorial group.
 */
public class TutorialGroupDetailPanel extends UiPart<Region> {
    private static final String FXML = "TutorialGroupDetailPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TutorialGroupDetailPanel.class.getSimpleName());

    @FXML
    private Label id;

    @FXML
    private Label name;

    @FXML
    private ListView<Student> students;

    @FXML
    private ListView<Assignment> assignments;

    @FXML
    private Region root;

    private TutorialGroup tutorialGroup;

    public TutorialGroupDetailPanel(TutorialGroup tutorialGroup) {
        super(FXML);
        this.tutorialGroup = tutorialGroup;

        id.setText(tutorialGroup.getId());
        name.setText(tutorialGroup.getName());
        students.setItems(tutorialGroup.getStudents().asUnmodifiableObservableList());
        students.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);

                if (empty || student == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(new StudentCard(student).getRoot());
                }
            }
        });
        assignments.setItems(tutorialGroup.getAssignments().asUnmodifiableObservableList());
        assignments.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Assignment assignment, boolean empty) {
                super.updateItem(assignment, empty);

                if (empty || assignment == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(new AssignmentCard(tutorialGroup, assignment).getRoot());
                }
            }
        });

        setEventHandlerForSelectionChangeEvent();
    }

    public void setEventHandlerForSelectionChangeEvent() {
        assignments.getSelectionModel().selectedItemProperty()
            .addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    logger.fine("Selection in assignment detailed view changed.");
                    raise(new AssignmentSelectedEvent(tutorialGroup, newValue));
                }
            });
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof TutorialGroupDetailPanel)) {
            return false;
        }

        return tutorialGroup.equals(((TutorialGroupDetailPanel) other).tutorialGroup);
    }



}

