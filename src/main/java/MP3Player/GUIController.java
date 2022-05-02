package MP3Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController {
    SoundPlayer soundPlayer;
    String musicName;
    FileChooser fileChooser;
    public void control(){
        GUI gui = new GUI();

        gui.button_pause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                soundPlayer.pause();
            }
        });

        gui.button_resume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                soundPlayer.resume();
            }
        });

        gui.button_stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                soundPlayer.stop();
            }
        });

        gui.button_play.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                soundPlayer.play(musicName);
            }
        });
        gui.button_open_file.addActionListener(new ActionListener() {
            public void  actionPerformed(ActionEvent e){new FileChooser();}

        });
        gui.setGUI();

    }

    public GUIController(SoundPlayer soundPlayer, String musicName){
        this.soundPlayer = soundPlayer;
        this.musicName = musicName;

    }

    public void open_file(){
        new FileChooser();
    }
}
