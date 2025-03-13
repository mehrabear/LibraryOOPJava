import java.util.ArrayList;
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
                        /// returning proccess
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
                        /// returning proccess
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
                        /// returning proccess
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
                        /// returning proccess
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
}
