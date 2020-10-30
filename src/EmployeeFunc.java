import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class EmployeeFunc extends User{


    public static void workingHours(int UID,String timein,String timeout){
        float difference;
        try {
            File file = new File("User.txt");
            File temp = new File("Temp.txt");
            Date date1 = new SimpleDateFormat("HH:mm").parse(timein);
            Date date2 = new SimpleDateFormat("HH:mm").parse(timeout);
            difference = date2.getTime() - date1.getTime();
            BufferedWriter writer = new BufferedWriter(new FileWriter(temp));
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            String[] split;
            while ((line = reader.readLine())!=null){
                split = line.split("#");
                if(UID != Integer.parseInt(split[0])){
                    writer.write(line+"\n");
                }else {
                    writer.write(split[0]+"#"+split[1]+"#"+split[2]+"#"+split[3]+"#"+(EmployeeFunc.getWorkingHours(UID)+(difference/3600000))+"\n");
                }
            }
            reader.close();
            writer.close();
            file.delete();
            temp.renameTo(file);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    public static float getWorkingHours(int UID){
        float WH = 0;
        File file = new File("User.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            String[] split;
            while ((line = reader.readLine())!=null){
                split = line.split("#");
                if(UID == Integer.parseInt(split[0])){
                    WH = Float.parseFloat(split[4]);
                }
            }
            reader.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return (float) (Math.floor(WH*100)/100);
    }


    public static int requestVacation(int UID,String desc,boolean bool){
        int b = 2;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("Requests.txt")));
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File("Requests.txt"),true));
            String line;
            String[] split;
            while ((line = reader.readLine())!=null){
                split = line.split("#");
                if(UID == Integer.parseInt(split[0]))
                    b = Integer.parseInt(split[2]);

            }
            if(b==2&&bool)
            writer.write(UID+"#"+desc+"#"+"0"+"\n");
            reader.close();
            writer.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return b;
    }

    public static void removeRequest(int UID){
        try {
            File file = new File("Requests.txt");
            File temp = new File("Temp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(temp));

            String line;
            String[] split;
            while ((line = reader.readLine())!=null){
                split = line.split("#");
                if(UID != Integer.parseInt(split[0]))
                    writer.write(line+"\n");
                else if (UID == Integer.parseInt(split[0])&&Integer.parseInt(split[2])==0)
                    writer.write(line+"\n");
            }

            reader.close();
            writer.close();
            file.delete();
            temp.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<String> assignedTasks(int UID){
        ArrayList<String> tasks = new ArrayList<>();
        try {
        BufferedReader BR = new BufferedReader(new FileReader(new File("Task.txt")));
        String line;
        while ((line = BR.readLine())!=null){
            String[] split = line.split("#");
            if(UID == Integer.parseInt(split[3])&&Integer.parseInt(split[4])==0){
                tasks.add(split[0]+"#"+split[2]);
            }
        }
        BR.close();
    }catch (IOException e){
        e.getMessage();
    }
        return tasks;
    }


    public static void taskDone(int TID){
        try {
            File tempFile = new File("Temp.txt");
            File file = new File("Task.txt");
            BufferedReader BR = new BufferedReader(new FileReader(file));
            BufferedWriter BW = new BufferedWriter(new FileWriter(tempFile));
            String line;
            while ((line = BR.readLine())!=null){
                String[] split = line.split("#");
                if(TID != Integer.parseInt(split[0])){
                    BW.write(line+"\n");
                }else if(TID == Integer.parseInt(split[0])&&Integer.parseInt(split[4])==0){
                    BW.write(split[0]+"#"+split[1]+"#"+split[2]+"#"+split[3]+"#"+"1"+"\n");
                }
            }
            BR.close();
            BW.close();
            file.delete();
            tempFile.renameTo(file);
        }catch (IOException e){
            e.getMessage();
        }
    }

    public static ArrayList<Penalty> viewPenalies(int UID){
        ArrayList<Penalty> penalties = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("Penalties.txt")));
            String line;
            while ((line = reader.readLine())!=null){
                String[] split = line.split("#");
                if(UID == Integer.parseInt(split[0])){
                   Penalty penalty = new Penalty(Double.parseDouble(split[1]),split[2]);
                   penalties.add(penalty);
                }
            }
            reader.close();
    }catch (IOException e){
            e.getMessage();
        }
        return penalties;
    }

}
