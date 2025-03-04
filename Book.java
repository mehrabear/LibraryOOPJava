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
        this.id = new String(id);
        this.title = new String(title);
        this.author = new String(author);
        this.publisher = new String(author);
        this.publishYear = publishYear;
        this.numberOfCopies = numberOfCopies;
        this.category = category;
        this.numberBorrowed = 0;

    }

    public String getId(){
        return new String(this.id);
    }


    public int getNumberBorrowed(){
        return this.numberBorrowed;
    }
    public void setTitle(String title){
        this.title = new String(title);
    }

    public void setAuthor(String author){
        this.author = new String(author);
    }

    public void setPublisher(String publisher){
        this.publisher = new String(publisher);
    }

    public void setPublishYear(int publishYear){
        this.publishYear = publishYear;
    }

    public void setNumberOfCopies(int numberOfCopies){
        this.numberOfCopies = numberOfCopies;
    }

    public void setCategory(String categoryId){
        if(Category.categoryExist(categoryId)){
            this.category = Category.catssearch(categoryId);
        }
    }

    public boolean canBorrow(){
        if (this.numberOfCopies - this.numberBorrowed > 0){
            return true;
        }
        return false;
    }




}
