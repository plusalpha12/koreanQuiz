package InitialGame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.MainMenu;
import Client.MainProcess;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IMenuView extends JFrame {
	
	private JPanel contentPane;
	private MainProcess main;
	
	public IMenuView(MainProcess main) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		this.main = main;
		
		setSize(460, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 50, 20, 50));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 300);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("< �ʼ����� >");
		lblNewLabel.setBounds(110, 30, 220, 50);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("�޸�����ü", Font.PLAIN, 42));
		
		JButton button_single = new JButton("ȥ���ϱ�");
		button_single.setBounds(120, 90, 210, 40);
		panel.add(button_single);
		button_single.setFont(new Font("�޸�����ü", Font.PLAIN, 18));
		button_single.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.InitialGameBack(true);
				dispose();
			}
		});
		
		JButton button_together = new JButton("�����ϱ�");
		button_together.setBounds(120, 145, 210, 40);
		panel.add(button_together);
		button_together.setFont(new Font("�޸�����ü", Font.PLAIN, 18));
		button_together.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.InitialGameBack(false);
				dispose();
			}
		});
		
		JButton button_achievement = new JButton("�������");
		button_achievement.setBounds(120, 200, 210, 40);
		panel.add(button_achievement);
		button_achievement.setFont(new Font("�޸�����ü", Font.PLAIN, 18));
		button_achievement.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.AchieveBack();
				dispose();
			}
		});
		
		JButton button_exit = new JButton("���� ����");
		button_exit.setBounds(120, 255, 210, 40);
		panel.add(button_exit);
		button_exit.setFont(new Font("�޸�����ü", Font.PLAIN, 18));
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MainMenu(main);
				dispose();
			}
		});
		
		
		setVisible(true);
	}
}
