package seedu.superta.testutil;

import seedu.superta.model.SuperTaClient;
import seedu.superta.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code SuperTaClient ab = new SuperTaClientBuilder().withPerson("John", "Doe").build();}
 */
public class SuperTaClientBuilder {

    private SuperTaClient superTAClient;

    public SuperTaClientBuilder() {
        superTAClient = new SuperTaClient();
    }

    public SuperTaClientBuilder(SuperTaClient superTAClient) {
        this.superTAClient = superTAClient;
    }

    /**
     * Adds a new {@code Student} to the {@code SuperTaClient} that we are building.
     */
    public SuperTaClientBuilder withPerson(Student student) {
        superTAClient.addStudent(student);
        return this;
    }

    public SuperTaClient build() {
        return superTAClient;
    }
}
