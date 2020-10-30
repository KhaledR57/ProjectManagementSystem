import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProjectManagerFunc extends User{

    public static int percentageOfCompletion(int PID){
        double done = 0;
        double all = 0;
        try{
            BufferedReader reader = new BufferedReader(new FileReader(new File("Task.txt")));
            String line;
            while ((line = reader.readLine())!=null){
                String[] split = line.split("#");
                if (PID == Integer.parseInt(split[1])){
                    all++;
                }
                if (PID == Integer.parseInt(split[1])&&Integer.parseInt(split[4])==1){
                    done++;
                }
            }
            reader.close();
        }catch (IOException e) {
            e.getMessage();
        }
        return (int)Math.floor((done/all)*100);
    }



    public static void report(int UID,String report){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Reports.txt"),true));
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd HH:mm");
            writer.write(UID+"#"+report+"#"+formatter.format(date)+"\n");
            writer.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

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
}
