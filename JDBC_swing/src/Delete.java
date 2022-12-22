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

public class Delete {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String url = "jdbc:mysql://localhost:3306/shop", uname = "root", pass = "abc123";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver Registered...");

		Connection cn = DriverManager.getConnection(url,uname,pass);
		System.out.println("Connection Succed...");
		
		JFrame f = new JFrame();
		
		JTextField t1;
		JLabel l,l1;
		JButton b1;
		
		t1 = new JTextField();
		t1.setBounds(130,100,100,27);
		
		l = new JLabel("-: GUPTA AGENCIES :-");
		l.setBounds(50,30,200,30);
		l.setForeground(Color.red);
        l.setFont(new Font("Serif", Font.BOLD, 18));
		l1 = new JLabel("Entry     : ");
	    l1.setFont(new Font("Serif", Font.BOLD, 16));
	    l1.setBounds(65,100,100,30);
	        
	        b1 = new JButton("Delete");
	        b1.setFont(new Font("Serif",Font.BOLD,16));
	        b1.setForeground(Color.WHITE);
	        b1.setBackground(Color.RED);
	        b1.setBounds(100,180,80,26);
	        b1.setBorder(null);
	        b1.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						Statement st = cn.createStatement();
						int entry = Integer.parseInt(t1.getText());
						
						int a = st.executeUpdate("delete from entry where entry = ' "+entry+ " ' ");
						
						if(a>0)
							System.out.println("Data deleted succesfully...");
					} catch (SQLException e) {
						
						System.out.println("Data not deleted...");
						e.printStackTrace();
					}
					
				}
			});
	        
	        f.add(t1);
	        f.add(l);
	        f.add(l1);
	        f.add(b1);
	        f.setSize(300,300);
	        f.setLayout(null);
	        f.setVisible(true);
	}

}
