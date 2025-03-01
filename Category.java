import java.util.ArrayList;

public class Category {
    private String id;
    private String name;
    private static ArrayList<Category> cats= new ArrayList<Category>();

    private Category(String id, String name){
        this.id = new String(id);
        this.name = new String(name);

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
        return new String(this.id);
    }
    public static boolean categoryExist(String id){
        for(int i = 0; i < cats.size(); i++){
            if(cats.get(i).id.equals(id)){
                return true;
            }
        }
        return false;
    }

    public static Category catssearch(String id){
        for(Category category:cats){
            if(category.id.equals(id)){
                return category;
            }
        }
        return null;
    }


}
