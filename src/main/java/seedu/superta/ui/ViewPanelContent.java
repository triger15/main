package seedu.superta.ui;

import java.net.URL;

import javafx.scene.layout.Region;
import seedu.superta.model.Model;

// @@author Caephler

/**
 * Abstract class for the bottom right panel.
 */
public abstract class ViewPanelContent extends UiPart<Region> {
    public ViewPanelContent(URL fxmlFileUrl) {
        super(fxmlFileUrl);
    }

    public ViewPanelContent(String fxmlFileName) {
        super(fxmlFileName);
    }

    public ViewPanelContent(URL fxmlFileUrl, Region root) {
        super(fxmlFileUrl, root);
    }

    public ViewPanelContent(String fxmlFileName, Region root) {
        super(fxmlFileName, root);
    }

    /**
     * This method should re-render the component.
     * This is used because undo/redo operations do not
     * reflect accurately on this component.
     */
    public abstract void update(Model model);
}
