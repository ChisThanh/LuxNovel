package project.luxnovel.Model;

public class ModelNovel
{
    private Integer id;
    private String name;
    private Integer author;
    private Integer category;
    private String describe;
    private String state;
    private String image;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getAuthor()
    {
        return author;
    }

    public void setAuthor(Integer author)
    {
        this.author = author;
    }

    public Integer getCategory()
    {
        return category;
    }

    public void setCategory(Integer category)
    {
        this.category = category;
    }

    public String getDescribe()
    {
        return describe;
    }

    public void setDescribe(String describe)
    {
        this.describe = describe;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public ModelNovel(Integer id, String name, Integer author, Integer category, String describe, String state, String image)
    {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.describe = describe;
        this.state = state;
        this.image = image;
    }

    public ModelNovel()
    {

    }
}
