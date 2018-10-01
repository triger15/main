package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.assignment.exceptions.AssignmentNotFoundException;

public class AssignmentList implements Iterable<Assignment> {
    private final ObservableList<Assignment> internalList = FXCollections.observableArrayList();

    public void add(Assignment toAdd) {
        requireNonNull(toAdd);
        internalList.add(toAdd);
    }

    public void remove(Assignment toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new AssignmentNotFoundException();
        }
    }

    public AssignmentList clone() {
        AssignmentList other = new AssignmentList();
        other.internalList.addAll(this.internalList);
        return other;
    }

    @Override
    public Iterator<Assignment> iterator() {
        return internalList.iterator();
    }
}
