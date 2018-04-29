package InitialGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;

public class IMenuView extends JFrame {
	
	private MainProcess main;
	private JPanel contentPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IMenuView frame = new IMenuView();
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
	public IMenuView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(460, 300);
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 50, 20, 50));
		setContentPane(contentPane);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 300);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("< 초성게임 >");
		lblNewLabel.setBounds(110, 30, 220, 50);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("휴먼편지체", Font.PLAIN, 42));
		
		JButton button_single = new JButton("혼자하기");
		button_single.setBounds(120, 90, 210, 40);
		panel.add(button_single);
		button_single.setFont(new Font("휴먼편지체", Font.PLAIN, 18));
		button_single.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IGameView();
			}
		});
		
		JButton button_together = new JButton("같이하기");
		button_together.setBounds(120, 145, 210, 40);
		panel.add(button_together);
		button_together.setFont(new Font("휴먼편지체", Font.PLAIN, 18));
		button_together.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JButton button_Single=(JButton)e.getSource();
				new IGameView();
			}
		});
		
		JButton button_exit = new JButton("종료하기");
		button_exit.setBounds(120, 200, 210, 40);
		panel.add(button_exit);
		button_exit.setFont(new Font("휴먼편지체", Font.PLAIN, 18));
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		
		setVisible(true);
	}
}
