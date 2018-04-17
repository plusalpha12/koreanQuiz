package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import InitialGame.Random;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Single extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Single frame = new Single();
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
	public Single() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JButton button_Start = new JButton();
		

		button_Start.setSize(125, 125);
		button_Start.setText("시작하기");
		button_Start.setLocation(120, 120);
		
		JPanel panel = new JPanel();
		panel.setBounds(-5, -1, 439, 262);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton button_login = new JButton("\uB85C\uADF8\uC778");
		button_login.setBounds(75, 47, 308, 51);
		panel.add(button_login);
		button_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LoginView();
			}
		});
		button_login.setFont(new Font("휴먼편지체", Font.PLAIN, 35));
		JButton button_start = new JButton("\uC2DC\uC791\uD558\uAE30");
		button_start.setBounds(75, 108, 309, 51);
		panel.add(button_start);
		button_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Random();
			}
		});
		
		button_start.setFont(new Font("휴먼편지체", Font.PLAIN, 35));
		
		JButton button_1 = new JButton("\uC774\uC804\uD654\uBA74");
		button_1.setBounds(51, 183, 119, 44);
		panel.add(button_1);
		button_1.setFont(new Font("휴먼편지체", Font.PLAIN, 21));
		
		JButton button = new JButton("\uD68C\uC6D0\uAC00\uC785");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new JoinView();
			}
		});
		button.setBounds(289, 183, 119, 44);
		panel.add(button);
		button.setFont(new Font("휴먼편지체", Font.PLAIN, 21));
		setVisible(true);
	}
}
