
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Insert {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String url =  "jdbc:mysql://localhost:3306/shop", uname = "root", pass = "abc123";
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Registered...");
		
		Connection cn = DriverManager.getConnection(url,uname,pass);
		System.out.println("Connection succed...");
		
		JFrame f = new JFrame();
		
		JTextField t1,t2,t3;
		JLabel l,l1,l2,l3;
		JButton b1;
		
		t1 = new JTextField();
		t1.setBounds(160,60,100,27);
		t2 = new JTextField();
		t2.setBounds(160,120,100,27);
		t3 = new JTextField();
		t3.setBounds(160,180,100,27);
		
		l = new JLabel("-: GUPTA AGENCIES :-");
        l.setBounds(90,10,210,27);
        l.setForeground(Color.red);
        l.setFont(new Font("Serif", Font.BOLD, 19));
        l1 = new JLabel("Entry        : ");
        l1.setFont(new Font("Serif", Font.BOLD, 16));
        l1.setBounds(75,60,100,30);
        l2 = new JLabel("Product    : ");
        l2.setFont(new Font("Serif", Font.BOLD, 16));
        l2.setBounds(75,120,100,30);
        l3 = new JLabel("Customer : ");
        l3.setFont(new Font("Serif", Font.BOLD, 16));
        l3.setBounds(75,180,100,30);
        
        b1 = new JButton("Insert");
        b1.setFont(new Font("Serif",Font.BOLD,16));
        b1.setForeground(Color.WHITE);
        b1.setBackground(Color.BLUE);
        b1.setBounds(130,250,80,26);
        b1.setBorder(null);
        b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Statement st = cn.createStatement();
					int price = Integer.parseInt(t1.getText());
					String product = t2.getText();
					String customer = t3.getText();
					
					int a = st.executeUpdate("INSERT INTO entry VALUES("+price+",'"+product+"','"+customer+"')");
					
					if(a>0)
						System.out.println("Data inserted successfully...");
				} catch (SQLException e) {
				   
					System.out.println("Data not inserted...");
					e.printStackTrace();
				}
				
			}
		});
        
        f.add(t1);
        f.add(t2);
        f.add(t3);
        f.add(l);
        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(b1);
        f.setSize(380,350);
        f.setLayout(null);
        f.setVisible(true);
        
	}

}
