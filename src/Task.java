import java.io.*;
import java.util.ArrayList;

public class Task {
    private int TID;
    private int PID;
    private String TName;


    public Task(int TID,int PID,String TName) {
        this.TID = TID;
        this.TName = TName;
        this.PID = PID;

    }

    public int getTID() {
        return TID;
    }

    public int getPID() {
        return PID;
    }

    public String getTName() {
        return TName;
    }

    public static void addTask(int TID, int PID, int UID, String TName){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Task.txt"),true));
            writer.write(TID+"#"+PID+"#"+TName+"#"+UID+"#"+"0"+"\n");
            writer.close();
        }catch (IOException e){
            e.getMessage();
        }
    }

    public static ArrayList<Task> viewTasks(int PID, int UID){
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("Task.txt")));
            String line;
            while ((line = reader.readLine())!=null){
                String[] split = line.split("#");
                if(PID == Integer.parseInt(split[1])&&UID == Integer.parseInt(split[3])&&Integer.parseInt(split[4])==0){
                    Task task = new Task(Integer.parseInt(split[0]),Integer.parseInt(split[1]),split[2]);
                    tasks.add(task);
                }
            }
        }catch (IOException e){
            e.getMessage();
        }
        return tasks;
    }

    public static ArrayList<Task> viewCompletedTasks(int PID){
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("Task.txt")));
            String line;
            while ((line = reader.readLine())!=null){
                String[] split = line.split("#");
                if(PID == Integer.parseInt(split[1])&&Integer.parseInt(split[4])==1){
                    Task task = new Task(Integer.parseInt(split[0]),Integer.parseInt(split[1]),split[2]);
                    tasks.add(task);
                }
            }
        }catch (IOException e){
            e.getMessage();
        }
        return tasks;
    }




}