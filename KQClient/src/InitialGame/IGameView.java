package InitialGame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		setSize(800, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel upperPanel = new JPanel();
	    JPanel lowerPanel = new JPanel();
	    
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BorderLayout());
	    JPanel UserPanel1 = new JPanel();
	    UserPanel1.setLayout(new GridLayout(2,1));
	    JPanel UserPanel2 = new JPanel();
	    UserPanel2.setLayout(new GridLayout(2,1));
	    chatlog = new JTextArea();
	    sp = new JScrollPane(chatlog, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    chatlog.setCaretPosition(chatlog.getDocument().getLength());
	    
	    textField = new JTextField(30);
	    JLabel text = new JLabel();
	    
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
	    upperPanel.setBackground(Color.gray);
	    lowerPanel.setBackground(Color.pink);

	    text.setText(Game.getRandomString(2));
	    text.setFont(new Font("휴먼편지체", Font.BOLD, 50));

	    middlePanel.add(UserPanel1, BorderLayout.WEST);
	    middlePanel.add(sp, BorderLayout.CENTER);
	    middlePanel.add(UserPanel2, BorderLayout.EAST);

	    getContentPane().add(upperPanel, "North");
	    getContentPane().add(middlePanel, "Center");
	    getContentPane().add(lowerPanel, "South");
	    
	    upperPanel.add(text);
	    lowerPanel.add(textField);
	    
	    JButton button_single1 = new JButton("유저1");
		UserPanel1.add(button_single1);
	    JButton button_single12 = new JButton("유저3");
		UserPanel1.add(button_single12);
	    JButton button_single2 = new JButton("유저2");
		UserPanel2.add(button_single2);
	    JButton button_single22 = new JButton("유저4");
		UserPanel2.add(button_single22);
	 
	    setVisible(true);
	    textField.requestFocus();
	}

}
