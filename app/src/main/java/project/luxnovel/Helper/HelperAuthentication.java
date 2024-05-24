package project.luxnovel.Helper;

import project.luxnovel.Model.ModelUser;

public class HelperAuthentication
{
    private static HelperAuthentication authentication;
    private ModelUser user;

    private HelperAuthentication()
    {
        if (user == null) user = new ModelUser();
    }

    public static synchronized HelperAuthentication getAuthentication()
    {
        if (authentication == null) authentication = new HelperAuthentication();
        return authentication;
    }

    public ModelUser getUser() {
        return user;
    }

    public  void setUser(ModelUser user) {
        this.user = user;
    }
}
