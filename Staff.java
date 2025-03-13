import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Staff{
    private String staffCode;
    private String password;
    private String name;
    private String lastName;
    private String id;
    private int birthYear;
    private String address;
    private ArrayList<ArrayList<String>> objects = new ArrayList<ArrayList<String>>();
    private static ArrayList<Staff> staffs = new ArrayList<Staff>();

    private Staff(String staffCode, String password, String name, String lastName, String id, int birthYear, String address){
        this.staffCode = staffCode;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.id = id;
        this.birthYear = birthYear;
        this.address = address;

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
                staff.password = password;
            }
            if(!name.equals("-")){
                staff.name = name;
            }
            if(!lastName.equals("-")){
                staff.lastName = lastName;
            }
            if(!id.equals("-")){
                staff.id = id;
            }
            if(!birthYear.equals("-")){
                staff.birthYear = Integer.parseInt(birthYear);
            }
            if(!address.equals("-")){
                staff.address = address;
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
                    if (staff.objects.isEmpty()){
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

    public static boolean passwordCheck(String id, String password){
        return password.equals(searchStaff(id).password);
    }

    public static boolean canBorrow(String id){
        return searchStaff(id).objects.size() < 5;
    }
    public static void borrow(String id, String libraryId, String objectId, String date, String hour){
        searchStaff(id).objects.add(new ArrayList<String>(Arrays.asList(libraryId, objectId, date, hour)));
    }
}