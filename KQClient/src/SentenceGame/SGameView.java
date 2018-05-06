package SentenceGame;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.*;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class SGameView extends JFrame {

	private JPanel contentPane;
	int score;
	int ClickNumber;
	int ClickCounting;
	int Level;
	String SepartateWord;
	private JTextField textField;


	public SGameView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 586);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(361, 10, 73, 41);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(76, 61, 691, 41);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton button_1 = new JButton("\uD55C");
		button_1.setBounds(150, 194, 171, 73);
		contentPane.add(button_1);

		JButton button = new JButton("\uAE4D\uB450\uAE30");
		button.setToolTipText("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(333, 194, 176, 73);
		contentPane.add(button);

		JButton button_2 = new JButton("\uC8FC\uC138\uC694");
		button_2.setBounds(521, 194, 164, 73);
		contentPane.add(button_2);

		JButton button_3 = new JButton("\uAE4D\uB69C\uAE30");
		button_3.setBounds(150, 285, 171, 73);
		contentPane.add(button_3);

		JButton button_4 = new JButton("\uC811\uC2DC");
		button_4.setBounds(333, 285, 176, 73);
		contentPane.add(button_4);

		JButton button_5 = new JButton("\uB354");
		button_5.setBounds(521, 285, 164, 73);
		contentPane.add(button_5);

		setUndecorated(true);
		setVisible(true);
	}

	public void PresentSentence() {

	}

	public class CountDown extends JFrame {
		private JLabel label;

		class MyThread extends Thread {
			public void run() {
				for (int i = 60; i >= 0; i--) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					label.setText(i + "");
				}
			}
		}

		public CountDown() {
			setTitle("카운트다운");
			setSize(250, 175);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JPanel panel = new JPanel();
			label = new JLabel("Start");
			label.setFont(new Font("serif", Font.BOLD, 100));
			panel.add(label);
			add(panel);
			(new MyThread()).start();
			setVisible(true);
		}

		public void main(String[] args) {
			new CountDown();
		}
	}
}
