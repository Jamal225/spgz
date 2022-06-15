package MP3Player;


import javax.swing.*;
import java.awt.*;

public class GUI {
    private final JFrame frame;
    private JSlider volume;
    private final JTextField urlLabel;
    private final JLabel duration;
    private final JSlider sliderDuration;

    public GUI(Controller controller) {
        frame = new JFrame("Player");
        JPanel panel = new JPanel();
        JButton playButton = new JButton("Play");
        duration = new JLabel();
        sliderDuration = new JSlider();
        sliderDuration.setValue(0);
        sliderDuration.setMaximum(Settings.maxValueDurationSlider);
        sliderDuration.addChangeListener(e -> controller.changeTrackPosition(sliderDuration.getValue()));
        playButton.addActionListener(e -> {
            sliderDuration.setValue(0);
            controller.play();
            controller.setVolume(volume.getValue());
            duration.setText(controller.getTrackDuration());
        });
        JButton resumeButton = new JButton("Resume");
        resumeButton.addActionListener(e -> controller.resume());
        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> controller.pause());
        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(e -> controller.stop());
        JButton openFileButton = new JButton("Open File");
        openFileButton.addActionListener(e -> controller.saveFile());
        volume = new JSlider();
        volume.setMinimum(0);
        volume.setMaximum(Settings.maxValueVolumeSlider);
        volume.addChangeListener(e -> controller.setVolume(volume.getValue()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        urlLabel = new JTextField("url", 15);
        JButton open_url = new JButton("Open URL");
        open_url.addActionListener(e -> controller.saveURL(urlLabel.getText()));
        panel.add(duration);
        panel.add(sliderDuration);
        panel.add(playButton);
        panel.add(resumeButton);
        panel.add(pauseButton);
        panel.add(stopButton);
        panel.add(openFileButton);
        panel.add(volume);
        JList<String> sound_names = new JList<>();
        sound_names.setModel(controller.soundNames);
        sound_names.addListSelectionListener(e -> controller.open(sound_names.getSelectedValue()));
        panel.add(urlLabel);
        panel.add(open_url);
        frame.getContentPane().add(BorderLayout.BEFORE_FIRST_LINE, sound_names);
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
    }

    public void setGUI() {
        frame.setVisible(true);
    }
}

