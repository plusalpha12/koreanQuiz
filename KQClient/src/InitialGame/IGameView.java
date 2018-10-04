package InitialGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Client.MainProcess;
import serial.sendclient;

public class IGameView extends JFrame {

	protected JTextField textField;
	protected JTextArea chatlog;
	private static MainProcess main = null;
	private JScrollPane sp;
	private JLabel quizlabel = new JLabel();
	final static JProgressBar progressBar = new JProgressBar();
	Thread dthread = new Thread(new d_Thread(progressBar, true));
	JButton ready = new JButton("준비하기");
	JButton ans_input = new JButton("입력");
	private boolean ready_switch = false;
	

	JLabel userid[] = new JLabel[4];
	JLabel username[] = new JLabel[4];
	JLabel score[] = new JLabel[4];
	JLabel userline[] = new JLabel[4];

	public IGameView(MainProcess main){

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		this.main = main;
		JPanel userPanel[] = new JPanel[4];

		setSize(600, 600);
		setLocationRelativeTo(null);
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
		userPanel[0].setLayout(new GridLayout(4,1));
		userPanel[1] = new JPanel();
		userPanel[1].setLayout(new GridLayout(4,1));
		userPanel[2] = new JPanel();
		userPanel[2].setLayout(new GridLayout(4,1));
		userPanel[3] = new JPanel();
		userPanel[3].setLayout(new GridLayout(4,1));


		chatlog = new JTextArea();
		chatlog.setEditable(false);
		sp = new JScrollPane(chatlog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		chatlog.setCaretPosition(chatlog.getDocument().getLength());

		textField = new JTextField(30);
		textField.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					if(!textField.getText().equals("")) {
						main.send_chat(textField.getText());
						textField.setText("");
						textField.requestFocus();
					}
				}
			}
			@Override
			public void keyReleased(KeyEvent arg0) {}
			@Override
			public void keyTyped(KeyEvent arg0) {}

		});
		quizlabel.setHorizontalAlignment(JLabel.CENTER);

		upperPanel.setBackground(Color.gray);
		lowerPanel.setBackground(Color.pink);

		getContentPane().add(upperPanel, "North");
		getContentPane().add(middlePanel, "Center");
		getContentPane().add(lowerPanel, "South");

		for(int i = 0; i<4; i++) {
			userid[i] = new JLabel("");
			userid[i].setHorizontalAlignment(SwingConstants.CENTER);
			userid[i].setFont(new Font("휴먼편지체", Font.PLAIN, 15));
			username[i] = new JLabel("");
			username[i].setHorizontalAlignment(SwingConstants.CENTER);
			username[i].setFont(new Font("휴먼편지체", Font.PLAIN, 15));
			score[i] = new JLabel("");
			score[i].setHorizontalAlignment(SwingConstants.CENTER);
			score[i].setFont(new Font("휴먼편지체", Font.PLAIN, 15));
			userline[i] = new JLabel("            ");
			userline[i].setHorizontalAlignment(SwingConstants.CENTER);
			userline[i].setFont(new Font("휴먼편지체", Font.PLAIN, 15));
			userPanel[i].add(userid[i]);
			userPanel[i].add(username[i]);
			userPanel[i].add(score[i]);
			userPanel[i].add(userline[i]);
		}

		progressBar.setBounds(50,50,250,30);
		progressBar.setValue(0);
		progressBar.setFont(new Font("휴먼편지체", Font.PLAIN, 18));
		progressBar.setStringPainted(true);

		/*
		JButton button_exit2 = new JButton("????");
		button_exit2.setFont(new Font("휴먼편지체", Font.PLAIN, 18));
		button_exit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//타이머 위치
			}
		});
		 */
		JButton button_exit = new JButton("종료하기");
		button_exit.setFont(new Font("휴먼편지체", Font.PLAIN, 18));
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				main.send_chat("close");
				dthread.interrupt();
				new IMenuView(main);
				dispose();
			}
		});

		
		// 채팅 or 정답 입력
		ans_input.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		ans_input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!textField.getText().equals("")) {
					main.send_chat(textField.getText());
					textField.setText("");
					textField.requestFocus();
				}
			}
		});
		
		textField.setVisible(false);
		ans_input.setVisible(false);
		
		// 준비
		ready.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		ready.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(ready_switch) {
					ready.setText("준비하기");
					ready_switch = false;
					main.send_chat("ready_cancle");
				}else {
					ready.setText("준비");
					ready_switch = true;
					main.send_chat("ready");
				}
			}
		});
		ready.setVisible(true);

		upperPanel.add(progressBar);
		upperPanel.add(quizlabel);
		upperPanel.add(button_exit);

		lowerPanel.add(textField);
		lowerPanel.add(ans_input);
		lowerPanel.add(ready, BorderLayout.WEST);
		
		middlePanel.add(UserPanel1, BorderLayout.WEST);
		middlePanel.add(sp, BorderLayout.CENTER);
		middlePanel.add(UserPanel2, BorderLayout.EAST);

		UserPanel1.add(userPanel[0]);
		UserPanel1.add(userPanel[2]);
		UserPanel2.add(userPanel[1]);
		UserPanel2.add(userPanel[3]);

		setVisible(true);

		textField.requestFocus();
	}
	public void set_msg(String text) {
		chatlog.append(text);
	}
	public void set_quiz(String text) {
		quizlabel.setText(text);
		quizlabel.setFont(new Font("휴먼편지체", Font.BOLD, 50));
	}
	public void set_userdata(int i, sendclient c) {
		userid[i].setText(c.getUserid());
		username[i].setText(c.getUsername()); 
	}
	public void set_userscore(String i, String userId) {
		for(int j = 0; j < 4; j++) {
			if(userid[j].getText().equals(userId)) {
				score[j].setText(i);
			}
		}
	}
	public void game_start(String text) {
		ready.setVisible(false);
		textField.setVisible(true);
		ans_input.setVisible(true);
		set_quiz(text);
		dthread.setDaemon(true);
		dthread.start();
	}
}

class d_Thread implements Runnable {

	static JProgressBar progressBar = null;
	static JFrame frame = new JFrame();
	static boolean run;
	int i = 0;

	d_Thread(JProgressBar progressBar, boolean tf){
		d_Thread.progressBar = progressBar;
		this.run = tf;
	}

	public void run(){
		while(run) {
			try {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						progressBar.setValue(i);
					}
				});
				Thread.sleep(1000);
				i++;
				if(i==100)
					JOptionPane.showMessageDialog(frame, "시간이 종료되었습니다");
			} catch (InterruptedException e) {
				run = false;
			}
		}
	}
}
