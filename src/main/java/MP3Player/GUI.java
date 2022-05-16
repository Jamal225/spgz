package MP3Player;


import javax.swing.*;
import java.awt.*;

public class GUI {
    JFrame frame ;
    JPanel panel ;
    public JButton button_play;
    public JButton button_resume;
    public JButton button_pause;
    public JButton button_stop;
    public JButton button_open_file;
    public JSlider volume;
    JTextArea ta;

    public GUI(Controller controller){
        frame = new JFrame("Player");
        panel = new JPanel();
        button_play = new JButton("Play");
        button_play.addActionListener(e -> controller.play());
        button_resume = new JButton("Resume");
        button_resume.addActionListener(e -> controller.resume());
        button_pause = new JButton("Pause");
        button_pause.addActionListener(e -> controller.pause());
        button_stop = new JButton("Stop");
        button_stop.addActionListener(e -> controller.stop());
        button_open_file = new JButton("Open File");
        button_open_file.addActionListener(e -> controller.openFile());
        volume = new JSlider();
        volume.setMinimum(0);
        volume.setMaximum(100);
        volume.addChangeListener(e -> controller.volumeControl(((JSlider)e.getSource()).getValue()));
        ta = new JTextArea();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 100);
        panel.add(button_play);
        panel.add(button_resume);
        panel.add(button_pause);
        panel.add(button_stop);
        panel.add(button_open_file);
        panel.add(volume);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        //frame.setVisible(false);
        controller.play();
    }

    public void setGUI(){
        frame.setVisible(true);
    }
}
