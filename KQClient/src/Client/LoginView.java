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

	private UserDTO dto = new UserDTO();

	private MainProcess main = new MainProcess();

	private JButton btnLogin;
	private JButton btnJoin;
	private JButton btnInit;

	private JTextField userText;
	private JPasswordField passText;
	private boolean bLoginCheck;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView loginView = new LoginView();
					loginView.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LoginView() {
		// setting
		setTitle("login");
		setSize(300, 150);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// panel
		JPanel panel = new JPanel();
		placeLoginPanel(panel);

		// add
		getContentPane().add(panel);

		// visible
		setVisible(true);

		main.LoginGui(this);
	}

	public void placeLoginPanel(JPanel panel){
		panel.setLayout(null);		
		JLabel userLabel = new JLabel("아이디:");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		JLabel passLabel = new JLabel("비밀번호:");
		passLabel.setBounds(10, 40, 80, 25);
		panel.add(passLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		passText = new JPasswordField(20);
		passText.setBounds(100, 40, 160, 25);
		panel.add(passText);

		btnInit = new JButton("\uCD08\uAE30\uD654");
		btnInit.setBounds(10, 80, 80, 25);
		panel.add(btnInit);
		btnInit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userText.setText("");
				passText.setText("");
			}
		});


		btnLogin = new JButton("로그인");
		btnLogin.setBounds(200, 80, 80, 25);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				dto.setUserId(userText.getText());
				dto.setUserpwd(passText.getText());

				main.backgroundClient(dto);
			
				if(dto.isLogincheck()) {			//로그인 승인이 되면
					JOptionPane.showMessageDialog(null, "Success");
					//게임창을 띄운다
					new InitialGame.Quiz();
					dispose();
				}else if(dto.getCheck() == 2) {		//아이디 입력이 잘못 되었을 때
					JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다.");
					userText.setText("");
					passText.setText("");
				}else if(dto.getCheck() == 3) {		//비밀번호가 틀렸을 때
					JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
					passText.setText("");
				}else {

				}
			}
		});
	}
}
