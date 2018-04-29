package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import InitialGame.IGameView;
import InitialGame.IMenuView;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private MainProcess main;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
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
	public MainMenu() {

		setTitle("game menu");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setResizable(false);
		setLocationRelativeTo(null);

		contentPane = new JPanel(null);
		setContentPane(contentPane);		

		JButton button_login = new JButton("초성게임");
		button_login.setLayout(null);
		button_login.setBounds(125, 25, 200, 50);
		button_login.setFont(new Font("휴먼편지체", Font.PLAIN, 35));
		button_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IMenuView();
				dispose();
			}
		});

		JButton button_start = new JButton("문장게임");
		button_start.setLayout(null);
		button_start.setBounds(125, 100, 200, 50);
		button_start.setFont(new Font("휴먼편지체", Font.PLAIN, 35));
		button_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IGameView();
			}
		});

		JButton button_logout = new JButton("로그아웃");
		button_logout.setLayout(null);
		button_logout.setBounds(25, 200, 125, 50);
		button_logout.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		button_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JButton button_join = new JButton("회원가입");
		button_join.setLayout(null);
		button_join.setBounds(300, 200, 125, 50);
		button_join.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		button_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new JoinView();
			}
		});

		contentPane.add(button_login);
		contentPane.add(button_start);
		contentPane.add(button_logout);
		contentPane.add(button_join);

		setVisible(true);
	}
}
