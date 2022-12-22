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

public class Update {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		String url =  "jdbc:mysql://localhost:3306/shop", uname = "root", pass = "abc123";
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Registered...");
		
		Connection cn = DriverManager.getConnection(url,uname,pass);
		System.out.println("Connection succed...");
		
		JFrame f = new JFrame();
		
		JTextField t1,t2;
		JLabel l,l1,l2;
		JButton b1;
		
		t1 = new JTextField();
		t1.setBounds(160,80,100,27);
		t2 = new JTextField();
		t2.setBounds(160,140,100,27);
		
		l = new JLabel("-: GUPTA AGENCIES :-");
        l.setBounds(90,10,210,27);
    	l.setForeground(Color.red);
        l.setFont(new Font("Serif", Font.BOLD, 19));
        l1 = new JLabel("Entry         : ");
        l1.setFont(new Font("Serif", Font.BOLD, 16));
        l1.setBounds(75,80,100,30);
        l2 = new JLabel("Customer  : ");
        l2.setFont(new Font("Serif", Font.BOLD, 16));
        l2.setBounds(75,140,100,30);
        
        
        b1 = new JButton("Update");
        b1.setFont(new Font("Serif",Font.BOLD,16));
        b1.setForeground(Color.BLACK);
        b1.setBackground(Color.GREEN);
        b1.setBounds(140,220,90,26);
        b1.setBorder(null);
        
        b1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Statement st = cn.createStatement();
					String customer = t2.getText();
					int entry = Integer.parseInt(t1.getText());
				
					int a = st.executeUpdate("update entry set customer = ' "+customer+" '  where entry =  "+entry+"  ");
					
					if(a>0)
						System.out.println("Data updated succesfully...");
				} catch (SQLException e) {
					
					System.out.println("Data not updated...");
					e.printStackTrace();
				}
				
			}
		});
        
        f.add(t1);
        f.add(t2);
        f.add(l);
        f.add(l1);
        f.add(l2);
        f.add(b1);
        f.setSize(380,350);
        f.setLayout(null);
        f.setVisible(true);

	}

}
