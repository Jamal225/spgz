import javax.swing.*;
import java.awt.*;

public class GUI {
    JFrame frame ;
    JPanel panel ;
    public JButton button_play;
    public JButton button_resume;
    public JButton button_pause;
    public JButton button_stop;
    JTextArea ta;

    public GUI(){
        frame = new JFrame("Player");
        panel = new JPanel();
        button_play = new JButton("Play");
        button_resume = new JButton("Resume");
        button_pause = new JButton("Pause");
        button_stop = new JButton("Stop");
        ta = new JTextArea();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);
        panel.add(button_play);
        panel.add(button_resume);
        panel.add(button_pause);
        panel.add(button_stop);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        //frame.setVisible(false);
    }

    public void setGUI(){
        frame.setVisible(true);
    }
}
