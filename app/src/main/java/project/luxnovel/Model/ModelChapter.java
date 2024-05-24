package project.luxnovel.Model;

public class ModelChapter
{
    private Integer id;
    private Integer novel;
    private Integer serial;
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

    public Integer getSerial()
    {
        return serial;
    }

    public void setSerial(Integer numerical)
    {
        this.serial = numerical;
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

    public ModelChapter(Integer id, Integer novel, Integer serial, Integer name, Integer content, String date)
    {
        this.id = id;
        this.novel = novel;
        this.serial = serial;
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public ModelChapter(Integer serial, Integer name, String date)
    {
        this.serial = serial;
        this.name = name;
        this.date = date;
    }

    public ModelChapter()
    {

    }
}
