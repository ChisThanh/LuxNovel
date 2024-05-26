package project.luxnovel.Model;

public class ModelState
{
    private Integer id;
    private Integer user;
    private Integer novel;
    private String state;
    private int rating;
    private String comment;
    private String time;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getUser()
    {
        return user;
    }

    public void setUser(Integer user)
    {
        this.user = user;
    }

    public Integer getNovel()
    {
        return novel;
    }

    public void setNovel(Integer novel)
    {
        this.novel = novel;
    }

    public String getState()
    {
        return state;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public int getRating()
    {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public ModelState(Integer id, Integer user, Integer novel, String state, int rating, String comment, String time)
    {
        this.id = id;
        this.user = user;
        this.novel = novel;
        this.state = state;
        this.rating = rating;
        this.comment = comment;
        this.time = time;
    }

    public ModelState()
    {

    }
}
