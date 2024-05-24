package project.luxnovel.Model;

public class ModelNovel
{
    private Integer id;
    private String name;
    private Integer author;
    private Integer category;
    private String description;
    private String state;
    private String cover;

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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public String getCover()
    {
        return cover;
    }

    public void setCover(String cover)
    {
        this.cover = cover;
    }

    public ModelNovel(Integer id, String name, Integer author, Integer category, String description, String state, String cover)
    {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.description = description;
        this.state = state;
        this.cover = cover;
    }

    public ModelNovel()
    {

    }
}
