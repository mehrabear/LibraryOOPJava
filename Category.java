import java.util.ArrayList;

public class Category {
    private String id;
    private String name;
    private static ArrayList<Category> cats= new ArrayList<Category>();

    private Category(String id, String name){
        this.id = id;
        this.name = name;

    }

    public static void addCategory(String id, String name){
        if(!categoryExist(id)){
            cats.add(new Category(id, name));
            System.out.println("success");
        }
        else {
            System.out.println("duplicate-id");
        }
    }

    public String getId(){
        return this.id;
    }
    public static boolean categoryExist(String id){
        for (Category cat : cats) {
            if (cat.id.equals(id)) {
                return true;
            }
        }
        return false;
    }

    public static Category catsSearch(String id){
        for(Category category:cats){
            if(category.id.equals(id)){
                return category;
            }
        }
        return null;
    }


}
