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

	private MainProcess main = null;

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

		passText = new JPasswordField(20);
		passText.setBounds(100, 40, 160, 25);
		panel.add(passText);

		btnInit = new JButton("\uCD08\uAE30\uD654");
		btnInit.setBounds(10, 80, 75, 25);
		panel.add(btnInit);
		btnInit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userText.setText("");
				passText.setText("");
			}
		});
		
		JButton button_join = new JButton("ȸ������");
		button_join.setBounds(100, 80, 90, 25);
		panel.add(button_join);
		button_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new JoinView();
			}
		});

		btnLogin = new JButton("�α���");
		btnLogin.setBounds(205, 80, 75, 25);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				main = new MainProcess();

				dto.setUserId(userText.getText());
				dto.setUserpwd(passText.getText());
				
				main.LoginBack(dto);
			
				if(dto.isLogincheck()) {			//�α��� ����
					new MainMenu(main);
					dispose();
				}else if(dto.getLcheck() == 2) {		//���̵� �Է��� �߸� �Ǿ��� ��
					JOptionPane.showMessageDialog(null, "���̵� �������� �ʽ��ϴ�.");
					userText.setText("");
					passText.setText("");
				}else if(dto.getLcheck() == 3) {		//��й�ȣ�� Ʋ���� ��
					JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ�Ƚ��ϴ�.");
					passText.setText("");
				}else {

				}
			}
		});
	}
}
