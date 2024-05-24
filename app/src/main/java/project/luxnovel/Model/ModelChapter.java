package project.luxnovel.Model;

public class ModelChapter
{
    private Integer id;
    private Integer novel;
    private Integer numerical;
    private Integer name;
    private Integer content;
    private String date;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getNovel()
    {
        return novel;
    }

    public void setNovel(Integer novel)
    {
        this.novel = novel;
    }

    public Integer getNumerical()
    {
        return numerical;
    }

    public void setNumerical(Integer numerical)
    {
        this.numerical = numerical;
    }

    public Integer getName()
    {
        return name;
    }

    public void setName(Integer name)
    {
        this.name = name;
    }

    public Integer getContent()
    {
        return content;
    }

    public void setContent(Integer content)
    {
        this.content = content;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public ModelChapter(Integer id, Integer novel, Integer numerical, Integer name, Integer content, String date)
    {
        this.id = id;
        this.novel = novel;
        this.numerical = numerical;
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public ModelChapter()
    {

    }
}
