import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectManager extends JFrame {
    private JButton reportBtn,addBtn;
    private JScrollPane scrollPane,scrollPane2;
    private JLabel label,idLabel,reportLabel,pidLabel,pNameLabel;
    private JTextField id,report,pid,pName;
    private JTable projectsTable,usersTable;
    public ProjectManager(String title){

        super(title);
        setContentPane(new JLabel(new ImageIcon("background.png")));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,815,600);
        setResizable(false);
        setLocationRelativeTo(null);
        Font font = new Font(Font.SANS_SERIF,Font.TYPE1_FONT,15);
        Font fontb = new Font(Font.SANS_SERIF,Font.BOLD,30);

        reportBtn = new JButton("Report Employee");
        reportBtn.setBounds(110,500,200,50);

        addBtn = new JButton("Add Project");
        addBtn.setBounds(500,500,200,50);


        label = new JLabel("Project Manager Panel");
        label.setFont(fontb);
        label.setBounds(220,15,400,50);


        String[] columnsNames = {"Project ID","Project Name","Percentage of Completion"};
        DefaultTableModel tableModel = new DefaultTableModel(columnsNames, 0);
        for (int i = 0; i < Project.viewProjectsObj().size(); i++) {
            Object[] objs = {Project.viewProjectsObj().get(i).getID(),Project.viewProjectsObj().get(i).getPName(),ProjectManagerFunc.percentageOfCompletion(Project.viewProjectsObj().get(i).getID())+"%"};
            tableModel.addRow(objs);
        }
        projectsTable = new JTable(tableModel);
        scrollPane = new JScrollPane(projectsTable);
        scrollPane.setBounds(50,75,350,245);


        columnsNames = new String[]{"Employee ID", "Employee Name", "Working Hours"};
        DefaultTableModel tableModel2 = new DefaultTableModel(columnsNames,0);
        for (int i = 0; i < ProjectManagerFunc.viewEmployee().size(); i++) {
            Object[] objects = {ProjectManagerFunc.viewEmployee().get(i).getID(),ProjectManagerFunc.viewEmployee().get(i).getName(),ProjectManagerFunc.viewEmployee().get(i).getWH()};
            tableModel2.addRow(objects);
        }
        usersTable = new JTable(tableModel2);
        scrollPane2 = new JScrollPane(usersTable);
        scrollPane2.setBounds(430,75,350,245);

        idLabel = new JLabel("Employee ID :");
        id = new JTextField(10);
        idLabel.setBounds(10,360,100,25);
        idLabel.setFont(font);
        id.setBounds(110,360,200,20);

        reportLabel = new JLabel("Report :");
        report = new JTextField(20);
        reportLabel.setBounds(50,410,100,25);
        reportLabel.setFont(font);
        report.setBounds(110,413,200,20);


        pidLabel = new JLabel("Project ID :");
        pid = new JTextField(20);
        pidLabel.setBounds(400,360,100,25);
        pidLabel.setFont(font);
        pid.setBounds(510,360,200,20);

        pNameLabel = new JLabel("Project Name :");
        pName = new JTextField(20);
        pNameLabel.setBounds(390,410,150,25);
        pNameLabel.setFont(font);
        pName.setBounds(510,413,200,20);


        add(label);
        add(reportBtn);
        add(scrollPane);
        add(scrollPane2);
        add(report);
        add(reportLabel);
        add(id);
        add(idLabel);
        add(addBtn);
        add(pName);
        add(pNameLabel);
        add(pid);
        add(pidLabel);

        reportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (!(id.getText().isEmpty()) && !(report.getText().isEmpty())) {
                        if (!Authentication.validUID(Integer.parseInt(id.getText())))
                            JOptionPane.showMessageDialog(getContentPane(), "Invalid Employee ID");
                        else {
                            ProjectManagerFunc.report(Integer.parseInt(id.getText()),report.getText());
                            JOptionPane.showMessageDialog(getContentPane(), "Employee Has Been Reported");
                        }
                    }else {
                        JOptionPane.showMessageDialog(getContentPane(), "You Should Enter All Data");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(getContentPane(),"ID Should Be Integer");
                }
            }
        });


        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if(!Authentication.validPID(Integer.parseInt(pid.getText()))){
                        Project.addProject(Integer.parseInt(pid.getText()),pName.getText());
                        JOptionPane.showMessageDialog(getContentPane(),"Project Has Been Added Successfully!");
                    }else {
                        JOptionPane.showMessageDialog(getContentPane(),"Invalid ID!");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(getContentPane(),"ID Should Be Integer");
                }
            }
        });
    }
}
