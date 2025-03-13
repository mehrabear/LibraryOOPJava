import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Student {
    private String studentCode;
    private String password;
    private String name;
    private String lastName;
    private String id;
    private int birthYear;
    private String address;
    private ArrayList<ArrayList<String>> objects = new ArrayList<ArrayList<String>>();
    private static ArrayList<Student> students = new ArrayList<Student>();

    private Student(String studentCode, String password, String name, String lastName, String id, int birthYear, String address){
        this.studentCode = studentCode;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.birthYear = birthYear;
        this.address = address;
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
                student.password = password;
            }
            if(!name.equals("-")){
                student.name = name;
            }
            if(!lastName.equals("-")){
                student.lastName = lastName;
            }
            if(!id.equals("-")){
                student.id = id;
            }
            if(!birthYear.equals("-")){
                student.birthYear = Integer.parseInt(birthYear);
            }
            if(!address.equals("-")){
                student.address = address;
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
                    if (student.objects.isEmpty()){
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
        return password.equals(searchStudent(id).password);
    }

    public static boolean canBorrow(String id){
        return searchStudent(id).objects.size() < 3;
    }


    public static void borrow(String id, String libraryId, String objectId){
        searchStudent(id).objects.add(new ArrayList<String>(Arrays.asList(libraryId, objectId)));
    }
}