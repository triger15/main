package seedu.superta.logic.commands;

public interface Descriptor {
    /**
     * This should return true if any field is edited.
     */
    boolean isAnyFieldEdited();
}
