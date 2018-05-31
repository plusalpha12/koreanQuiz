package InitialGame;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.MainProcess;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AchieveView extends JFrame {
	
	private JPanel contentPane;
	private JLabel wordlabel[] = null;
	
	public AchieveView(MainProcess main, ArrayList<String> wordlist) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		int i = 0;
		wordlabel = new JLabel[wordlist.size()];
		
		setSize(600, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 50, 20, 50));
		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 600);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("백과사전");
		lblNewLabel.setBounds(190, 20, 220, 50);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("휴먼편지체", Font.PLAIN, 42));

		JPanel panel2 = new JPanel();
		panel.add(panel2);
		panel2.setBounds(0, 80, 600, 500);
		panel2.setLayout(new FlowLayout());
		
		for(String word : wordlist) {
			wordlabel[i] = new JLabel(word);
			panel2.add(wordlabel[i]);
			wordlabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			wordlabel[i].setFont(new Font("휴먼편지체", Font.PLAIN, 15));
			i++;
		}
		
		
		JButton button_exit = new JButton("종료하기");
		button_exit.setBounds(195, 525, 210, 40);
		panel.add(button_exit);
		button_exit.setFont(new Font("휴먼편지체", Font.PLAIN, 18));
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IMenuView(main);
				dispose();
			}
		});
		
		setVisible(true);
	}
}
