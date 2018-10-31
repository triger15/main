package seedu.superta.commons.events.ui;

import seedu.superta.commons.events.BaseEvent;
import seedu.superta.model.Model;

/**
 * StateEvent means Undo or Redo event.
 * To update UI correctly.
 */
public class StateEvent extends BaseEvent {
    private Model model;

    public StateEvent(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return this.model;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
