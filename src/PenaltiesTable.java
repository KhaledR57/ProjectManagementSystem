import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
        import java.awt.*;

public class PenaltiesTable extends JFrame {
    private JTable PenaltiesTable;
    private JScrollPane scrollPane;
    private JLabel label;
    public PenaltiesTable(String title){
        super(title);
        setContentPane(new JLabel(new ImageIcon("background.png")));
        setLayout(null);
        setBounds(0,0,515,400);
        setLocationRelativeTo(null);
        setResizable(false);

        Font fontb = new Font(Font.SANS_SERIF,Font.BOLD,30);

        label = new JLabel("Penalties Table");
        label.setFont(fontb);
        label.setBounds(150,15,400,50);

        String[] columnsNames = {"Penalties","Penalties Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columnsNames, 0);
        for (int i = 0; i < EmployeeFunc.viewPenalies(Employee.UID).size(); i++) {
            Object[] objs = {"-"+EmployeeFunc.viewPenalies(Employee.UID).get(i).getPen()+"$",EmployeeFunc.viewPenalies(Employee.UID).get(i).getDate()};
            tableModel.addRow(objs);
        }
        PenaltiesTable = new JTable(tableModel);
        scrollPane = new JScrollPane(PenaltiesTable);
        scrollPane.setBounds(50,75,400,245);

        add(scrollPane);
        add(label);
    }
}
