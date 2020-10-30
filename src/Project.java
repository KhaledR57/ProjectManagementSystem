import java.io.*;
import java.util.ArrayList;

public class Project {

    private int PID;
    private String PName;
    private double percentage;

    public Project(){
    }

    public Project(int PID, String PName) {
        this.PID = PID;
        this.PName = PName;

    }

    public Project(int PID, String PName, double percentage) {
        this.PID = PID;
        this.PName = PName;
        this.percentage = percentage;
    }

    public int getID() {
        return PID;
    }

    public void setID(int ID) {
        this.PID = ID;
    }

    public String getPName() {
        return PName;
    }

    public void setPName(String PName) {
        this.PName = PName;
    }

    public double getPercentage() {
        return percentage;
    }

    public static void addProject(int PID, String PName){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Project.txt"),true));
            writer.write(PID+"#"+PName+"#"+"0.0"+"\n");
            writer.close();
        }catch (IOException e){
            e.getMessage();
        }
    }

    public static ArrayList<Project> viewProjectsObj(){
        ArrayList<Project> projects = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("Project.txt")));
            String line;
            while ((line = reader.readLine())!=null){
                String[] split = line.split("#");
                    Project project = new Project(Integer.parseInt(split[0]),split[1],Double.parseDouble(split[2]));
                    projects.add(project);
            }
            reader.close();
        }catch (IOException e){
            e.getMessage();
        }
        return projects;
    }

    public static ArrayList<String> viewProjects(){
        ArrayList<String> projects = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("Project.txt")));
            String line;
            while ((line = reader.readLine())!=null){
                String[] split = line.split("#");
                projects.add(split[0]+" # "+split[1]);
            }
            reader.close();
        }catch (IOException e){
            e.getMessage();
        }
        return projects;
    }

}
