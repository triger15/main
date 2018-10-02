package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT_MARKS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_ASSIGNMENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_STUDENT_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENERAL_TUTORIAL_GROUP_ID;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.GradeAssignmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.Grade;
import seedu.address.model.student.StudentId;

/**
 * Parser for the grade-assignment command.
 */
public class GradeAssignmentCommandParser implements Parser<GradeAssignmentCommand> {

    @Override
    public GradeAssignmentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMap = ArgumentTokenizer.tokenize(
            args,
            PREFIX_GENERAL_TUTORIAL_GROUP_ID,
            PREFIX_GENERAL_ASSIGNMENT_ID,
            PREFIX_GENERAL_STUDENT_ID,
            PREFIX_ASSIGNMENT_MARKS
        );

        if (!arePrefixesPresent(
            argMap,
            PREFIX_GENERAL_TUTORIAL_GROUP_ID,
            PREFIX_GENERAL_ASSIGNMENT_ID,
            PREFIX_GENERAL_STUDENT_ID,
            PREFIX_ASSIGNMENT_MARKS
        ) || !argMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, GradeAssignmentCommand.MESSAGE_USAGE));
        }

        String tgId = ParserUtil.parseTutorialGroupId(
            argMap.getValue(PREFIX_GENERAL_TUTORIAL_GROUP_ID).get()
        );
        String asId = ParserUtil.parseString(
            argMap.getValue(PREFIX_GENERAL_ASSIGNMENT_ID).get()
        );
        StudentId stId = ParserUtil.parseStudentId(
            argMap.getValue(PREFIX_GENERAL_STUDENT_ID).get()
        );
        Integer marks = ParserUtil.parseInt(
            argMap.getValue(PREFIX_ASSIGNMENT_MARKS).get()
        );

        Grade grade = new Grade(tgId, asId, stId, marks);
        return new GradeAssignmentCommand(grade);
    }
}
