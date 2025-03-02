import java.util.ArrayList;

public class Student {
    private String studentCode;
    private String password;
    private String name;
    private String lastName;
    private String id;
    private int birthYear;
    private String address;
    private static ArrayList<Student> students = new ArrayList<Student>();

    private Student(String studentCode, String password, String name, String lastName, String id, int birthYear, String address){
        this.studentCode = new String(studentCode);
        this.password = new String(password);
        this.name = new String(name);
        this.lastName = new String(lastName);
        this.id = new String(id);
        this.birthYear = birthYear;
        this.address = new String(address);

    }

    public static void addStudent(String studentCode, String password, String name, String lastName, String id, int birthYear, String address){
        if(!studentExist(studentCode)){
            students.add(new Student(studentCode, password, name, lastName, id, birthYear, address));
        }
        else {
            System.out.println("duplicate-id");
        }
    }

    
}