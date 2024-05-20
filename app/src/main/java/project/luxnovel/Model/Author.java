package project.luxnovel.Model;

public class Author {
    private int id_Author;
    private String name_Author;

    public Author(int id_Author, String name_Author) {
        this.id_Author = id_Author;
        this.name_Author = name_Author;
    }

    public int getId_Author() {
        return id_Author;
    }

    public void setId_Author(int id_Author) {
        this.id_Author = id_Author;
    }

    public String getName_Author() {
        return name_Author;
    }

    public void setName_Author(String name_Author) {
        this.name_Author = name_Author;
    }
}
