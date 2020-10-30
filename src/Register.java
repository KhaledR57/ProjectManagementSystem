import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Register extends JFrame {
    private JLabel name,pass1,pass2,idlabel;
    private JTextField userName,ID;
    private JPasswordField password1,password2;
    private JRadioButton PM,TL,EMP;
    private ButtonGroup buttonGroup;
    private JButton reg_btn;
    private File file = new File("User.txt");
    public Register(String title){
        super(title);
        Font font = new Font(Font.SANS_SERIF,Font.PLAIN,15);

        setBounds(0, 0, 500,600);
        setContentPane(new JLabel(new ImageIcon("background.png")));
        setLocationRelativeTo(null);
        setLayout(new FlowLayout(FlowLayout.CENTER,50,70));

        idlabel = new JLabel("ID: ");
        idlabel.setFont(font);
        ID = new JTextField(25);

        name = new JLabel("Username: ");
        name.setFont(font);
        userName = new JTextField(20);

        pass1 = new JLabel("Password: ");
        pass1.setFont(font);
        password1 = new JPasswordField(20);

        pass2 = new JLabel("Confirm Password: ");
        pass2.setFont(font);
        password2 = new JPasswordField(20);

        buttonGroup = new ButtonGroup();

        PM = new JRadioButton("Project Manager",false);
        PM.setActionCommand("1");
        TL = new JRadioButton("Team Leader",false);
        TL.setActionCommand("2");
        EMP = new JRadioButton("Employee",true);
        EMP.setActionCommand("3");
        buttonGroup.add(PM);
        buttonGroup.add(TL);
        buttonGroup.add(EMP);

        reg_btn = new JButton("Register");
        reg_btn.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
        add(idlabel);
        add(ID);
        add(name);
        add(userName);
        add(pass1);
        add(password1);
        add(pass2);
        add(password2);
        add(PM);
        add(TL);
        add(EMP);
        add(reg_btn);

        reg_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String strPassword = new String(password1.getPassword());
                String strConfirmPassword = new String(password2.getPassword());
                String name = userName.getText();
                int id;
                int role = Integer.parseInt(buttonGroup.getSelection().getActionCommand());
                if(password1.getText().isEmpty()||userName.getText().isEmpty()||ID.getText().isEmpty()){
                    JOptionPane.showMessageDialog(getContentPane(),"Enter All Data!");
                }else if(!(strPassword.equals(strConfirmPassword))){
                    JOptionPane.showMessageDialog(getContentPane(),"Password Not Match!");
                }else{
                    try{
                        id = Integer.parseInt(ID.getText());
                        Authentication.register(id,name,strPassword,role,file);
                        JOptionPane.showMessageDialog(getContentPane(),"User has been added successfully");
//                    Admin admin = new Admin("Admin Panel");
//                    admin.setVisible(true);
                        Register.this.setVisible(false);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(getContentPane(),"ID Should Be Integer");
                    }
                }

            }
        });
    }
}
