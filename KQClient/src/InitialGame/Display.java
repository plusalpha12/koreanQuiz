package InitialGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Client.LoginView;
import Client.MainProcess;
import Client.Single;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;

public class Display extends JFrame {
	
	static JFrame jf;
	

	private JPanel contentPane;
	Single single;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Display frame = new Display();
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
	public Display() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jf =  new JFrame("Layout");
        jf.setSize(300, 300);
        jf.setLocation(700, 400);



		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(37, 49, 37, 49));
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 261);
		contentPane.add(panel);
		panel.setLayout(null);
		
		
		
		JLabel lblNewLabel = new JLabel("< \uCD08\uC131\uAC8C\uC784 >");
		lblNewLabel.setBounds(112, 32, 214, 50);
		panel.add(lblNewLabel);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("»ﬁ∏’∆Ì¡ˆ√º", Font.PLAIN, 42));
		
		JButton button_single = new JButton("\uD63C\uC790\uD558\uAE30");
		button_single.setBounds(112, 92, 214, 43);
		panel.add(button_single);
		buttonGroup.add(button_single);
		button_single.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JButton button_Single=(JButton)e.getSource();
				new Single();
			}
		});
		button_single.setFont(new Font("»ﬁ∏’∆Ì¡ˆ√º", Font.PLAIN, 18));
		
		JButton button_together = new JButton("\uAC19\uC774\uD558\uAE30");
		button_together.setBounds(112, 145, 214, 38);
		panel.add(button_together);
		buttonGroup.add(button_together);
		button_together.setFont(new Font("»ﬁ∏’∆Ì¡ˆ√º", Font.PLAIN, 18));
		
		JButton button_exit = new JButton("\uC885\uB8CC\uD558\uAE30");
		button_exit.setBounds(112, 193, 214, 38);
		panel.add(button_exit);
		button_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonGroup.add(button_exit);
		button_exit.setFont(new Font("»ﬁ∏’∆Ì¡ˆ√º", Font.PLAIN, 18));
		setVisible(true);
		

		
	}

}
