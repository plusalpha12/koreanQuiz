package SentenceGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SGameWrongView extends JFrame {

	private JPanel contentPane;
	ImageIcon icon;
	JLabel lbl;
	JButton[] buttons;
	JButton[] buttons2;
	int buttonX, buttonX2, buttonY;
	int i = 0;

	public SGameWrongView(ArrayList<String> sentence, ArrayList<String> wrWord) {

		buttonX = 80;
		buttonX2 = 450;
		buttonY = 40;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		setBounds(400, 250, 700, 700);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaptionBorder);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		icon = new ImageIcon("sentence\\back.jpg");
		Image image = icon.getImage();
		Image cimage = image.getScaledInstance(700, 700, image.SCALE_SMOOTH);
		icon = new ImageIcon(cimage);
		lbl = new JLabel(icon);
		lbl.setBounds(-1, 0, 700, 700);

		buttons = new JButton[sentence.size()];
		buttons2 = new JButton[wrWord.size()];
		
		for(String text : sentence){

			buttons[i] = new JButton(text);
			buttons[i].setBackground(new Color(173, 216, 200));
			buttons[i].setFont(new Font("HY신명조", Font.PLAIN, 15));
			buttons[i].setBounds(buttonX, buttonY, 350, 40);
			contentPane.add(buttons[i]);
			
			buttons2[i] = new JButton(text);
			buttons2[i].setBackground(new Color(173, 216, 200));
			buttons2[i].setFont(new Font("HY신명조", Font.PLAIN, 15));
			buttons2[i].setBounds(buttonX2, buttonY, 150, 40);
			contentPane.add(buttons2[i]);

			buttons[i].addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
				}
			});
			buttonY+=50;
			++i;
		}
		contentPane.add(lbl);
		setVisible(true);
	}
}
