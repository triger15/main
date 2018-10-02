package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assignment.GradeBook;

public class XmlAdaptedGradeBook implements XmlAdapted<GradeBook> {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "GradeBook's %s field is missing!";

    @XmlElement
    private List<XmlGrade> grades = new ArrayList<>();

    public XmlAdaptedGradeBook() { }

    public XmlAdaptedGradeBook(GradeBook source) {
        source.stream()
            .map(XmlGrade::new)
            .forEach(grades::add);
    }

    @Override
    public GradeBook toModelType() {
        GradeBook gradebook = new GradeBook();

        grades.stream()
            .forEach(g -> {
                try {
                    gradebook.addGrade(
                        ParserUtil.parseStudentId(g.getStudentId()), g.getMarks());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });

        return gradebook;
    }
}
