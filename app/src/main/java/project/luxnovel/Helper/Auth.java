package project.luxnovel.Helper;

import project.luxnovel.Model.User;

public class Auth {
    private static Auth auth;
    private  User user;

    private Auth() {
        if (user == null)
            user = new User();
    }

    public static synchronized Auth getAuth() {
        if (auth == null)
            auth = new Auth();
        return auth;
    }

    public User getUser() {
        return user;
    }

    public  void setUser(User user) {
        this.user = user;
    }
}