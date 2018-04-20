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
		new LoginView();
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

		btnJoin = new JButton("회원가입");
		btnJoin.setBounds(100, 80, 90, 25);
		panel.add(btnJoin);
		btnJoin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					JoinView join = new JoinView();
					join.setVisible(true);
				}catch(Exception e1) {
					e1.printStackTrace();
				}
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
				
				//isLoginCheck();
			}
		});
	}

	/*
	public void isLoginCheck(){

		if(!userText.getText().equals(null)){

			JOptionPane.showMessageDialog(null, "Success");
			bLoginCheck = true;
			// 로그인 성공이라면 매니져창 뛰우기
			if(isLogin()){

			}
		}else{
			JOptionPane.showMessageDialog(null, "Faild");
			userText.setText("");
			passText.setText("");
		}
	}

	public boolean isLogin() {		
		return bLoginCheck;
	}


	// mainProcess와 연동
	public void setMain(MainProcess main) {
		this.main = main;
	}
	 */
}
