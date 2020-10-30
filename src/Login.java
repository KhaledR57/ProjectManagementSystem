import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Login extends JFrame{
    private JLabel name,pass;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton login_btn;
    private String uName,uPass;
    private File file = new File("User.txt");
    public Login(String title){
        super(title);
        Font font = new Font(Font.SANS_SERIF,Font.PLAIN,15);
        setBounds(0, 0, 500,250);
        setContentPane(new JLabel(new ImageIcon("background.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER,45,30));
        //setSize(500,250);
        pass = new JLabel("Password: ");
        pass.setFont(font);
        name = new JLabel("Username: ");
        name.setFont(font);
        login_btn = new JButton("Login");
        login_btn.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        textField = new JTextField(20);
        passwordField = new JPasswordField(20);
        add(name);
        add(textField);
        add(pass);
        add(passwordField);
        add(login_btn);
        this.setVisible(true);
        login_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uName = textField.getText();
                uPass = passwordField.getText();
                int role = Authentication.login(uName,uPass,file);
                if(role==3){
                    new Employee("Employee Panel",Authentication.getUID()).setVisible(true);
                    Login.this.setVisible(false);
                }else if(role==2){
                    new TeamLeader("Team Leader Panel").setVisible(true);
                    Login.this.setVisible(false);
                }else if(role==1){
                    new ProjectManager("Project Manager").setVisible(true);
                    Login.this.setVisible(false);
                }else if(role==0){
                    new Admin("Admin Panel").setVisible(true);
                    Login.this.setVisible(false);
                }
                else {
                    JOptionPane.showMessageDialog(getContentPane(),"Username or Password is not correct");
                }
            }

        });

    }
}
