public class Book {
    private final String id;
    private String title;
    private String author;
    private String publisher;
    private int publishYear;
    private int numberOfCopies;
    private Category category;
    private int numberBorrowed;


    public Book(String id, String title, String author, String publisher, int publishYear, int numberOfCopies,Category category, String libraryId){
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = author;
        this.publishYear = publishYear;
        this.numberOfCopies = numberOfCopies;
        this.category = category;
        this.numberBorrowed = 0;

    }

    public String getId(){
        return this.id;
    }


    public int getNumberBorrowed(){
        return this.numberBorrowed;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public void setPublisher(String publisher){
        this.publisher = publisher;
    }

    public void setPublishYear(int publishYear){
        this.publishYear = publishYear;
    }

    public void setNumberOfCopies(int numberOfCopies){
        this.numberOfCopies = numberOfCopies;
    }

    public void setCategory(String categoryId){
        if(Category.categoryExist(categoryId)){
            this.category = Category.catsSearch(categoryId);
        }
    }

    public boolean canBorrow(){
        return this.numberOfCopies - this.numberBorrowed > 0;
    }

    public void borrow(){
        this.numberBorrowed ++;
    }



}
