package InitialGame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 

public class ex  {

 

  protected JTextField textField;
  protected JTextArea textArea;
  private final static String newline = "\n";

  

  public static void main(String[] args) {
     ex chat = new ex();
     chat.ex();       
  }
 

 

  public void ex() {
    JFrame f = new JFrame("Hack the Planet");
    JPanel upperPanel = new JPanel();  
    JPanel lowerPanel = new JPanel();
    textField = new JTextField(20);
    textArea = new JTextArea(5, 20);
    


    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
    upperPanel.setBackground(Color.gray);
    lowerPanel.setBackground(Color.pink);

    textArea.setEditable(false);




    f.getContentPane().add(upperPanel, "North");
    f.getContentPane().add(lowerPanel, "South");
    upperPanel.add(textArea);
    lowerPanel.add(textField);
 
    f.pack();
    f.setVisible(true);
    textField.requestFocus();

    //textField.addActionListener(this);
}
 

 
 

  public void actionPerformed(ActionEvent evt) {
    String text = textField.getText();
    textArea.append(text + newline);
    textField.selectAll();
    textArea.setCaretPosition(textArea.getDocument().getLength());
 }  
}
 

 
