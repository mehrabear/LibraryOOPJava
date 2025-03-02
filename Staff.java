import java.util.ArrayList;
import java.util.Iterator;

public class Staff{
    private String staffCode;
    private String password;
    private String name;
    private String lastName;
    private String id;
    private int birthYear;
    private String address;
    private ArrayList<Book> books;
    private static ArrayList<Staff> staffs = new ArrayList<Staff>();

    private Staff(String staffCode, String password, String name, String lastName, String id, int birthYear, String address){
        this.staffCode = new String(staffCode);
        this.password = new String(password);
        this.name = new String(name);
        this.lastName = new String(lastName);
        this.id = new String(id);
        this.birthYear = birthYear;
        this.address = new String(address);
        this.books = new ArrayList<Book>();

    }

    public static void addStaff(String staffCode, String password, String name, String lastName, String id, int birthYear, String address){
        if(!staffExist(staffCode)){
            staffs.add(new Staff(staffCode, password, name, lastName, id, birthYear, address));
            System.out.println("success");
        }
        else {
            System.out.println("duplicate-id");
        }
    }

    public static boolean staffExist(String staffCode){
        for (Staff staff:staffs) {
            if(staff.staffCode.equals(staffCode)){
                return true;
            }
        }
        return false;
    }

    public static void editStaff(String staffCode, String password, String name, String lastName, String id, String birthYear, String address){
        if(staffExist(staffCode)){
            Staff staff = searchStaff(staffCode);
            if(!password.equals("-")){
                staff.password = new String(password);
            }
            if(!name.equals("-")){
                staff.name = new String(name);
            }
            if(!lastName.equals("-")){
                staff.lastName = new String(lastName);
            }
            if(!id.equals("-")){
                staff.id = new String(id);
            }
            if(!birthYear.equals("-")){
                staff.birthYear = Integer.parseInt(birthYear);
            }
            if(!address.equals("-")){
                staff.address = new String(address);
            }
            System.out.println("success");

        }
        else {
            System.out.println("not-found");
        }
    }

    public static void removeStaff(String staffCode){
        if(staffExist(staffCode)){
            Iterator<Staff> staffIterator = staffs.iterator();
            while (staffIterator.hasNext()){
                Staff staff = staffIterator.next();
                if(staff.staffCode.equals(staffCode)){
                    if (staff.books.size() == 0){
                        staffIterator.remove();
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
    private static Staff searchStaff(String staffCode){
        for (Staff staff:staffs) {
            if(staff.staffCode.equals(staffCode)){
                return staff;
            }
        }
        return null;
    }


}