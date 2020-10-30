//import javax.swing.*;
//import javax.swing.plaf.basic.BasicBorders;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class VacationForm extends JFrame {
//    private JLabel vtitle,vdescription;
//    private JTextField descriptionTextField;
//    private JTextField titleTextField;
//    private JButton send_btn;
//    public VacationForm(String title,int UID){
//        super(title);
//        Font font = new Font(Font.SANS_SERIF,Font.PLAIN,15);
//
//        setBounds(0, 0, 500,600);
//        setContentPane(new JLabel(new ImageIcon("background.png")));
//        setLocationRelativeTo(null);
//        setLayout(new FlowLayout(FlowLayout.CENTER,50,70));
//
//        vtitle = new JLabel("Title: ");
//        vtitle.setFont(font);
//        titleTextField = new JTextField(20);
//
//        vdescription = new JLabel("Description: ");
//        vdescription.setFont(font);
//        descriptionTextField = new JTextArea(10,20);
//        descriptionTextField.setBorder(new BasicBorders.FieldBorder(Color.lightGray,Color.lightGray,Color.lightGray,Color.lightGray));
//
//        send_btn = new JButton("Send");
//        send_btn.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
//
//
//        send_btn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(!(titleTextField.getText().isEmpty())&&!(descriptionTextField.getText().isEmpty())){
//                    EmployeeFunc.requestVacation(UID,titleTextField.getText(),descriptionTextField.getText());
//                    JOptionPane.showMessageDialog(getContentPane(),"Done!");
//                    titleTextField.setText("");
//                    descriptionTextField.setText("");
//                }else {
//                    JOptionPane.showMessageDialog(getContentPane(),"You Should Enter All Data");
//                }
//            }
//        });
//
//
//        add(vtitle);
//        add(titleTextField);
//        add(vdescription);
//        add(descriptionTextField);
//        add(send_btn);
//
//    }
//}
