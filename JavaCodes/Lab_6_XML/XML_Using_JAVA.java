import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class StudentExample {
    public static void main(String[] args) {
        try {
            // Initialize JAXB context for the generated classes
            JAXBContext context = JAXBContext.newInstance("your.generated.package.name");

            // Create a student object
            Student student = new Student();
            student.setName("John Doe");
            student.setAge(20);
            student.setGrade("A");

            // Marshalling (Java object to XML)
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(student, new File("student.xml"));

            // Unmarshalling (XML to Java object)
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Student unmarshalledStudent = (Student) unmarshaller.unmarshal(new File("student.xml"));

            // Print the unmarshalled student data
            System.out.println("Name: " + unmarshalledStudent.getName());
            System.out.println("Age: " + unmarshalledStudent.getAge());
            System.out.println("Grade: " + unmarshalledStudent.getGrade());
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
