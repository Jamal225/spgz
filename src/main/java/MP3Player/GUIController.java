package MP3Player;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;

public class GUIController {
    SoundPlayer soundPlayer;
    MP3Decoder mp3Decoder;
    File file;

    public void control() {
        GUI gui = new GUI();
        gui.volume.setMinimum(0);
        gui.volume.setMaximum(100);
        gui.volume.addChangeListener(e -> soundPlayer.setVolume(((JSlider)e.getSource()).getValue()));
        gui.button_pause.addActionListener(e -> soundPlayer.pause());

        gui.button_resume.addActionListener(e -> soundPlayer.resume());

        gui.button_stop.addActionListener(e -> soundPlayer.stop());

        gui.button_play.addActionListener(e -> soundPlayer.play(file));
        gui.button_open_file.addActionListener(e -> {
            var fileChooser = new FileChooser();
            file = fileChooser.getFile();
        });
        gui.setGUI();

    }

    public GUIController(SoundPlayer soundPlayer) {
        this.soundPlayer = soundPlayer;
        mp3Decoder = new MP3Decoder();
    }
}
