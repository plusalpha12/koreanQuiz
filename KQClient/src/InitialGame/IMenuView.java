package InitialGame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.LoginView;
import Client.MainProcess;
import Client.MainMenu;

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
		
		this.main = main;
		
		setSize(460, 300);
		setLocationRelativeTo(null);
		
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
				main.InitialGameBack();
			}
		});
		
		JButton button_together = new JButton("�����ϱ�");
		button_together.setBounds(120, 145, 210, 40);
		panel.add(button_together);
		button_together.setFont(new Font("�޸�����ü", Font.PLAIN, 18));
		button_together.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.InitialGameBack();
			}
		});
		
		JButton button_exit = new JButton("�����ϱ�");
		button_exit.setBounds(120, 200, 210, 40);
		panel.add(button_exit);
		button_exit.setFont(new Font("�޸�����ü", Font.PLAIN, 18));
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.exit();
				dispose();
			}
		});
		
		
		setVisible(true);
	}
}
