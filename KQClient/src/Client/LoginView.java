package Client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame{

	private UserDTO dto;
	
	private MainProcess main;
	private TestFrm testFrm;
	
	private JButton btnLogin;
	private JButton btnInit;
	private JPasswordField passText;
	private JTextField userText;
	private boolean bLoginCheck;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}

	public LoginView() {
		// setting
		setTitle("login");
		setSize(295, 144);
		setResizable(false);
		setLocation(800, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// panel
		JPanel panel = new JPanel();
		placeLoginPanel(panel);
		
		// add
		getContentPane().add(panel);
		
		// visible
		setVisible(true);
	}
	
	public void placeLoginPanel(JPanel panel){
		panel.setLayout(null);		
		JLabel userLabel = new JLabel("���̵�:");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);
		
		JLabel passLabel = new JLabel("��й�ȣ:");
		passLabel.setBounds(10, 40, 80, 25);
		panel.add(passLabel);
		
		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);
		//dto.setUserId(userText.getText());
		
		passText = new JPasswordField(20);
		passText.setBounds(100, 40, 160, 25);
		panel.add(passText);
		//dto.setUserpwd(passText.getText());
		
		passText.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();			
			}
		});
		
		btnInit = new JButton("\uCD08\uAE30\uD654");
		btnInit.setBounds(10, 80, 100, 25);
		panel.add(btnInit);
		
		btnInit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userText.setText("");
				passText.setText("");
			}
		});
		
		btnLogin = new JButton("�α���");
		btnLogin.setBounds(160, 80, 100, 25);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();
			}
		});
	}
	
	public void isLoginCheck(){
		if(userText.getText().equals("test") && new String(passText.getPassword()).equals("1234")){
			JOptionPane.showMessageDialog(null, "Success");
			bLoginCheck = true;
			
			// �α��� �����̶�� �Ŵ���â �ٿ��
			if(isLogin()){
				main.showFrameTest(); // ����â �޼ҵ带 �̿��� â�ٿ��
			}					
		}else{
			JOptionPane.showMessageDialog(null, "Faild");
		}
	}
	
	public boolean isLogin() {		
		return bLoginCheck;
	}

	
	// mainProcess�� ����
	public void setMain(MainProcess main) {
		this.main = main;
	}
}