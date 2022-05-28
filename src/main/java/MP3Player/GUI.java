package MP3Player;


import javax.swing.*;
import java.awt.*;

public class GUI {
    private final JFrame frame;
    private JSlider volume;
    private final JTextField label;

    public GUI(Controller controller) {
        frame = new JFrame("Player");
        JPanel panel = new JPanel();
        JButton button_play = new JButton("Play");
        button_play.addActionListener(e -> {
            controller.play();
            controller.volumeControl(volume.getValue());
        });
        JButton button_resume = new JButton("Resume");
        button_resume.addActionListener(e -> controller.resume());
        JButton button_pause = new JButton("Pause");
        button_pause.addActionListener(e -> controller.pause());
        JButton button_stop = new JButton("Stop");
        button_stop.addActionListener(e -> controller.stop());
        JButton button_open_file = new JButton("Open File");
        button_open_file.addActionListener(e -> {
            controller.saveFile();
        });
        volume = new JSlider();
        volume.setMinimum(0);
        volume.setMaximum(100);
        volume.addChangeListener(e -> controller.volumeControl(volume.getValue()));
        JTextArea ta = new JTextArea();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 100);
        label = new JTextField("url", 20);
        JButton open_url = new JButton("Open URL");
        open_url.addActionListener(e -> controller.saveURL(label.getText()));
        panel.add(button_play);
        panel.add(button_resume);
        panel.add(button_pause);
        panel.add(button_stop);
        panel.add(button_open_file);
        panel.add(volume);
        JList<String> sound_names = new JList<>();
        sound_names.setModel(controller.model);
        sound_names.addListSelectionListener(e -> controller.open(sound_names.getSelectedValue()));
        panel.add(label);
        panel.add(open_url);
        frame.getContentPane().add(BorderLayout.BEFORE_FIRST_LINE, sound_names);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
    }

    public void setGUI() {
        frame.setVisible(true);
    }
}

