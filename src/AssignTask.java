import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AssignTask extends JFrame {

    private JLabel tidLabel, pidLabel,tnameLabel,uidLabel;
    private JTextField tid, pid,tname,uid;
    private JButton assign_btn;

    public AssignTask(String title) {
        super(title);
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);

        setBounds(0, 0, 500, 600);
        setContentPane(new JLabel(new ImageIcon("background.png")));
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 70));

        tidLabel = new JLabel("Task ID: ");
        tidLabel.setFont(font);
        tid = new JTextField(20);

        pidLabel = new JLabel("Project ID: ");
        pidLabel.setFont(font);
        pid = new JTextField(20);

        tnameLabel = new JLabel("Task Name: ");
        tnameLabel.setFont(font);
        tname = new JTextField(20);

        uidLabel = new JLabel("Assigned for (ID): ");
        uidLabel.setFont(font);
        uid = new JTextField(20);

        assign_btn = new JButton("Assign");
        assign_btn.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));


        assign_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(tid.getText().isEmpty()) && !(uid.getText().isEmpty()) && !(pid.getText().isEmpty()) && !(tname.getText().isEmpty())) {

                    try{
                        if (!Authentication.validTID(Integer.parseInt(tid.getText())))
                            JOptionPane.showMessageDialog(getContentPane(), "Invalid Task ID");
                        else if (!Authentication.validPID(Integer.parseInt(pid.getText())))
                            JOptionPane.showMessageDialog(getContentPane(), "Invalid Project ID");
                        else if(!Authentication.validUID(Integer.parseInt(uid.getText())))
                            JOptionPane.showMessageDialog(getContentPane(), "Invalid User ID");
                        else{
                            Task.addTask(Integer.parseInt(tid.getText()),Integer.parseInt(pid.getText()),Integer.parseInt(uid.getText()),tname.getText());
                            JOptionPane.showMessageDialog(getContentPane(), "Task Has Been Add Successfully");
                            AssignTask.this.setVisible(false);
                        }
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(getContentPane(),"ID Should Be Integer");
                    }
                } else {
                    JOptionPane.showMessageDialog(getContentPane(), "You Should Enter All Data");
                }
            }
        });


        add(tidLabel);
        add(tid);
        add(pidLabel);
        add(pid);
        add(tnameLabel);
        add(tname);
        add(uidLabel);
        add(uid);
        add(assign_btn);
    }
}