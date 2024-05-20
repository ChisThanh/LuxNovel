package project.luxnovel.Model;

public class User {
    private int id_User;
    private String name_Login;
    private String Password;
    private String Email;
    private String UserName;
    private String Dob;
    private String Gender;

    public User(String name_Login, String password, String email, String userName, String dob, String gender) {
        this.name_Login = name_Login;
        Password = password;
        Email = email;
        UserName = userName;
        Dob = dob;
        Gender = gender;
    }

    public User() {

    }

    public User(String name_Login,String password, String email) {
        this.name_Login = name_Login;
        Password=password;
        Email = email;
    }

    public int getId_User() {
        return id_User;
    }

    public void setId_User(int id_User) {
        this.id_User = id_User;
    }

    public String getName_Login() {
        return name_Login;
    }

    public void setName_Login(String name_Login) {
        this.name_Login = name_Login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getDob() {
        return Dob;
    }

    public void setDob(String dob) {
        Dob = dob;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }
}
