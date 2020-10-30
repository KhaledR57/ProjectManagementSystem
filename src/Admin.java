import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Admin extends JFrame {
    private JButton add,delete,update;
    private JList projects;
    private JScrollPane scrollPane;
    private File file = new File("User.txt");
    public Admin(String title){
        super(title);

        Font font = new Font(Font.SANS_SERIF,Font.PLAIN,15);
        setContentPane(new JLabel(new ImageIcon("background.png")));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0,0,615,600);
        setLocationRelativeTo(null);

        projects = new JList(Project.viewProjects().toArray());
        projects.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        projects.setFont(font);
        scrollPane = new JScrollPane(projects);
        scrollPane.setBounds(200,60,200,400);


        add = new JButton("Add User");
        add.setBounds(0,0,200,50);
        delete = new JButton("Delete User");
        delete.setBounds(200,0,200,50);
        update = new JButton("Update User");
        update.setBounds(400,0,200,50);


        add(add);
        add(delete);
        add(update);
        add(scrollPane);
        this.setVisible(true);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Register register = new Register("Add User");
                register.setVisible(true);
            }
        });

        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog("User ID");
                int ID;
                try {
                    if(!(id.isEmpty())) {
                        ID = Integer.parseInt(id);
                        if(Authentication.delete(ID,file))
                            JOptionPane.showMessageDialog(getContentPane(),"User has been deleted successfully");
                        else
                            JOptionPane.showMessageDialog(getContentPane(),"User not found!","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }catch (Exception ex){
                    ex.getMessage();
                }
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Update update = new Update("Update User");
                update.setVisible(true);
            }
        });
    }
}
