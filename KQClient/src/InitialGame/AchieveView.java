package InitialGame;

import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.MainProcess;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AchieveView extends JFrame {
	
	private JPanel contentPane;
	private JLabel wordlabel[] = null;
	private ArrayList<String> wordlist = null;
	ImageIcon icon;
	
	JLabel subtitle = new JLabel("백과사전");
	JPanel panel2 = new JPanel();
	
	public AchieveView(MainProcess main) {
		
		icon = new ImageIcon("drawable/background2.jpg");
		Image image = icon.getImage();
		Image cimage = image.getScaledInstance(1200, 800, image.SCALE_SMOOTH);
		icon = new ImageIcon(cimage);
		
		JLabel jl = new JLabel(icon);
		jl.setBounds(0, 0, 1200, 800);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		wordlist = new ArrayList<String>();
		wordlist.add("단어 사전");
		wordlabel = new JLabel[wordlist.size()];
		
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(20, 50, 20, 50));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(jl);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1200, 800);
		contentPane.add(panel);
		panel.setLayout(null);
		
		subtitle.setSize(200,50);
		subtitle.setHorizontalAlignment(JLabel.CENTER);
		subtitle.setFont(new Font("휴먼편지체", Font.PLAIN, 42));

		panel.add(panel2);
		panel2.setBounds(0, 80, 600, 500);
		panel2.setLayout(new FlowLayout());
		
		JButton button_exit = new JButton("이전 화면");
		button_exit.setBounds(500, 710, 200, 40);
		panel.add(button_exit);
		button_exit.setFont(new Font("휴먼편지체", Font.PLAIN, 18));
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.send_chat("close");
				new IMenuView(main);
				dispose();
			}
		});
		
		setVisible(true);
	}
	
	public void setWord(ArrayList<String> wordlist) {
		int i = 0;
		this.wordlist = wordlist;
		
		for(String word : wordlist) {
			wordlabel[i] = new JLabel(word);
			panel2.add(wordlabel[i]);
			wordlabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			wordlabel[i].setFont(new Font("휴먼편지체", Font.PLAIN, 15));
			++i;
		}
		
	}
}
