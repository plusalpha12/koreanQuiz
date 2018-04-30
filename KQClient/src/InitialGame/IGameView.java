package InitialGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Client.Client;
import Client.MainProcess;

public class IGameView extends JFrame {

	protected JTextField textField;
	protected JTextArea chatlog;
	private JScrollPane sp;
	private MainProcess main = null;
	JLabel userid[] = new JLabel[4];
	JLabel username[] = new JLabel[4];

	public IGameView(MainProcess main){
		this.main = main;
		JPanel userPanel[] = new JPanel[4];

		setTitle("Initial Game!");
		setSize(600, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(5,5));

		JPanel upperPanel = new JPanel();
		upperPanel.setLayout(new GridLayout(1,5));
		JPanel lowerPanel = new JPanel();
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BorderLayout(5,5));
		JPanel UserPanel1 = new JPanel();
		UserPanel1.setLayout(new GridLayout(2,1));
		JPanel UserPanel2 = new JPanel();
		UserPanel2.setLayout(new GridLayout(2,1));
		
		userPanel[0] = new JPanel();
		userPanel[0].setLayout(new GridLayout(2,1));
		userPanel[1] = new JPanel();
		userPanel[1].setLayout(new GridLayout(2,1));
		userPanel[2] = new JPanel();
		userPanel[2].setLayout(new GridLayout(2,1));
		userPanel[3] = new JPanel();
		userPanel[3].setLayout(new GridLayout(2,1));


		chatlog = new JTextArea();
		sp = new JScrollPane(chatlog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatlog.setCaretPosition(chatlog.getDocument().getLength());

		textField = new JTextField(30);
		JLabel text = new JLabel();
		text.setHorizontalAlignment(JLabel.CENTER);

		upperPanel.setBackground(Color.gray);
		lowerPanel.setBackground(Color.pink);

		text.setText(Game.getRandomString(2));
		text.setFont(new Font("휴먼편지체", Font.BOLD, 50));


		getContentPane().add(upperPanel, "North");
		getContentPane().add(middlePanel, "Center");
		getContentPane().add(lowerPanel, "South");

		for(int i = 0; i<4; i++) {
			userid[i] = new JLabel();
			userid[i].setHorizontalAlignment(SwingConstants.CENTER);
			userid[i].setFont(new Font("휴먼편지체", Font.PLAIN, 15));
			username[i] = new JLabel();
			username[i].setHorizontalAlignment(SwingConstants.CENTER);
			username[i].setFont(new Font("휴먼편지체", Font.PLAIN, 15));
			userPanel[i].add(userid[i]);
			userPanel[i].add(username[i]);
		}

		JButton button_exit2 = new JButton("????");
		button_exit2.setFont(new Font("휴먼편지체", Font.PLAIN, 18));
		button_exit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		JButton button_exit = new JButton("종료하기");
		button_exit.setFont(new Font("휴먼편지체", Font.PLAIN, 18));
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.exit();
				dispose();
			}
		});

		JButton ans_input = new JButton("입력");
		ans_input.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		ans_input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.send_chat(textField.getText());
			}
		});
		lowerPanel.add(ans_input);

		upperPanel.add(button_exit2);
		upperPanel.add(text);
		upperPanel.add(button_exit);

		lowerPanel.add(textField);

		middlePanel.add(UserPanel1, BorderLayout.WEST);
		middlePanel.add(sp, BorderLayout.CENTER);
		middlePanel.add(UserPanel2, BorderLayout.EAST);

		UserPanel1.add(userPanel[0]);
		UserPanel1.add(userPanel[1]);
		UserPanel2.add(userPanel[2]);
		UserPanel2.add(userPanel[3]);


		setVisible(true);
		textField.requestFocus();
	}
	public void set_msg(String text) {
		chatlog.append(text);
	}

	public void set_userdata(int i, Client c) {
		userid[i].setText(c.getUserid());
		username[i].setText(c.getUsername());
	}
}
