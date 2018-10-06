package systemtests;

import org.junit.Test;

import seedu.address.logic.commands.CreateTutorialGroupCommand;
import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.tutorialgroup.TutorialGroup;
import seedu.address.testutil.TutorialGroupBuilder;
import seedu.address.testutil.TypicalPersons;

public class CreateTutorialGroupSystemTest extends AddressBookSystemTest {

    @Test
    public void createTutorialGroup() {
        Model model = getModel();

        Student firstStudent = TypicalPersons.ALICE;
        Student secondStudent = TypicalPersons.BOB;

        TutorialGroupBuilder tgBuilder = new TutorialGroupBuilder();
        TutorialGroup typicalTg = tgBuilder.build();
        String command = "  " + CreateTutorialGroupCommand.COMMAND_WORD + " n/CS1101S Studio 04A " + "id/04a";

        assertCommandSuccess(command, typicalTg, String.format(CreateTutorialGroupCommand.MESSAGE_SUCCESS, typicalTg));
    }

    /**
     * Checks whether the assertion was correct.
     */
    private void assertCommandSuccess(String command, TutorialGroup expected, String expectedMessage) {
        Model expectedModel = getModel();
        expectedModel.addTutorialGroup(expected);
        executeCommand(command);
        assertApplicationDisplaysExpected("", expectedMessage, expectedModel);
        assertSelectedCardUnchanged();
        assertCommandBoxShowsDefaultStyle();
        assertStatusBarUnchangedExceptSyncStatus();
    }

}
