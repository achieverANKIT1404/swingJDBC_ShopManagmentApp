 import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Dispaly extends JFrame implements ActionListener {

    JFrame frame1;
    JLabel l0, l1, l2;
    JComboBox c1;
    JButton b1;
   Connection con;
    ResultSet rs, rs1;
    Statement st, st1;
    PreparedStatement pst;
 //   String ids;
    static JTable table;
    String[] columnNames = {"Entry", "product", "customer"};
  //  String from;

    Dispaly() {

        l0 = new JLabel("-:GUPTA ENGENCIES:-");
        l0.setForeground(Color.red);
        l0.setFont(new Font("Serif", Font.BOLD, 19));
        l1 = new JLabel("Click on below button");
        l1.setFont(new Font("Serif", Font.BOLD, 16));
        b1 = new JButton("Show");
        b1.setFont(new Font("Serif",Font.BOLD,16));
        b1.setForeground(Color.DARK_GRAY);
        b1.setBackground(Color.ORANGE);
        b1.setBorder(null);

        l0.setBounds(80, 30, 350, 40);
        l1.setBounds(70, 110, 145, 20);
        b1.setBounds(150, 150, 90, 27);
        b1.addActionListener(this);

        setTitle("Fetching Info From shop");
        setLayout(null);
        setVisible(true);
        setSize(400, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(l0);
        add(l1);;
        add(b1);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // (1)
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop", "root", "abc123");  // (2)
            st = con.createStatement();
            rs = st.executeQuery("select * from entry");
          //  Vector v = new Vector();
//            while (rs.next()) {
//           //     ids = rs.getString(1);
//           //     v.add(ids);
//            }
            c1 = new JComboBox();
            c1.setBounds(150, 110, 150, 20);

            add(c1);
            st.close();
            rs.close();
            
        } catch (Exception e) {
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
        showTableData();
        }

    }

    public void showTableData() {

        frame1 = new JFrame("shop Search Result");
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setLayout(new BorderLayout());
//TableModel tm = new TableModel();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
//DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames());
//table = new JTable(model);
        table = new JTable();
table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
      //  from = (String) c1.getSelectedItem();
//String textvalue = textbox.getText();
        int Entry;
        String product = "";
        String customer = "";

        try {
            pst = con.prepareStatement("select * from entry");
            ResultSet rs = pst.executeQuery();
            int i = 0;
          
            while(rs.next())
            {
            	  Entry = Integer.parseInt(rs.getString(1));
                  product = rs.getString(2);
                  customer = rs.getString(3);
                 
                  model.addRow(new Object[]{Entry, product, customer});
                  i++;
          }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(400, 300);
    }

    public static void main(String args[]) {
        new Dispaly();
    }
}