package project.luxnovel.Model;

public class ModelUser
{
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String dob;
    private String gender;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDob()
    {
        return dob;
    }

    public void setDob(String dob)
    {
        this.dob = dob;
    }

    public String getGender()
    {
        return gender;
    }

    public void setGender(String gender)
    {
        this.gender = gender;
    }

    public ModelUser()
    {

    }

    public ModelUser(Integer id) {
        this.id = id;
    }

    public ModelUser(String username)
    {
        this.username = username;
    }

    public ModelUser(String username, String password, String email)
    {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public ModelUser(Integer id, String username, String password, String email, String name, String dob, String gender)
    {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
    }

    public ModelUser(String username, String password, String email, String name, String dob, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
    }
}
