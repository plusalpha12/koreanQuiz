package Client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.Font;

public class JoinView extends JFrame {

	private JPanel contentPane;
	private JTextField newUserid;
	private JTextField newUsername;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnNewButton;
	private JoinProcess join = null;
	private UserDTO dto = new UserDTO();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinView joinview = new JoinView();
					joinview.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public JoinView() {
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setResizable(false);
		dto = new UserDTO();
		
		setTitle("Join");
		setSize(350, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 370, 350);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("���̵�");
		label.setFont(new Font("�޸�����ü", Font.PLAIN, 15));
		label.setBounds(60, 50, 60, 20);
		panel.add(label);
		JLabel label_1 = new JLabel("��й�ȣ");
		label_1.setFont(new Font("�޸�����ü", Font.PLAIN, 13));
		label_1.setBounds(60, 80, 60, 20);
		panel.add(label_1);
		JLabel label_2 = new JLabel("��й�ȣ Ȯ��");
		label_2.setFont(new Font("�޸�����ü", Font.PLAIN, 13));
		label_2.setBounds(60, 110, 90, 20);
		panel.add(label_2);
		JLabel label_name = new JLabel("�г���");
		label_name.setBounds(60, 140, 60, 20);
		panel.add(label);
		JLabel label_4 = new JLabel("����");
		label_4.setFont(new Font("�޸�����ü", Font.PLAIN, 13));
		label_4.setBounds(60, 170, 60, 20);
		panel.add(label_4);
		
		
		newUserid = new JTextField(10);
		newUserid.setBounds(145, 50, 120, 20);
		panel.add(newUserid);
		
		newUsername = new JTextField(10);
		newUsername.setBounds(145, 140, 120, 20);
		panel.add(newUsername);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(145, 80, 120, 20);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(145, 110, 120, 20);
		panel.add(passwordField_1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("����");
		chckbxNewCheckBox.setFont(new Font("�޸�����ü", Font.PLAIN, 13));
		buttonGroup.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setBounds(145, 165, 60, 25);
		panel.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("����");
		chckbxNewCheckBox_1.setFont(new Font("�޸�����ü", Font.PLAIN, 13));
		buttonGroup.add(chckbxNewCheckBox_1);
		chckbxNewCheckBox_1.setBounds(220, 166, 60, 25);
		panel.add(chckbxNewCheckBox_1);
		

		btnNewButton = new JButton("ȸ������");
		btnNewButton.setIcon(new ImageIcon("login/05.jpg"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(passwordField.getText().equals(passwordField_1.getText())) {
					
					join = new JoinProcess();
					if(newUserid.getText().length() >= 6	&& newUsername.getText().length() >= 4 && passwordField.getText().length() >= 6){
						dto.setNewUserId(newUserid.getText());
						dto.setNewUsername(newUsername.getText());
						dto.setNewUserpwd(passwordField.getText());
						join.backgroundJoin(dto);
						
						if(dto.isJoincheck()) {			//ȸ������ ����
							JOptionPane.showMessageDialog(null, "ȸ������ �Ϸ�");
							dispose();
						}else if(dto.getJcheck() == 2) {		//���̵� �Է��� �߸� �Ǿ��� ��
							JOptionPane.showMessageDialog(null, "�ߺ��Ǵ� ���̵� �ֽ��ϴ�.");
							newUserid.setText("");
						}else if(dto.getJcheck() == 3) {		//��й�ȣ�� Ʋ���� ��
							JOptionPane.showMessageDialog(null, "�ߺ��Ǵ� �г����� �ֽ��ϴ�.");
							newUsername.setText("");
						}else {			}
					}else if(newUserid.getText().length() < 6) {
						JOptionPane.showMessageDialog(null, "���̵� 6�� �̻�");
					}else if(newUsername.getText().length() < 4) {
						JOptionPane.showMessageDialog(null, "�г��� 4�� �̻�");
					}else if(passwordField.getText().length() < 6) {
						JOptionPane.showMessageDialog(null, "��й�ȣ 6�� �̻�");
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
					passwordField.setText("");
					passwordField_1.setText("");
				}
			}
		});
		btnNewButton.setBounds(160, 232, 120, 33);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("login/back1.jpg"));
		lblNewLabel.setBounds(0, 0, 339, 318);
		panel.add(lblNewLabel);
		
		setVisible(true);
	}
}

