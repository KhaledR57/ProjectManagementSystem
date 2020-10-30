import java.io.*;

public class Authentication {

    private static int UID = -1;

    public static int login(String userName,String password,File file){
        try {
            BufferedReader BR = new BufferedReader(new FileReader(file));
            String line;
            while ((line = BR.readLine())!=null){
                User user = new User();
                String[] split = line.split("#");
                user.setName(split[1]);
                user.setPassword(split[2]);
                if(user.getName().equals(userName)&&user.getPassword().equals(password)){
                    UID = Integer.parseInt(split[0]);
                    BR.close();
                    return Integer.parseInt(split[3]);
                }

            }
            BR.close();
        }catch (IOException e){
            System.out.println(""+e.getMessage());
        }
        return -1;
    }


    public static void register(int ID, String userName, String password,int role, File file){
        try{
            BufferedWriter BW = new BufferedWriter(new FileWriter(file,true));
            BW.write(ID+"#"+userName+"#"+password+"#"+role+"#"+"0.0"+"\n");
            BW.close();
        }catch (IOException e){
            System.out.println(""+e.getMessage());
        }

    }

    public static boolean delete(int ID,File file){
        boolean d = false;
        try{
            File tempFile = new File("TempFile.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String line;
            String[] split;
            while ((line = reader.readLine())!=null){
                split = line.split("#");
                if(ID != Integer.parseInt(split[0])){
                    writer.write(line+"\n");
                }else {
                    d = true;
                }
            }
            reader.close();
            writer.close();
            file.delete();
            tempFile.renameTo(file);
        }catch (IOException e){
            System.out.println(""+e);
        }
        return d;
    }

    public static boolean update(int ID, String userName, String password,int role,File file){
        boolean d = false;
        try{
            File tempFile = new File("temp.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String line;
            while ((line = reader.readLine())!=null){
                String[] split = line.split("#");
                if(ID != Integer.parseInt(split[0])){
                    writer.write(line+"\n");
                }else {
                    writer.write(ID+"#"+userName+"#"+password+"#"+role+"#"+split[4]+"\n");
                    d = true;
                }
            }
            reader.close();
            writer.close();
            file.delete();
            tempFile.renameTo(file);
        }catch (IOException e){
            System.out.println(""+e);
        }
        return d;
    }

    public static boolean validPID(int PID){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("Project.txt")));
            String line;
            while ((line = reader.readLine())!=null){
                String[] split = line.split("#");
                if(PID == Integer.parseInt(split[0]))
                    return true;
            }
        }catch (IOException e){
            e.getMessage();
        }
        return false;
    }


    public static boolean validUID(int UID){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("User.txt")));
            String line;
            while ((line = reader.readLine())!=null){
                String[] split = line.split("#");
                if(UID == Integer.parseInt(split[0])&&Integer.parseInt(split[3])==3)
                    return true;
            }
        }catch (IOException e){
            e.getMessage();
        }
        return false;
    }


    public static boolean validTID(int TID){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("Task.txt")));
            String line;
            while ((line = reader.readLine())!=null){
                String[] split = line.split("#");
                if(TID == Integer.parseInt(split[0]))
                    return false;
            }
        }catch (IOException e){
            e.getMessage();
        }
        return true;
    }

    public static int getUID() {
        return UID;
    }
}
