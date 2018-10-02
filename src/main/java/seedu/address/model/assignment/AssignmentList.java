package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.assignment.exceptions.AssignmentNotFoundException;

public class AssignmentList implements Iterable<Assignment> {
    private final ObservableList<Assignment> internalList = FXCollections.observableArrayList();

    public AssignmentList(List<Assignment> source) {
        internalList.addAll(source);
    }

    public AssignmentList() { }

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

    @Override
    public String toString() {
        String result = "";
        Iterator<Assignment> it = iterator();
        while (it.hasNext()) {
            result += it.next();
            result += ", ";
        }
        return result;
    }
}
