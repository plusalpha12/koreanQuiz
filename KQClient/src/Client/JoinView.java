package Client;

import java.awt.BorderLayout;
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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.JScrollBar;
import java.awt.Checkbox;
import java.awt.TextField;
import javax.swing.ButtonGroup;
import java.awt.event.MouseWheelListener;
import java.awt.event.MouseWheelEvent;

public class JoinView extends JFrame {

	private JPanel contentPane;
	private JTextField newUserid;
	private JTextField newUsername;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JoinProcess join;
	private UserDTO dto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinView frame = new JoinView();
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
	public JoinView() {
		setTitle("Join");
		setBounds(100, 100, 492, 332);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 476, 293);
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("\uC774\uB984:");
		lblNewLabel.setBounds(58, 133, 57, 15);
		panel.add(lblNewLabel);
		
		JLabel label = new JLabel("\uC544\uC774\uB514:");
		label.setBounds(58, 51, 57, 15);
		panel.add(label);
		JLabel label_1 = new JLabel("\uBE44\uBC00\uBC88\uD638:");
		label_1.setBounds(58, 82, 57, 15);
		panel.add(label_1);
		JLabel label_2 = new JLabel("\uBE44\uBC00\uBC88\uD638 \uD655\uC778:");
		label_2.setBounds(58, 107, 90, 15);
		panel.add(label_2);
		JLabel label_3 = new JLabel("\uC0DD\uB144\uC6D4\uC77C:");
		label_3.setBounds(58, 158, 57, 15);
		panel.add(label_3);
		JLabel label_4 = new JLabel("\uC131\uBCC4:");
		label_4.setBounds(58, 183, 57, 15);
		panel.add(label_4);
		
		
		newUserid = new JTextField();
		newUserid.setBounds(145, 48, 116, 21);
		panel.add(newUserid);
		newUserid.setColumns(10);
		
		newUsername = new JTextField();
		newUsername.setBounds(145, 130, 116, 21);
		panel.add(newUsername);
		newUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(144, 105, 117, 17);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(145, 78, 116, 19);
		panel.add(passwordField_1);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("\uB0A8\uC790");
		buttonGroup.add(chckbxNewCheckBox);
		chckbxNewCheckBox.setBounds(143, 179, 60, 23);
		panel.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("\uC5EC\uC790");
		buttonGroup.add(chckbxNewCheckBox_1);
		chckbxNewCheckBox_1.setBounds(200, 179, 60, 23);
		panel.add(chckbxNewCheckBox_1);
		
		JSpinner spinner = new JSpinner();
		spinner.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg0) {
			}
		});
		spinner.setModel(new SpinnerNumberModel(1994, 1900, 2000, 1));
		spinner.setBounds(145, 155, 69, 22);
		panel.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg0) {
			}
		});
		spinner_1.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		spinner_1.setBounds(226, 155, 29, 22);
		panel.add(spinner_1);
		
		JSpinner spinner_2 = new JSpinner();
		spinner_2.addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent arg0) {
			}
		});
		spinner_2.setModel(new SpinnerNumberModel(1, 1, 31, 1));
		spinner_2.setBounds(267, 155, 29, 22);
		panel.add(spinner_2);
		

		JButton btnNewButton = new JButton("\uC644\uB8CC");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(passwordField.getText().equals(passwordField_1.getText())) {
					dto.setNewUserId(newUserid.getText());
					dto.setNewUsername(newUsername.getText());
					dto.setNewUserpwd(passwordField.getText());
					//dto.setNewUserbirth(newUserid.getText());
					JOptionPane.showMessageDialog(null, "회원가입 완료!");
					dispose();
				}
			}
		});
		btnNewButton.setBounds(344, 239, 97, 23);
		panel.add(btnNewButton);
	}
	// JoinProcess와 연동
	public void setMain(JoinProcess join) {
		this.join = join;
	}
}
