package seedu.superta.logic;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.superta.commons.core.ComponentManager;
import seedu.superta.commons.core.LogsCenter;
import seedu.superta.logic.commands.Command;
import seedu.superta.logic.commands.CommandResult;
import seedu.superta.logic.commands.exceptions.CommandException;
import seedu.superta.logic.parser.AddressBookParser;
import seedu.superta.logic.parser.exceptions.ParseException;
import seedu.superta.model.Model;
import seedu.superta.model.student.Student;

/**
 * The main LogicManager of the app.
 */
public class LogicManager extends ComponentManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final CommandHistory history;
    private final AddressBookParser addressBookParser;

    public LogicManager(Model model) {
        this.model = model;
        history = new CommandHistory();
        addressBookParser = new AddressBookParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        try {
            Command command = addressBookParser.parseCommand(commandText);
            return command.execute(model, history);
        } finally {
            history.add(commandText);
        }
    }

    @Override
    public ObservableList<Student> getFilteredPersonList() {
        return model.getFilteredStudentList();
    }

    @Override
    public ListElementPointer getHistorySnapshot() {
        return new ListElementPointer(history.getHistory());
    }
}
