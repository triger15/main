package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.TutorialGroup.TutorialGroup;
import seedu.address.model.assignment.AssignmentList;
import seedu.address.model.student.Student;
import seedu.address.model.student.UniqueStudentList;

public class XmlAdaptedTutorialGroup implements XmlAdapted<TutorialGroup> {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutorial Group's %s field is missing!";

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String id;
    @XmlElement
    private List<XmlAdaptedPerson> students = new ArrayList<>();

    public XmlAdaptedTutorialGroup() {}

    public XmlAdaptedTutorialGroup(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public XmlAdaptedTutorialGroup(TutorialGroup source) {
        name = source.getName();
        id = source.getId();
        students = source.getStudents().asUnmodifiableObservableList().stream()
            .map(XmlAdaptedPerson::new)
            .collect(Collectors.toList());
    }

    @Override
    public TutorialGroup toModelType() throws IllegalValueException {
        final List<Student> modelStudents = new ArrayList<>();
        for (XmlAdaptedPerson student : students) {
            modelStudents.add(student.toModelType());
        }
        UniqueStudentList studentList = new UniqueStudentList();
        studentList.setPersons(modelStudents);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "ID"));
        }

        // TODO: Replace last argument with actual assignment syntax
        return new TutorialGroup(id, name, studentList, new AssignmentList());
    }

}
