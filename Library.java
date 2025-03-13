import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

public class Library {
    private String id;
    private String name;
    private int year;
    private int tableCount;
    private String address;
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<Thesis> thesisess = new ArrayList<Thesis>();
    private static ArrayList<Library> libs = new ArrayList<Library>();

    private Library(String id, String name, int year, int tableCount, String address){
        this.id = id;
        this.name = name;
        this.year = year;
        this.tableCount = tableCount;
        this.address = address;
    }
    public static void addLibrary(String id, String name, int year, int tableCount, String address){
        if(!libraryExist(id)){
            System.out.println("success");
            libs.add(new Library(id, name, year, tableCount, address));
        }
        else{
            System.out.println("duplicate-id");
        }
    }
    public void addBook(String id, String title, String author, String publisher, int publishYear, int numberOfCopies,String categoryId){
        if(Category.categoryExist(categoryId)){
            if(bookExist(id)){
                System.out.println("success");
                this.books.add(new Book(id, title, author, publisher, publishYear, numberOfCopies, Category.catsSearch(categoryId),this.id));
            }
            else{
                System.out.println("duplicate-id");
            }
        }
        else{
            System.out.println("not-found");
        }
    }

    public void editBook(String id, String title, String author, String publisher, String publishYear, String numberOfCopies,String categoryId){
        for(Book book: this.books){
            if((book.getId().equals(id))&&(categoryId.equals("-") || Category.categoryExist(categoryId))){
                if(!title.equals("-")){
                    book.setTitle(title);
                }
                if(!author.equals("-")){
                    book.setAuthor(author);
                }
                if(!publisher.equals("-")){
                    book.setPublisher(publisher);
                }
                if(!publishYear.equals("-")){
                    book.setPublishYear(Integer.parseInt(publishYear));
                }
                if(!numberOfCopies.equals("-")){
                    book.setNumberOfCopies(Integer.parseInt(numberOfCopies));
                }
                if(!categoryId.equals("-")){
                    book.setCategory(categoryId);
                }
                return;
            }
        }
        System.out.println("not-found");
    }

    public void addThesis(String id, String title, String studentName, String professorName, int year, String categoryId){
        if(Category.categoryExist(categoryId)){
            if(thesisExist(id)){
                System.out.println("success");
                this.thesisess.add(new Thesis(id, title, studentName, professorName, year, Category.catsSearch(categoryId)));
            }
            else{
                System.out.println("duplicate-id");
            }
        }
        else{
            System.out.println("not-found");
        }
    }

    public void editThesis(String id, String title, String studentName, String professorName, String year, String categoryId){
        for(Thesis thesis: this.thesisess){
            if((thesis.getId().equals(id))&&((categoryId.equals("-")) ||  Category.categoryExist(categoryId))){
                if(!title.equals("-")){
                    thesis.setTitle(title);
                }
                if(!studentName.equals("-")){
                    thesis.setStudentName(studentName);
                }
                if(!professorName.equals("-")){
                    thesis.setProfessorName(professorName);
                }
                if(!year.equals("-")){
                    thesis.setYear(Integer.parseInt(year));
                }
                if(!categoryId.equals("-")){
                    thesis.setCategory(categoryId);
                }
                return;
            }
        }
        System.out.println("not-found");
    }

    public String getId(){
        return this.id;
    }
    public static boolean libraryExist(String id){
        for (Library lib: libs) {
            if(lib.getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    public boolean bookExist(String id){
        for(Book book:this.books){
            if(book.getId().equals(id)){
                return true;
            }
        }
        return false;
    }


    public boolean thesisExist(String id){
        for(Thesis thesis:this.thesisess){
            if(thesis.getId().equals(id)){
                return true;
            }
        }
        return false;
    }


    public void removeBook(String id){
        Iterator<Book> bookIterator = books.iterator();
        while(bookIterator.hasNext()){
            Book book = bookIterator.next();
            if(book.getId().equals(id)){
                if(book.getNumberBorrowed() == 0){
                    bookIterator.remove();
                    return;
                }
                else {
                    System.out.println("not-allowed");
                }
            }
        }
        System.out.println("not-found");
    }

    public void removeThesis(String id){
        Iterator<Thesis> thesisIterator = thesisess.iterator();
        while(thesisIterator.hasNext()){
            Thesis thesis = thesisIterator.next();
            if(thesis.getId().equals(id)){
                if(thesis.getBorrowed()){
                    thesisIterator.remove();
                    return;
                }
                else {
                    System.out.println("not-allowed");
                }
            }
        }
        System.out.println("not-found");
    }

    public void borrow(String id, String password, String libraryId, String objectId, String date, String hour){
        if(Student.studentExist(id)){
            if(bookExist(objectId)){
                if(Student.passwordCheck(id, password)){
                    if(bookSearch(objectId).canBorrow() && Student.canBorrow(id)){
                        System.out.println("success");
                        bookSearch(objectId).borrow();
                        Student.borrow(id, libraryId, objectId, date, hour);
                    }
                    else {
                        System.out.println("not-allowed");
                    }
                }
                else {
                    System.out.println("invalid-pass");
                }
            }
            else if(thesisExist(objectId)){
                if(Student.passwordCheck(id, password)){
                    if(!thesisSearch(objectId).getBorrowed() && Student.canBorrow(id)){
                        System.out.println("success");
                        thesisSearch(objectId).borrow();
                        Student.borrow(id, libraryId, objectId, date, hour);
                    }
                    else {
                        System.out.println("not-allowed");
                    }
                }
                else {
                    System.out.println("invalid-pass");
                }
            }
            else {
                System.out.println("not-found");
            }
        }
        else if(Staff.staffExist(id)){
            if(bookExist(objectId)){
                if(Staff.passwordCheck(id, password)){
                    if(bookSearch(objectId).canBorrow() && Staff.canBorrow(id)){
                        System.out.println("success");
                        bookSearch(objectId).borrow();
                        Staff.borrow(id, libraryId, objectId, date, hour);
                    }
                    else {
                        System.out.println("not-allowed");
                    }
                }
                else {
                    System.out.println("invalid-pass");
                }
            }
            else if(thesisExist(objectId)){
                if(Staff.passwordCheck(id, password)){
                    if(!thesisSearch(objectId).getBorrowed() && Staff.canBorrow(id)){
                        System.out.println("success");
                        thesisSearch(objectId).borrow();
                        Staff.borrow(id, libraryId, objectId, date, hour);
                    }
                    else {
                        System.out.println("not-allowed");
                    }
                }
                else {
                    System.out.println("invalid-pass");
                }
            }
            else {
                System.out.println("not-found");
            }

        }
        else{
            System.out.println("not-found");
        }
    }

    public void returnBorrow(String id, String password, String libraryId, String objectId, String date, String hour){
        if(Student.studentExist(id)){
            if(bookExist(objectId)){
                if(Student.passwordCheck(id, password)){
                    if(Student.objectExist(id, libraryId, objectId)){
                        System.out.println("success");
                        Student.returnObject(id, libraryId, objectId, date, hour, 'B');
                        bookSearch(objectId).returnBook();
                    }
                    else {
                        System.out.println("not-found");
                    }
                }
                else {
                    System.out.println("invalid-pass");
                }
            }
            else if(thesisExist(objectId)){
                if(Student.passwordCheck(id, password)){
                    if(Student.objectExist(id, libraryId, objectId)){
                        System.out.println("success");
                        Student.returnObject(id, libraryId, objectId, date, hour, 'T');
                        thesisSearch(objectId).returnThesis();
                    }
                    else {
                        System.out.println("not-found");
                    }
                }
                else {
                    System.out.println("invalid-pass");
                }
            }
            else {
                System.out.println("not-found");
            }
        }
        else if(Staff.staffExist(id)){
            if(bookExist(objectId)){
                if(Staff.passwordCheck(id, password)){
                    if(Staff.objectExist(id, libraryId, objectId)){
                        System.out.println("success");
                        Staff.returnObject(id, libraryId, objectId, date, hour, 'B');
                        bookSearch(objectId).returnBook();
                    }
                    else {
                        System.out.println("not-found");
                    }
                }
                else {
                    System.out.println("invalid-pass");
                }
            }
            else if(thesisExist(objectId)){
                if(Staff.passwordCheck(id, password)){
                    if(Staff.objectExist(id, libraryId, objectId)){
                        System.out.println("success");
                        Staff.returnObject(id, libraryId, objectId, date, hour, 'T');
                        thesisSearch(objectId).returnThesis();
                    }
                    else {
                        System.out.println("not-found");
                    }
                }
                else {
                    System.out.println("invalid-pass");
                }
            }
            else {
                System.out.println("not-found");
            }

        }
        else{
            System.out.println("not-found");
        }
    }

    private Book bookSearch(String id){
        for (Book book:books) {
            if (book.getId().equals(id)){
                return book;
            }
        }
        return null;
    }

    private Thesis thesisSearch(String id){
        for (Thesis thesis:thesisess) {
            if (thesis.getId().equals(id)){
                return thesis;
            }
        }
        return null;
    }

    public static void search(String str){
        ArrayList<String> matchs = new ArrayList<>();

        for (Library library:libs) {
            for(Book book:library.books){
                if(book.matches(str)){
                    matchs.add(book.getId());
                }
            }
            for (Thesis thesis: library.thesisess) {
                if(thesis.matches(str)){
                    matchs.add(thesis.getId());
                }
            }
        }

        Collections.sort(matchs);

        if(matchs.size() == 0){
            System.out.print("no-found");
            return;
        }

        for (int i = 0; i < matchs.size(); i++) {
            System.out.print(matchs.get(i));
            if(i < matchs.size() - 1){
                System.out.print("|");
            }
        }
        System.out.println();
    }

    public static int diffrenceHour(String h1, String d1, String h2, String d2){
        int[] hour1 = new int[2];
        int[] hour2 = new int[2];
        hour1[0] = Integer.parseInt(h1.split(":")[0]);
        hour1[1] = Integer.parseInt(h1.split(":")[1]);
        hour2[0] = Integer.parseInt(d1.split(":")[0]);
        hour2[1] = Integer.parseInt(d1.split(":")[1]);
        int[] date1 = new int[3];
        int[] date2 = new int[3];
        date1[0] = Integer.parseInt(d1.split("-")[0]);
        date1[1] = Integer.parseInt(d1.split("-")[1]);
        date1[2] = Integer.parseInt(d1.split("-")[2]);
        date2[0] = Integer.parseInt(d2.split("-")[0]);
        date2[1] = Integer.parseInt(d2.split("-")[1]);
        date2[2] = Integer.parseInt(d2.split("-")[2]);

        Date start = new Date(date1[0] - 1900,date1[1],date1[2],hour1[0],hour1[1], 0);
        Date end = new Date(date2[0] - 1900,date2[1],date2[2],hour2[0],hour2[1], 0);

        long diff = end.getTime() - start.getTime();

        diff = diff / (24 * 60 * 60 * 1000);
        return (int) diff;
    }
}
