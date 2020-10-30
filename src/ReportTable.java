import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReportTable extends JFrame{

    private JTable ReportTable;
    private JScrollPane scrollPane;
    private JLabel label;
    public ReportTable(String title){
        super(title);
        setContentPane(new JLabel(new ImageIcon("background.png")));
        setLayout(null);
        setBounds(0,0,515,400);
        setLocationRelativeTo(null);
        setResizable(false);

        Font fontb = new Font(Font.SANS_SERIF,Font.BOLD,30);

        label = new JLabel("Report Table");
        label.setFont(fontb);
        label.setBounds(150,15,400,50);

        String[] columnsNames = {"Employee ID","Report Desc","Report Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columnsNames, 0);
        for (int i = 0; i < TeamLeaderFunc.viewReports().size(); i++) {
            Object[] objs = {TeamLeaderFunc.viewReports().get(i).getUID(),TeamLeaderFunc.viewReports().get(i).getTxt(),TeamLeaderFunc.viewReports().get(i).getDate()};
            tableModel.addRow(objs);
        }
        ReportTable = new JTable(tableModel);
        scrollPane = new JScrollPane(ReportTable);
        scrollPane.setBounds(50,75,400,245);

        add(scrollPane);
        add(label);
    }
}