package inTellee;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsername;
	private JTextField tfPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("User Name");
		lblUsername.setBounds(97, 72, 68, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(97, 115, 54, 14);
		contentPane.add(lblPassword);
		
		tfUsername = new JTextField();
		tfUsername.setBounds(188, 69, 86, 20);
		contentPane.add(tfUsername);
		tfUsername.setColumns(10);
		
		tfPassword = new JTextField();
		tfPassword.setBounds(188, 112, 86, 20);
		contentPane.add(tfPassword);
		tfPassword.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/inTellee","root","");
					java.sql.Statement stmt=conn.createStatement();
					java.sql.Statement stmt2=conn.createStatement();
					
					String sqlstmt="Select * from userinfo where Username='"+tfUsername.getText()+"' and Password='"+tfPassword.getText()+"'";
					ResultSet rs=stmt.executeQuery(sqlstmt);
					
					if(rs.next())
					{
						//UserPage n=new UserPage();
						Desktop d= Desktop.getDesktop();
						
						
						d.browse(new URI("https://github.com/" + tfUsername.getText()));
						
						
					}
					else
						JOptionPane.showMessageDialog(null,"Login Failed..");
					
					
					conn.close();

				}
					catch(Exception e){
						
						System.out.println(e.toString());
					}
			}
		});
		btnSubmit.setBounds(114, 171, 89, 23);
		contentPane.add(btnSubmit);
	}
}
