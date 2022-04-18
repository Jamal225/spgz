import javax.swing.*;
import java.awt.*;

public class GUI {
    JFrame frame ;
    JPanel panel ;
    public JButton play ;
    public JButton resume ;
    public JButton pause ;
    public JButton stop ;
    JTextArea ta;

    public GUI(){
        frame = new JFrame("Player");
        panel = new JPanel();
        play = new JButton("Play");
        resume = new JButton("Resume");
        pause = new JButton("Pause");
        stop = new JButton("Stop");
        ta = new JTextArea();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);
        panel.add(play);
        panel.add(resume);
        panel.add(pause);
        panel.add(stop);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        //frame.setVisible(false);
    }

    public void setGUI(){
        frame.setVisible(true);
    }
}
