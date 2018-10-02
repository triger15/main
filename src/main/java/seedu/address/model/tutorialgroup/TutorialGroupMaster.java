package seedu.address.model.tutorialgroup;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Model for Tutorial Group Master.
 */
public class TutorialGroupMaster {
    public static final List<String> RANDOM_WORDS = List.of(
        "best",
        "good",
        "froggy",
        "stormy",
        "dust",
        "illegal",
        "dusty"
    );

    private static Set<String> uids = new HashSet<>();

    public final HashMap<String, TutorialGroup> tutorialGroups;

    public TutorialGroupMaster() {
        tutorialGroups = new HashMap<>();
    }

    public TutorialGroupMaster(HashMap<String, TutorialGroup> tutorialGroups) {
        this.tutorialGroups = tutorialGroups;
    }

    public TutorialGroupMaster(TutorialGroupMaster toClone) {
        tutorialGroups = (HashMap<String, TutorialGroup>) toClone.tutorialGroups.clone();
    }

    /**
     * Adds a tutorial group to the listing.
     * @param tg The tutorial group to be added.
     */
    public void addTutorialGroup(TutorialGroup tg) {
        tutorialGroups.put(tg.getId(), tg);
        uids.add(tg.getId());
    }

    /**
     * Updates a tutorial group.
     * @param target the target tutorial group to be edited.
     * @param edited the tutorialgroup with updated parameters.
     */
    public void setTutorialGroup(TutorialGroup target, TutorialGroup edited) {
        tutorialGroups.put(target.getId(), edited);
    }

    /**
     * Used for backups.
     */
    public void setTutorialGroups(List<TutorialGroup> tutorialGroups) {
        requireAllNonNull(tutorialGroups);
        for (TutorialGroup tg : tutorialGroups) {
            this.tutorialGroups.put(tg.getId(), new TutorialGroup(tg));
        }
    }

    /**
     * Removes a tutorial group from the listing.
     * @param key the tutorial group to be removed. Only the ID field will be used to remove.
     */
    public void removeTutorialGroup(TutorialGroup key) {
        tutorialGroups.remove(key.getId());
        uids.remove(key.getId());
    }

    /**
     * Returns true if the listing contains this tutorial group.
     * @param tg the tutorial group to be checked.
     */
    public boolean contains(TutorialGroup tg) {
        return uids.contains(tg.getId());
    }

    /**
     * Returns true if there is a tutorial group that matches this ID.
     * @param id the ID to be checked.
     */
    public boolean contains(String id) {
        return uids.contains(id);
    }

    /**
     * Get a tutorial group given an ID.
     * @param id the ID to be checked.
     * @return The corresponding tutorial group.
     */
    public Optional<TutorialGroup> getTutorialGroup(String id) {
        return Optional.ofNullable(tutorialGroups.get(id));
    }

    /**
     * Returns an unmodifiable view of the current tutorial groups.
     */
    public ObservableList<TutorialGroup> asUnmodifiableObservableList() {
        List<TutorialGroup> list = new ArrayList<>();
        for (Map.Entry<String, TutorialGroup> entry : tutorialGroups.entrySet()) {
            list.add(entry.getValue());
        }
        ObservableList<TutorialGroup> result = FXCollections.unmodifiableObservableList(
            FXCollections.observableList(list));
        return result;

    }

    /**
     * tutorialgroup creation helper.
     */
    public static TutorialGroup createTutorialGroup(String name, String uid) {
        String finalUid = generateUid(uid);
        return new TutorialGroup(finalUid, name);
    }

    /**
     * Utility function: generates a random prefix for appending to IDs.
     */
    private static String randomPrefix() {
        Random rng = new Random();
        int wordIndex = rng.nextInt(TutorialGroupMaster.RANDOM_WORDS.size());
        String word = TutorialGroupMaster.RANDOM_WORDS.get(wordIndex);
        int number = rng.nextInt(1000);
        return "" + word + number;
    }

    /**
     * Given a UID candidate, generates the resultant UID if there is a naming conflict.
     * @param uid The UID candidate
     * @return The UID candidate, if there are no naming conflicts, or an altered version otherwise.
     */
    public static String generateUid(String uid) {
        String uidCandidate = uid;
        while (uids.contains(uidCandidate)) {
            uidCandidate = uidCandidate + "-" + TutorialGroupMaster.randomPrefix();
        }
        return uidCandidate;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof TutorialGroupMaster) {
            TutorialGroupMaster o = (TutorialGroupMaster) other;
            return uids.equals(o.uids)
                && tutorialGroups.equals(o.tutorialGroups);
        }
        return false;
    }
}
