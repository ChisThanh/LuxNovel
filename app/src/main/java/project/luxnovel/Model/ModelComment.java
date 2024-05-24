package project.luxnovel.Model;

public class ModelComment
{
    private String user;
    private Double rating;
    private String comment;

    public String getUser()
    {
        return user;
    }

    public void setUser(String user)
    {
        this.user = user;
    }

    public Double getRating()
    {
        return rating;
    }

    public void setRating(Double rating)
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

    public ModelComment(String user, Double rating, String comment)
    {
        this.user = user;
        this.rating = rating;
        this.comment = comment;
    }

    public ModelComment()
    {

    }
}
