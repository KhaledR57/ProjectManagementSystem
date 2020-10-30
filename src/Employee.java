import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Employee extends JFrame {

    private JButton vacationBtn,penaltiesBtn,WHoursBtn,MoveBtn;
    private JList assignedTasksList;
    private JList finishedTasksList;
    private static ArrayList<String> tasks;
    public static int UID;
    public Employee(String title,int UID){

        super(title);
        Employee.UID = UID;
        setContentPane(new JLabel(new ImageIcon("background.png")));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,615,600);
        setLocationRelativeTo(null);


        Date dateIN = new Date();


        MoveBtn = new JButton("Done");//
        MoveBtn.setBounds(200,500,200,50);//
        vacationBtn = new JButton("Request Vacation");//
        vacationBtn.setBounds(0,0,200,50);//
        penaltiesBtn = new JButton("View Penalties");
        penaltiesBtn.setBounds(200,0,200,50);
        WHoursBtn = new JButton("Working Hours");//
        WHoursBtn.setBounds(400,0,200,50);//


        tasks = EmployeeFunc.assignedTasks(UID);
        assignedTasksList = new JList(tasks.toArray());
        JScrollPane scrollPane = new JScrollPane(assignedTasksList);
        scrollPane.setBounds(200,80,200,400);
        assignedTasksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

//        finishedTasks = new ArrayList<>();
//        finishedTasksList = new JList();
//        JScrollPane scrollPane2 = new JScrollPane(finishedTasksList);
//        scrollPane2.setBounds(395,60,200,400);


        MoveBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) assignedTasksList.getSelectedValue();
                if (assignedTasksList.getSelectedIndex()!=-1){
                    String[] split = selected.split("#");
                    EmployeeFunc.taskDone(Integer.parseInt(split[0]));
                    tasks.remove(assignedTasksList.getSelectedIndex());
                    assignedTasksList.setListData(tasks.toArray());
                }
            }
        });


        WHoursBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(getContentPane(),"Your Working Hours is: "+EmployeeFunc.getWorkingHours(UID)+" Hours");
            }
        });


        vacationBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String desc = "";
                    if(EmployeeFunc.requestVacation(UID,desc,false)==2){
                        desc = JOptionPane.showInputDialog("Description Of The Request");
                        try {
                            if(!desc.isEmpty()){
                                EmployeeFunc.requestVacation(UID,desc,true);
                                JOptionPane.showMessageDialog(getContentPane(),"Your Request Has been Sent");
                            }else
                                JOptionPane.showMessageDialog(getContentPane(),"You Should Enter Request Description");
                        }catch (Exception ex){
                            ex.getMessage();
                        }

                    }
                    else if(EmployeeFunc.requestVacation(UID,desc,false)==0)
                        JOptionPane.showMessageDialog(getContentPane(),"Your Request is Pending");
                    else if(EmployeeFunc.requestVacation(UID,desc,false)==-1)
                        JOptionPane.showMessageDialog(getContentPane(),"Your Request Has Been Rejected");
                    else if(EmployeeFunc.requestVacation(UID,desc,false)==1)
                        JOptionPane.showMessageDialog(getContentPane(),"Your Request Has Been Accepted");
                EmployeeFunc.removeRequest(UID);
            }
        });


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                Date dateOUT = new Date();
                EmployeeFunc.workingHours(UID,formatter.format(dateIN),formatter.format(dateOUT));

            }
        });

        penaltiesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PenaltiesTable("Penalties Table").setVisible(true);
            }
        });

        getContentPane().add(MoveBtn);
        getContentPane().add(vacationBtn);
        getContentPane().add(penaltiesBtn);
        getContentPane().add(WHoursBtn);
        getContentPane().add(scrollPane);
        //getContentPane().add(scrollPane2);

    }
}
