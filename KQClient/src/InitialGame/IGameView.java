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
	    JPanel UserPanel2 = new JPanel();
	    UserPanel2.setLayout(new GridLayout(2,1));
	   
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
	    
	    
	    JButton button_single1 = new JButton("유저1");
	    JButton button_single12 = new JButton("유저3");
	    JButton button_single2 = new JButton("유저2");
	    JButton button_single22 = new JButton("유저4");
	    

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
	    
		UserPanel1.add(button_single1);
		UserPanel1.add(button_single12);
		UserPanel2.add(button_single2);
		UserPanel2.add(button_single22);
		
	 
	    setVisible(true);
	    textField.requestFocus();
	}

}
