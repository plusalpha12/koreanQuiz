package InitialGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import Client.MainProcess;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.event.ActionEvent;

public class AchieveView extends JFrame {
	
	private JPanel contentPane;
	private ArrayList<String> wordlist = new ArrayList<String>();
	ImageIcon icon, icon2;
	
	JLabel subtitle = new JLabel("단어장");
	JPanel panel = new JPanel();
	JPanel panel2 = new JPanel();
	JButton[] cho = new JButton[19];
	JButton button_exit = new JButton("이전 화면");
	JLabel jl;
	JLabel[] wordlabel = new JLabel[100];
	
	Hashtable <String, String> ht = new Hashtable<String, String>();
	
	public AchieveView(MainProcess main) {
		
		icon = new ImageIcon("achieve/achieve_back.jpg");
		/* 라벨에 배경 씌우기
		Image image = icon.getImage();
		Image cimage = image.getScaledInstance(1200, 800, image.SCALE_SMOOTH);
		icon = new ImageIcon(cimage);
		
		jl = new JLabel(icon);
		jl.setBounds(0, 0, 1200, 800);
		*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(1200, 800);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		contentPane.setBorder(new EmptyBorder(20, 50, 20, 50));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		panel = new JPanel(new BorderLayout());
		panel.setBounds(0, 0, 1100, 800);
		panel.setLayout(null);
		panel.setBackground(new Color(255,0,0,0));
		
		subtitle.setBounds(80, 40, 200, 50);
		subtitle.setFont(new Font("휴먼편지체", Font.PLAIN, 42));

		JPanel cpanel = new JPanel();
		cpanel.setBounds(1050, 0, 150, 800);
		cpanel.setLayout(new FlowLayout());
		cpanel.setBackground(new Color(255,0,0,0));
		
		for(int i=0; i<cho.length; ++i) {
			int num = i;
			icon2 = new ImageIcon("achieve/"+ (i+1) +".jpg");
			
			Image image = icon2.getImage();
			Image cimage = image.getScaledInstance(80, 25, image.SCALE_SMOOTH);
			icon2 = new ImageIcon(cimage);
			
			cho[i] = new JButton(icon2);
			cho[i].setSize(80, 25);
			cho[i].setBackground(Color.WHITE);
			cho[i].setBorderPainted(false);
			cho[i].setFocusPainted(true);
			cpanel.add(cho[i]);
			
			cho[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					main.AchieveWord(num);
				}
			});
		}
		
		panel2.setBounds(100, 100, 975, 600);
		//panel2.setBorder(new LineBorder(Color.BLACK,2));
		panel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		for(int j = 0; j<100; ++j) {
			wordlabel[j] = new JLabel("");
			wordlabel[j].setFont(new Font("바탕", Font.PLAIN, 30));
			wordlabel[j].setOpaque(false);
			panel2.add(wordlabel[j]);
			
			wordlabel[j].addMouseListener(new MyMouse());
		}
		
		button_exit.setBounds(500, 710, 200, 40);
		button_exit.setFont(new Font("휴먼편지체", Font.PLAIN, 18));
		button_exit.setBorderPainted(false);
		button_exit.setFocusPainted(false);
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new IMenuView(main);
				main.send_chat("close");
				dispose();
			}
		});

		panel.add(subtitle);
		panel.add(panel2, "WEST");
		panel.add(button_exit);
		contentPane.add(cpanel);
		contentPane.add(panel);
		//contentPane.add(jl);
		
		setVisible(true);
	}
	
	@SuppressWarnings("null")
	public void setWord(ArrayList<String> wordlist, ArrayList<String> defilist) {
		int i = 0;
		for(String word : wordlist) {
			if(word.equals("null")) {
				wordlabel[i].setText("단어가 없습니다");
				continue;
			}
			ht.put(wordlist.get(i), defilist.get(i));
			wordlabel[i].setText(word);
			wordlabel[i].setBorder(new LineBorder(Color.BLACK,1));
			++i;
			System.out.println(ht);
		}
		if(i>=25 && i<50) {
			for(;i<50;++i) {
				wordlabel[i].setText("");
				wordlabel[i].setBorder(new LineBorder(Color.BLACK,0));
			}
		}else if(i >= 50) {
			for(;i<100;++i) {
				wordlabel[i].setText("");
				wordlabel[i].setBorder(new LineBorder(Color.BLACK,0));
			}
		}else {
			for(;i<25;++i) {
				wordlabel[i].setText("");
				wordlabel[i].setBorder(new LineBorder(Color.BLACK,0));
			}
			
		}
	}
	class MyMouse implements MouseListener, MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent arg0) {	// TODO Auto-generated method stub
		}
		@Override
		public void mouseMoved(MouseEvent arg0) {// TODO Auto-generated method stub	
		}
		@Override
		public void mouseClicked(MouseEvent e) {	// TODO Auto-generated method stub
			if(e.getClickCount() == 1) {
				JLabel jl = (JLabel)e.getSource();
				ht.get(jl.getText());
				System.out.println(ht.get(jl.getText()));
				JOptionPane.showMessageDialog(null, ht.get(jl.getText()),"의미 설명", JOptionPane.QUESTION_MESSAGE);
			}
		}
		@Override
		public void mouseEntered(MouseEvent arg0) {	// TODO Auto-generated method stub
		}
		@Override
		public void mouseExited(MouseEvent arg0) {	// TODO Auto-generated method stub
		}
		@Override
		public void mousePressed(MouseEvent arg0) {	// TODO Auto-generated method stub
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {// TODO Auto-generated method stub
		}
	}
}
