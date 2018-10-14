package seedu.superta.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.superta.model.ReadOnlySuperTaClient;
import seedu.superta.model.SuperTaClient;
import seedu.superta.model.student.Address;
import seedu.superta.model.student.Email;
import seedu.superta.model.student.Feedback;
import seedu.superta.model.student.Name;
import seedu.superta.model.student.Phone;
import seedu.superta.model.student.Student;
import seedu.superta.model.student.StudentId;
import seedu.superta.model.tag.Tag;

/**
 * Contains utility methods for populating {@code SuperTaClient} with sample data.
 */
public class SampleDataUtil {

    public static final Feedback EMPTY_FEEDBACK = new Feedback("");

    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                new StudentId("A0123456T"),
                getTagSet("friends"),
                EMPTY_FEEDBACK),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                new StudentId("A0144582N"),
                getTagSet("colleagues", "friends"),
                EMPTY_FEEDBACK),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                new StudentId("A0128314Y"),
                getTagSet("neighbours"),
                EMPTY_FEEDBACK),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                new StudentId("A0122301Y"),
                getTagSet("family"),
                EMPTY_FEEDBACK),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                new StudentId("A0182324E"),
                getTagSet("classmates"),
                EMPTY_FEEDBACK),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                new StudentId("A0139410Y"),
                getTagSet("colleagues"),
                EMPTY_FEEDBACK),
        };
    }

    public static ReadOnlySuperTaClient getSampleAddressBook() {
        SuperTaClient sampleAb = new SuperTaClient();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
