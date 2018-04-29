package InitialGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Client.JoinView;
import Client.MainMenu;

public class IGameView extends JFrame {
	
	  protected JTextField textField;
	  protected JTextArea chatlog;
	  private JScrollPane sp;
	  private final static String newline = "\n";

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IGameView igame = new IGameView();
					igame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public IGameView(){
		
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
	    JPanel UserPaner1_1 = new JPanel();
	    UserPaner1_1.setLayout(new GridLayout(2,1));
	    JPanel UserPaner1_2 = new JPanel();
	    UserPaner1_2.setLayout(new GridLayout(2,1));
	    JPanel UserPanel2 = new JPanel();
	    UserPanel2.setLayout(new GridLayout(2,1));
	    JPanel UserPaner2_1 = new JPanel();
	    UserPaner2_1.setLayout(new GridLayout(2,1));
	    JPanel UserPaner2_2 = new JPanel();
	    UserPaner2_2.setLayout(new GridLayout(2,1));
	   
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

		JLabel userid1 = new JLabel("user1");
		userid1.setHorizontalAlignment(SwingConstants.CENTER);
		userid1.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		JLabel username1 = new JLabel("user1");
		username1.setHorizontalAlignment(SwingConstants.CENTER);
		username1.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		UserPaner1_1.add(userid1);
		UserPaner1_1.add(username1);
		
		JLabel userid2 = new JLabel("user2");
		userid2.setHorizontalAlignment(SwingConstants.CENTER);
		userid2.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		JLabel username2 = new JLabel("user2");
		username2.setHorizontalAlignment(SwingConstants.CENTER);
		username2.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		UserPaner1_2.add(userid2);
		UserPaner1_2.add(username2);
		
		JLabel userid3 = new JLabel("user3");
		userid3.setHorizontalAlignment(SwingConstants.CENTER);
		userid3.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		JLabel username3 = new JLabel("user3");
		username3.setHorizontalAlignment(SwingConstants.CENTER);
		username3.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		UserPaner2_1.add(userid3);
		UserPaner2_1.add(username3);
		
		JLabel userid4 = new JLabel("user4");
		userid4.setHorizontalAlignment(SwingConstants.CENTER);
		userid4.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		JLabel username4 = new JLabel("user4");
		username4.setHorizontalAlignment(SwingConstants.CENTER);
		username4.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		UserPaner2_2.add(userid4);
		UserPaner2_2.add(username4);

		JButton button_exit2 = new JButton("????");
		button_exit2.setFont(new Font("휴먼편지체", Font.PLAIN, 18));
		button_exit2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		JButton button_exit = new JButton("종료하기");
		button_exit.setFont(new Font("휴먼편지체", Font.PLAIN, 18));
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JButton ans_input = new JButton("입력");
		ans_input.setFont(new Font("휴먼편지체", Font.PLAIN, 15));
		ans_input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
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
	    
		UserPanel1.add(UserPaner1_1);
		UserPanel1.add(UserPaner1_2);
		UserPanel2.add(UserPaner2_1);
		UserPanel2.add(UserPaner2_2);
		
	 
	    setVisible(true);
	    textField.requestFocus();
	}
	public void set_msg() {
		chatlog.append("test");
	}

}
