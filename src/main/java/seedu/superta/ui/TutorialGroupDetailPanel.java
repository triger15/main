package seedu.superta.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.superta.model.student.Student;
import seedu.superta.model.tutorialgroup.TutorialGroup;

// @@author caephler
/**
 * TutorialGroupDetailPanel to display details of tutorial group.
 */
public class TutorialGroupDetailPanel extends UiPart<Region> {
    private static final String FXML = "TutorialGroupDetailPanel.fxml";

    @FXML
    private Label id;

    @FXML
    private Label name;

    @FXML
    private ListView<Student> students;

    private TutorialGroup tutorialGroup;

    public TutorialGroupDetailPanel(TutorialGroup tutorialGroup) {
        super(FXML);
        this.tutorialGroup = tutorialGroup;

        id.setText(tutorialGroup.getId());
        name.setText(tutorialGroup.getName());
        students.setItems(tutorialGroup.getStudents().asUnmodifiableObservableList());
        students.setCellFactory(listView -> new ListCell<Student>(){
            @Override
            protected void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);

                if (empty || student == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(new StudentCard(student, 1).getRoot());
                }
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

