public class User {
    private int ID;
    private String name;
    private String password;
    private String WH;

    public User(int ID, String name, String WH) {
        this.ID = ID;
        this.name = name;
        this.WH = WH;
    }

    public User(){
    }

    public String getWH() {
        return WH;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
