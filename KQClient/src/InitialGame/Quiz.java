package InitialGame;
import java.awt.*;
import javax.swing.*;

 
public class Quiz extends JPanel {
   
    //�⺻ ������
    public Quiz() {    
    }
   
    //�̹��� ��ü �غ�
    ImageIcon backgroundImg = new ImageIcon("C:\\Users\\pc1\\Downloads\\back.png");
   
    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backgroundImg.getImage(), 0, 0, null);
        setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ����
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

                 setOpaque(false);//�׸��� ǥ���ϰ� ����,�����ϰ� ����

                 super.paintComponent(g);

            }

        };
*/
 


