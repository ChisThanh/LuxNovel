package project.luxnovel.Model;

public class ModelNovelAPI extends ModelNovel {

    private String author_name;
    private String category_name;

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public ModelNovelAPI(Integer id, String author_name, String category_name, String name, String description, String cover) {
        super(id, name, -1, -1, description, "-1", cover);
        this.author_name = author_name;
        this.category_name = category_name;
    }
}
