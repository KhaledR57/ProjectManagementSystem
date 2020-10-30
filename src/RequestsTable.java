import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class RequestsTable extends JFrame {

    private JButton acceptBtn,rejectBtn;
    private JTable requestsTable;
    private static ArrayList<String> requests;
    private JLabel label;
    public RequestsTable(String title){

        super(title);
        setContentPane(new JLabel(new ImageIcon("background.png")));
        setLayout(null);
        setBounds(0,0,615,600);
        setLocationRelativeTo(null);
        setResizable(false);

        Font fontb = new Font(Font.SANS_SERIF,Font.BOLD,30);


        label = new JLabel("Requests");
        label.setFont(fontb);
        label.setBounds(240,15,400,50);


        acceptBtn = new JButton("Accept");
        acceptBtn.setBounds(100,500,150,50);

        rejectBtn = new JButton("Reject");
        rejectBtn.setBounds(350,500,150,50);



        String[] columnsNames = {"Employee ID","Description"};
        DefaultTableModel tableModel = new DefaultTableModel(columnsNames, 0);
        for (int i = 0; i < TeamLeaderFunc.viewRequests().size(); i++) {
            Object[] objs = {TeamLeaderFunc.viewRequests().get(i).getUID(),TeamLeaderFunc.viewRequests().get(i).getDesc()};
            tableModel.addRow(objs);
        }
        requestsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(requestsTable);
        scrollPane.setBounds(100,100,400,350);



        acceptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int UID = -1;
                try{
                    UID = (int) requestsTable.getValueAt(requestsTable.getSelectedRow(), 0);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(getContentPane(),"You Should Select Request First");
                }
                if (UID!=-1){
                    TeamLeaderFunc.dealRequests(UID,1);
                    JOptionPane.showMessageDialog(getContentPane(),"Request Has Been Accepted");
                }
            }
        });


        rejectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int UID = -1;
                try{
                    UID = (int) requestsTable.getValueAt(requestsTable.getSelectedRow(), 0);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(getContentPane(),"You Should Select Request First");
                }
                if (UID!=-1){
                    TeamLeaderFunc.dealRequests(UID,-1);
                    JOptionPane.showMessageDialog(getContentPane(),"Request Has Been Rejected");
                }
            }
        });


        getContentPane().add(acceptBtn);
        getContentPane().add(rejectBtn);
        getContentPane().add(scrollPane);
        getContentPane().add(label);

    }
}
