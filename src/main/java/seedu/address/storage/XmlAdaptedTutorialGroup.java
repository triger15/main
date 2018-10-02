package seedu.address.storage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.TutorialGroup.TutorialGroup;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.AssignmentList;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.UniqueStudentList;

public class XmlAdaptedTutorialGroup {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutorial Group's %s field is missing!";

    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String id;
    @XmlElement
    private List<String> studentIds = new ArrayList<>();
    @XmlElement
    private List<XmlAdaptedAssignment> assignments = new ArrayList<>();

    public XmlAdaptedTutorialGroup() {}

    public XmlAdaptedTutorialGroup(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public XmlAdaptedTutorialGroup(TutorialGroup source) {
        name = source.getName();
        id = source.getId();
        studentIds = source.getStudents().asUnmodifiableObservableList().stream()
            .map(st -> st.getStudentId().toString())
            .collect(Collectors.toList());
        Iterator<Assignment> it = source.getAssignments().iterator();
        while (it.hasNext()) {
            assignments.add(new XmlAdaptedAssignment(it.next()));
        }
    }

    /**
     * Requires the data store so that we don't recreate students.
     */
    public TutorialGroup toModelType(AddressBook addressBook) throws IllegalValueException {
        final List<Student> modelStudents = new ArrayList<>();
        for (String id : studentIds) {
            Student student = addressBook.getStudentWithId(new StudentId(id)).get();
            modelStudents.add(student);
        }
        UniqueStudentList studentList = new UniqueStudentList();
        studentList.setPersons(modelStudents);

        final List<Assignment> modelAssignments = new ArrayList<>();
        for (XmlAdaptedAssignment ass : assignments) {
            modelAssignments.add(ass.toModelType());
        }
        AssignmentList assignmentList = new AssignmentList(modelAssignments);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }
        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "ID"));
        }

        // TODO: Replace last argument with actual assignment syntax
        return new TutorialGroup(id, name, studentList, assignmentList);
    }

}
