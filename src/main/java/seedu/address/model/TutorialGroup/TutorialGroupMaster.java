package seedu.address.model.TutorialGroup;

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

public class TutorialGroupMaster {
    public static Set<String> uids = new HashSet<>();
    public static final List<String> RANDOM_WORDS = List.of(
        "best",
        "good",
        "froggy",
        "stormy",
        "dust",
        "illegal",
        "dusty"
        );
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

    public void addTutorialGroup(TutorialGroup tg) {
        tutorialGroups.put(tg.getId(), tg);
        uids.add(tg.getId());
    }

    public void setTutorialGroup(TutorialGroup target, TutorialGroup edited) {
        tutorialGroups.put(target.getId(), edited);
    }

    public void setTutorialGroups(List<TutorialGroup> tutorialGroups) {
        requireAllNonNull(tutorialGroups);
        for (TutorialGroup tg : tutorialGroups) {
            this.tutorialGroups.put(tg.getId(), new TutorialGroup(tg));
        }
    }

    public void removeTutorialGroup(TutorialGroup key) {
        tutorialGroups.remove(key.getId());
        uids.remove(key.getId());
    }

    public boolean contains(TutorialGroup tg) {
        return uids.contains(tg.getId());
    }

    public boolean contains(String id) {
        return uids.contains(id);
    }

    public Optional<TutorialGroup> getTutorialGroup(String id) {
        return Optional.ofNullable(tutorialGroups.get(id));
    }

    public ObservableList<TutorialGroup> asUnmodifiableObservableList() {
        List<TutorialGroup> list = new ArrayList<>();
        for (Map.Entry<String, TutorialGroup> entry : tutorialGroups.entrySet()) {
            list.add(entry.getValue());
        }
        ObservableList<TutorialGroup> result = FXCollections.unmodifiableObservableList(
            FXCollections.observableList(list));
        return result;

    }

    public static TutorialGroup createTutorialGroup(String name, String uid) {
        String finalUid = generateUid(uid);
        return new TutorialGroup(finalUid, name);
    }

    private static String randomPrefix() {
        Random rng = new Random();
        int wordIndex = rng.nextInt(TutorialGroupMaster.RANDOM_WORDS.size());
        String word = TutorialGroupMaster.RANDOM_WORDS.get(wordIndex);
        int number = rng.nextInt(1000);
        return "" + word + number;
    }

    public static String generateUid(String uid) {
        String uidCandidate = uid;
        while (uids.contains(uidCandidate)) {
            uidCandidate = uidCandidate + "-" + TutorialGroupMaster.randomPrefix();
        }
        return uidCandidate;
    }
}
