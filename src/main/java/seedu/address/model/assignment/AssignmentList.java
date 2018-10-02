package seedu.address.model.assignment;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
        other.internalList.addAll(internalList.stream()
            .map(Assignment::new)
            .collect(Collectors.toList()));
        return other;
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Assignment> asUnmodifiableObservableList() {
        return FXCollections.unmodifiableObservableList(internalList);
    }


    @Override
    public Iterator<Assignment> iterator() {
        return internalList.iterator();
    }

    @Override
    public String toString() {
        return internalList.stream()
            .map(Assignment::toString)
            .collect(Collectors.joining("\n"));
    }
}
