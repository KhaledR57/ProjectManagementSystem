public class Report {
    private int UID;
    private String txt;
    private String date;

    public Report() {
    }

    public Report(int UID, String txt, String date) {
        this.UID = UID;
        this.txt = txt;
        this.date = date;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
