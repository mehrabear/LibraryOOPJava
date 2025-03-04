import java.util.ArrayList;
import java.util.Iterator;

public class Student {
    private String studentCode;
    private String password;
    private String name;
    private String lastName;
    private String id;
    private int birthYear;
    private String address;
    private ArrayList<Book> books;
    private static ArrayList<Student> students = new ArrayList<Student>();

    private Student(String studentCode, String password, String name, String lastName, String id, int birthYear, String address){
        this.studentCode = new String(studentCode);
        this.password = new String(password);
        this.name = new String(name);
        this.lastName = new String(lastName);
        this.id = new String(id);
        this.birthYear = birthYear;
        this.address = new String(address);
        this.books = new ArrayList<Book>();

    }

    public static void addStudent(String studentCode, String password, String name, String lastName, String id, int birthYear, String address){
        if(!studentExist(studentCode)){
            students.add(new Student(studentCode, password, name, lastName, id, birthYear, address));
            System.out.println("success");
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
            System.out.println("success");

        }
        else {
            System.out.println("not-found");
        }
    }

    public static void removeStudent(String studentCode){
        if(studentExist(studentCode)){
            Iterator<Student> studentIterator = students.iterator();
            while (studentIterator.hasNext()){
                Student student = studentIterator.next();
                if(student.studentCode.equals(studentCode)){
                    if (student.books.size() == 0){
                        studentIterator.remove();
                        System.out.println("success");
                    }
                    else {
                        System.out.println("not-allowed");
                    }
                }
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

    public static boolean passwordCheck(String id, String password){
        if(password.equals(searchStudent(id).password)){
            return true;
        }
        return false;
    }
}