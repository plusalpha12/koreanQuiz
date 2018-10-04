package InitialGame;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.MainMenu;
import Client.MainProcess;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IMenuView extends JFrame {
	
	private JPanel contentPane;
	private MainProcess main;
	ImageIcon icon;
	int[] num = {0,1,0};
	
	public IMenuView(MainProcess main) {
		this.main = main;
		
		main.setIview(this);
		
		icon = new ImageIcon("drawable/background3.jpg");
		Image image = icon.getImage();
		Image cimage = image.getScaledInstance(500, 400, image.SCALE_SMOOTH);
		icon = new ImageIcon(cimage);
		JLabel jl = new JLabel(icon);
		jl.setBounds(0, 0, 500, 400);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		setTitle("접속중인 유저 : " + num[1] + " / 대기중인 유저 : " + num[2]);
		setSize(500, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 50, 20, 50));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 500, 500);
		panel.setLayout(null);
		
		JLabel subtitle = new JLabel("< 초성게임 >");
		subtitle.setBounds(130, 30, 220, 50);
		panel.add(subtitle);
		subtitle.setHorizontalAlignment(SwingConstants.CENTER);
		subtitle.setFont(new Font("휴먼편지체", Font.PLAIN, 42));
		
		JButton button_single = new JButton("혼자하기");
		button_single.setBounds(140, 90, 210, 40);
		panel.add(button_single);
		button_single.setFont(new Font("휴먼편지체", Font.BOLD, 22));
		button_single.setBackground(new Color(170,220,170));
		button_single.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.InitialGameBack(true);
				dispose();
			}
		});
		
		JButton button_together = new JButton("같이하기");
		button_together.setBounds(140, 150, 210, 40);
		panel.add(button_together);
		button_together.setFont(new Font("휴먼편지체", Font.BOLD, 22));
		button_together.setBackground(new Color(170,220,170));
		button_together.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.InitialGameBack(false);
				dispose();
			}
		});
		
		JButton button_achievement = new JButton("백과사전");
		button_achievement.setBounds(140, 210, 210, 40);
		panel.add(button_achievement);
		button_achievement.setFont(new Font("휴먼편지체", Font.BOLD, 22));
		button_achievement.setBackground(new Color(170,220,170));
		button_achievement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.AchieveBack();
				dispose();
			}
		});
		
		JButton button_exit = new JButton("놀이 선택");
		button_exit.setBounds(140, 270, 210, 40);
		panel.add(button_exit);
		button_exit.setFont(new Font("휴먼편지체", Font.BOLD, 22));
		button_exit.setBackground(new Color(170,220,170));
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainMenu(main);
				dispose();
			}
		});
		panel.add(jl);
		contentPane.add(panel);
		
		main.connstart();
		
		setVisible(true);
	}
	
	public void settitle(int[] num) {
		this.num = num;
		setTitle("접속중인 유저 : " + num[1] + " / 대기중인 유저 : " + num[2]);
	}
}
