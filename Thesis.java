public class Thesis {
    private final String id;
    private String title;
    private String studentName;
    private String professorName;
    private int year;
    private Category category;
    private boolean borrowed;

    public Thesis(String id, String title, String studentName, String professorName, int year, Category category){
        this.id = new String(id);
        this.title = new String(title);
        this.studentName = new String(studentName);
        this.professorName = new String(professorName);
        this.year = year;
        this.category = category;
        borrowed = false;
    }

    public String getId(){
        return new String(this.id);
    }

    public void setTitle(String title){
        this.title = new String(title);
    }

    public void setStudentName(String studentName){
        this.studentName = new String(studentName);
    }

    public void setProfessorName(String professorName){
        this.professorName = new String(professorName);
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setCategory(String categoryId){
        if(Category.categoryExist(categoryId)){
            this.category = Category.catssearch(categoryId);
        }
    }

    public boolean getBorrowed(){
        return this.borrowed;
    }







}
