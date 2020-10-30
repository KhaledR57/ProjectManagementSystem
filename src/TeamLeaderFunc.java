import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TeamLeaderFunc extends User{

    public static ArrayList<User> viewEmployee(){
        ArrayList<User> users = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("User.txt")));
            String line;
            while ((line = reader.readLine())!=null){
                String[] split = line.split("#");
                if(Integer.parseInt(split[3])==3){
                    User user = new User(Integer.parseInt(split[0]),split[1],split[4]);
                    users.add(user);
                }
            }
            reader.close();
        }catch (IOException e){
            e.getMessage();
        }
        return users;
    }

    public static ArrayList<Report> viewReports(){
        ArrayList<Report> reports = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("Reports.txt")));
            String line;
            while ((line = reader.readLine())!=null){
                String[] split = line.split("#");
                Report report = new Report(Integer.parseInt(split[0]),split[1],split[2]);
                reports.add(report);
            }
            reader.close();
        }catch (IOException e){
            e.getMessage();
        }
        return reports;
    }

    public static ArrayList<Task> viewCompletedTasks(){
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("Task.txt")));
            String line;
            while ((line = reader.readLine())!=null){
                String[] split = line.split("#");
                if(Integer.parseInt(split[4])==1){
                    Task task = new Task(Integer.parseInt(split[0]),Integer.parseInt(split[1]),split[2]);
                    tasks.add(task);
                }
            }
            reader.close();
        }catch (IOException e){
            e.getMessage();
        }
        return tasks;
    }

    public static ArrayList<Requests> viewRequests(){
        ArrayList<Requests> requests = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("Requests.txt")));
            String line;
            while ((line = reader.readLine())!=null){
                String[] split = line.split("#");
                if(Integer.parseInt(split[2])==0){
                    Requests request = new Requests(Integer.parseInt(split[0]),split[1]);
                    requests.add(request);
                }
            }
            reader.close();
        }catch (IOException e){
            e.getMessage();
        }
        return requests;
    }

    public static void dealRequests(int UID,int r){
        File file = new File("Requests.txt");
        File temp = new File("Temp.txt");
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
            String line;
            String[] split;
            while ((line = reader.readLine())!=null){
                split = line.split("#");
                if(UID != Integer.parseInt(split[0]))
                    writer.write(line+"\n");
                else if (UID == Integer.parseInt(split[0])&&Integer.parseInt(split[2])==0)
                    writer.write(Integer.parseInt(split[0])+"#"+split[1]+"#"+r+"\n");
                else{
                    writer.write(line+"\n");
                }
            }

            reader.close();
            writer.close();
            file.delete();
            temp.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void assignPenalty(int UID,double pen){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Penalties.txt"),true));
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd HH:mm");
            writer.write(UID+"#"+pen+"#"+formatter.format(date)+"\n");
            writer.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
