package seedu.address.storage;

import seedu.address.commons.exceptions.IllegalValueException;

public interface XmlAdapted<T> {
    public T toModelType() throws IllegalValueException;
}
