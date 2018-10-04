package SentenceGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import Client.MainMenu;
import Client.MainProcess;
import InitialGame.IMenuView;

public class SGameView {

	JFrame jf = new JFrame();
	private JPanel contentPane;
	//단어 버튼들
	JButton[] buttons;
	//어떤 문장을 내보낼지 index 역할
	int chooseSentence;
	//현재 맞는 문장
	String separtateWord;
	//틀린 단어
	String wrongWord;
	//단어버튼 시작 좌표
	int buttonX = 170;
	int buttonY = 194;
	JTextField textField;
	Timer timer;
	JProgressBar progressBar;
	int counter = 100;
	JButton exitButton;

	public SGameView(ArrayList<String> coSentences,  ArrayList<String> wrWords, MainProcess main, ArrayList<String> coSentences1, ArrayList<String> coSentences2) {
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
		exitButton = new JButton("종료");
		exitButton.setIcon(new ImageIcon("sentence\\04.jpg"));
		exitButton.setBounds(676, 245, 164, 161);
		contentPane.add(exitButton);
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				new MainMenu(main);
				jf.dispose();
			}
		});

		//checkButton
		JButton checkButton = new JButton("확인");
		checkButton.setIcon(new ImageIcon("sentence\\01.jpg"));
		checkButton.setBounds(770, 61, 70, 41);
		contentPane.add(checkButton);
		checkButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				//정답 체크
				if(	textField.getText().trim().equals(separtateWord)
					|| textField.getText().trim().equals(coSentences1.get(chooseSentence))
					|| textField.getText().trim().equals(coSentences2.get(chooseSentence)))
				{
					AudioInputStream ais;
					try {
						ais = AudioSystem.getAudioInputStream(new File("sound/correct.wav"));
						Clip clip = AudioSystem.getClip();
						clip.stop();
						clip.open(ais);
						FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
						gainControl.setValue(-50.0f);
						clip.start();
					} catch (UnsupportedAudioFileException | IOException e1) {
						e1.printStackTrace();
					} catch (LineUnavailableException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "정답입니다","정답입니다.",JOptionPane.INFORMATION_MESSAGE);
					for(int j = 0; j<buttons.length; j++)
						contentPane.remove(buttons[j]);
					contentPane.repaint();
					textField.setText("");
					buttonX = 170;
					buttonY = 194;
					int chooseSen;
					counter=101;
					//이전 문장 현재 문장 비교 do-while문
					do{
						chooseSen = (int)(coSentences.size()*Math.random());
					}while(chooseSentence==chooseSen);
					chooseSentence = chooseSen;
					setSentence(coSentences.get(chooseSentence), wrWords.get(chooseSentence));
				}
				//오답 체크
				else{
					AudioInputStream ais;
					try {
						ais = AudioSystem.getAudioInputStream(new File("sound/incorrect.wav"));
						Clip clip = AudioSystem.getClip();
						clip.stop();
						clip.open(ais);
						clip.start();
					} catch (UnsupportedAudioFileException | IOException e1) {
						e1.printStackTrace();
					} catch (LineUnavailableException e1) {
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "오답입니다.","오답입니다.",JOptionPane.OK_OPTION);
					textField.setText("");
				}
			}
		});

		//타이머 설정
		progressBar = new JProgressBar(JProgressBar.HORIZONTAL, 0, 100);
		progressBar.setValue(counter);
		ActionListener listener = new ActionListener() {    
			public void actionPerformed(ActionEvent ae) {
				counter--;
				progressBar.setValue(counter);
				if (counter<1) {
					JOptionPane.showMessageDialog(null, "시간 초과");
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

	public void setSentence(String sep, String wro){
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIgnoreRepaint(true);
		lblNewLabel_1.setLabelFor(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("sentence\\back.jpg"));
		lblNewLabel_1.setBounds(-1, 0, 841, 547);
		contentPane.add(lblNewLabel_1);
		separtateWord = sep; wrongWord = wro;
		//wordButton
		buttons = new JButton[separtateWord.split(" ").length+wrongWord.split(" ").length];
		String totalWord = separtateWord+" "+wrongWord;
		String [] totalArr = totalWord.split(" ");
		totalArr = shuffle(totalArr);
		for(int i = 0; i<buttons.length; i++){
			
				//buttons[i]= new JButton(new ImageIcon("C:\\Users\\pc1\\Documents\\카카오톡 받은 파일\\01 (3)\\03.jpg"));
				buttons[i] = new JButton(totalArr[i]);
				buttons[i].setBackground(new Color(173, 216, 230));
				buttons[i].setFont(new Font("HY신명조", Font.PLAIN, 12));
				buttons[i].setBounds(buttonX, buttonY, 150, 50);
				//buttons[i]= new JButton(new ImageIcon("C:\\Users\\pc1\\Documents\\카카오톡 받은 파일\\01 (3)\\03.jpg"));
				contentPane.add(buttons[i]);
				//buttons[i]= new JButton(new ImageIcon("C:\\Users\\pc1\\Documents\\카카오톡 받은 파일\\01 (3)\\03.jpg"));
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

