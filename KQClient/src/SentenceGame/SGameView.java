package SentenceGame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class SGameView {

	public static JFrame jf = new JFrame();
	private static JPanel contentPane;
	//�ܾ� ��ư��
	static JButton[] buttons;
	//� ������ �������� index ����
	static int chooseSentence;
	//���� �´� ����
	static String separtateWord;
	//Ʋ�� �ܾ�
	static String wrongWord;
	//�ܾ��ư ���� ��ǥ
	static int buttonX = 170;
	static int buttonY = 194;
	static JTextField textField;
	static Timer timer;
	static JProgressBar progressBar;
	static int counter = 100;
	//�´� ���� ����Ʈ
	static ArrayList<String> coSentences;
	//Ʋ�� �ܾ� ����Ʈ
	static ArrayList<String> wrWords;

	public SGameView(ArrayList<String> coSentences,  ArrayList<String> wrWords) {
		this.coSentences = coSentences; this.wrWords = wrWords;
		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setBounds(100, 100, 856, 586);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jf.setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(361, 10, 73, 41);
		contentPane.add(lblNewLabel);

		//textField
		textField = new JTextField();
		textField.setBounds(76, 61, 691, 41);
		contentPane.add(textField);
		textField.setColumns(10);

		//exitButton
		JButton exitButton = new JButton("����");
		exitButton.setBounds(741, 0, 100, 50);
		contentPane.add(exitButton);
		exitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		//checkButton
		JButton checkButton = new JButton("Ȯ��");
		checkButton.setBounds(770, 61, 70, 41);
		contentPane.add(checkButton);
		checkButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//���� üũ
				if(textField.getText().trim().equals(separtateWord)){
					JOptionPane.showMessageDialog(null, "�����Դϴ�","�����Դϴ�.",JOptionPane.INFORMATION_MESSAGE);
					for(int j = 0; j<buttons.length; j++)
						contentPane.remove(buttons[j]);
					contentPane.repaint();
					textField.setText("");
					buttonX = 170;
					buttonY = 194;
					int chooseSen;
					counter=101;
					//���� ���� ���� ���� �� do-while��
					do{
						chooseSen = (int)(coSentences.size()*Math.random());
					}while(chooseSentence==chooseSen);
					chooseSentence = chooseSen;
					setSentence(coSentences.get(chooseSentence), wrWords.get(chooseSentence));
				}
				//���� üũ
				else{
					JOptionPane.showMessageDialog(null, "�����Դϴ�.","�����Դϴ�.",JOptionPane.OK_OPTION);
					textField.setText("");
				}
			}
		});

		//Ÿ�̸� ����
		progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		progressBar.setValue(counter);
		ActionListener listener = new ActionListener() {    
			public void actionPerformed(ActionEvent ae) {
				counter--;
				progressBar.setValue(counter);
				if (counter<1) {
					JOptionPane.showMessageDialog(null, "�ð� �ʰ�");
					timer.stop();
					System.exit(0);
				} 
			}
		};
		timer = new Timer(1000, listener);
		timer.start();
		progressBar.setBounds(0, 0, 164, 50);
		contentPane.add(progressBar);
		chooseSentence = (int)(coSentences.size()*Math.random());
		setSentence(coSentences.get(chooseSentence), wrWords.get(chooseSentence));
		jf.setVisible(true);
	}

	public static void setSentence(String sep, String wro){
		separtateWord = sep; wrongWord = wro;
		//wordButton
		buttons = new JButton[separtateWord.split(" ").length+wrongWord.split(" ").length];
		String totalWord = separtateWord+" "+wrongWord;
		String [] totalArr = totalWord.split(" ");
		totalArr = shuffle(totalArr);
		for(int i = 0; i<buttons.length; i++){
			buttons[i] = new JButton(totalArr[i]);
			buttons[i].setBounds(buttonX, buttonY, 150, 50);
			contentPane.add(buttons[i]);
			buttons[i].addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					textField.setText(textField.getText()+" "+arg0.getActionCommand());
				}
			});
			buttonX+=170;
			//button newLine
			if(i%3==2){
				buttonX=170;
				buttonY+=70;
			}
		}
		jf.setVisible(true);

	}


	private static Random random;
	public static String[] shuffle(String [] array) {
		if (random == null) random = new Random();
		int count = array.length;
		for (int i = count; i > 1; i--) {
			swap(array, i - 1, random.nextInt(i));
		}
		return array;
	}
	private static String[] swap(String[] array, int i, int j) {
		String temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		return array;
	}
}
