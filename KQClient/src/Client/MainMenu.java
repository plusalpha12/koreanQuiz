package Client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import InitialGame.IGameView;
import InitialGame.IMenuView;
import SentenceGame.SGameView;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private MainProcess main;
	ImageIcon icon;

	public MainMenu(MainProcess main) {
		
		this.main = main;
		JLabel jl = new JLabel(new ImageIcon("igame/back2.jpg"));
		jl.setBounds(0, 0, 450, 300);

		setTitle("game menu");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setResizable(false);
		setLocationRelativeTo(null);

		contentPane = new JPanel(null);
		setContentPane(contentPane);		

		JButton button_login = new JButton("초성게임");
		button_login.setIcon(new ImageIcon("igame/igame.jpg"));
		button_login.setLayout(null);
		button_login.setBounds(120, 25, 210, 50);
		button_login.setFont(new Font("휴먼편지체", Font.PLAIN, 35));
		button_login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IMenuView(main);
				dispose();
			}
		});

		JButton button_start = new JButton("문장게임");
		button_start.setIcon(new ImageIcon("igame/sen.jpg"));
		button_start.setLayout(null);
		button_start.setBounds(120, 100, 210, 50);
		button_start.setFont(new Font("휴먼편지체", Font.PLAIN, 35));
		button_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.SentenceGameBack();
				dispose();
			}
		});

		JButton button_logout = new JButton("로그아웃");
		button_logout.setIcon(new ImageIcon("igame/out.jpg"));
		button_logout.setLayout(null);
		button_logout.setBounds(25, 200, 116, 50);
		button_logout.setFont(new Font("휴먼편지체", Font.PLAIN, 25));
		button_logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.exit();
				new LoginView();
				dispose();
			}
		});

		JButton button_join = new JButton("회원가입");
		button_join.setIcon(new ImageIcon("igame/join.jpg"));
		button_join.setLayout(null);
		button_join.setBounds(300, 200, 116, 50);
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
		contentPane.add(jl);

		setVisible(true);
	}
}
