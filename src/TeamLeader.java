import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamLeader extends JFrame {
    private JButton penalitiesBtn,assignTasksBtn,requestsBtn,reportsBtn;
    private JScrollPane scrollPane,scrollPane2;
    private JLabel label;
    private JTable tasksTable,usersTable;
    public TeamLeader(String title){

        super(title);
        setContentPane(new JLabel(new ImageIcon("background.png")));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,815,600);
        setLocationRelativeTo(null);
        setResizable(false);
//        Font font = new Font(Font.SANS_SERIF,Font.PLAIN,15);
//        Font fontb = new Font(Font.SANS_SERIF,Font.BOLD,16);

        Font fontb = new Font(Font.SANS_SERIF,Font.BOLD,30);

        label = new JLabel("Team Leader Panel");
        label.setFont(fontb);
        label.setBounds(250,15,400,50);

        penalitiesBtn = new JButton("Assign Penalties");
        penalitiesBtn.setBounds(5,500,200,50);

        assignTasksBtn = new JButton("Assign Task");
        assignTasksBtn.setBounds(205,500,200,50);

        requestsBtn = new JButton("View Requests");
        requestsBtn.setBounds(405,500,200,50);

        reportsBtn = new JButton("View Reports");
        reportsBtn.setBounds(605,500,200,50);

        String[] columnsNames = {"Task ID","Project ID","Task Name"};
        DefaultTableModel tableModel = new DefaultTableModel(columnsNames, 0);
        for (int i = 0; i < TeamLeaderFunc.viewCompletedTasks().size(); i++) {
            Object[] objs = {TeamLeaderFunc.viewCompletedTasks().get(i).getTID(),TeamLeaderFunc.viewCompletedTasks().get(i).getPID(),TeamLeaderFunc.viewCompletedTasks().get(i).getTName()};
            tableModel.addRow(objs);
        }
        tasksTable = new JTable(tableModel);
        scrollPane = new JScrollPane(tasksTable);
        scrollPane.setBounds(40,120,350,245);


        columnsNames = new String[]{"Employee ID", "Employee Name", "Working Hours"};
        DefaultTableModel tableModel2 = new DefaultTableModel(columnsNames,0);
        for (int i = 0; i < TeamLeaderFunc.viewEmployee().size(); i++) {
            Object[] objects = {TeamLeaderFunc.viewEmployee().get(i).getID(),TeamLeaderFunc.viewEmployee().get(i).getName(),TeamLeaderFunc.viewEmployee().get(i).getWH()};
            tableModel2.addRow(objects);
        }
        usersTable = new JTable(tableModel2);
        scrollPane2 = new JScrollPane(usersTable);
        scrollPane2.setBounds(430,120,350,245);


        add(penalitiesBtn);
        add(assignTasksBtn);
        add(requestsBtn);
        add(reportsBtn);
        add(label);
        add(scrollPane);
        add(scrollPane2);

        assignTasksBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AssignTask("Assign Task").setVisible(true);
            }
        });

        penalitiesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int EID = Integer.parseInt(JOptionPane.showInputDialog("Enter Employee ID"));
                    double penalty = Double.parseDouble(JOptionPane.showInputDialog("Enter Penalty"));
                    if(Authentication.validUID(EID)&&penalty>0){
                        TeamLeaderFunc.assignPenalty(EID,penalty);
                        JOptionPane.showMessageDialog(getContentPane(),"Penalty Has Been Added Successfully!");
                    }else if (!Authentication.validUID(EID)){
                        JOptionPane.showMessageDialog(getContentPane(),"Invalid ID");
                    }else {
                        JOptionPane.showMessageDialog(getContentPane(),"Try Again");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(getContentPane(),"Penalty Should Be Number");
                }

            }
        });

        reportsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ReportTable("Report Table").setVisible(true);
            }
        });

        requestsBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RequestsTable("Requests Table").setVisible(true);
            }
        });
    }
}
