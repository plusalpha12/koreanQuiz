package InitialGame;
import java.awt.*;
import javax.swing.*;

 
public class Quiz extends JPanel {
   
    //기본 생성자
    public Quiz() {    
    }
   
    //이미지 객체 준비
    ImageIcon backgroundImg = new ImageIcon("C:\\Users\\pc1\\Downloads\\back.png");
   
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImg.getImage(), 0, 0, null);
        setOpaque(false);//그림을 표시하게 설정,투명하게 조절
        super.paintComponent(g);
   }
   
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.add(new Quiz());
       
        f.setSize(400, 300);
        f.setVisible(true);
    }
}

/*

backgroundImg = new ImageIcon("C:\\Users\\pc1\\Downloads\\back.png");

        JPanel background = new JPanel() {

            public void paintComponent(Graphics g) {

                 g.drawImage(backgroundImg.getImage(), 0, 0, null);

                 setOpaque(false);//그림을 표시하게 설정,투명하게 조절

                 super.paintComponent(g);

            }

        };
*/
 


