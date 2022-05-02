package MP3Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ButtonController {

    private JButton btnPlay;
    private JButton btnStop;
    private JButton btnResume;
    private JButton btnPause;
    private JButton btnOpenDir;
    SoundPlayer soundPlayer;
    String filename;

    public ButtonController() {
    }

    public  void ActionButton()
    {
        btnPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                soundPlayer.pause();
            }
        });

        btnResume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              soundPlayer.resume();
            }
        });

        btnStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                soundPlayer.stop();
            }
        });

        btnPlay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                soundPlayer.play(filename);
            }
        });

//        btnOpenDir.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                new FileChooser();
//            }
//        });
    }

}
