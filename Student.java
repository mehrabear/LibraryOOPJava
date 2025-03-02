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

    public static boolean studentExist(String studentCode){
        for (Student student:students) {
            if(student.studentCode.equals(studentCode)){
                return true;
            }
        }
        return false;
    }

    public static void editStudent(String studentCode, String password, String name, String lastName, String id, String birthYear, String address){
        if(Student.studentExist(studentCode)){
            Student student = searchStudent(studentCode);
            if(!password.equals("-")){
                student.password = new String(password);
            }
            if(!name.equals("-")){
                student.name = new String(name);
            }
            if(!lastName.equals("-")){
                student.lastName = new String(lastName);
            }
            if(!id.equals("-")){
                student.id = new String(id);
            }
            if(!birthYear.equals("-")){
                student.birthYear = Integer.parseInt(birthYear);
            }
            if(!address.equals("-")){
                student.address = new String(address);
            }

        }
        else {
            System.out.println("not-found");
        }
    }

    private static Student searchStudent(String studentCode){
        for (Student student:students) {
            if(student.studentCode.equals(studentCode)){
                return student;
            }
        }
        return null;
    }
}