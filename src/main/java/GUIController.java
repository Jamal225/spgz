

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUIController {
    SoundPlayer soundPlayer;
    String musicName;
    public void control(){
        GUI gui = new GUI();
        AListener actionListener = new AListener(soundPlayer, musicName);
        gui.play.addActionListener(actionListener);
        gui.resume.addActionListener(actionListener);
        gui.pause.addActionListener(actionListener);
        gui.stop.addActionListener(actionListener);
        gui.setGUI();
    }

    public GUIController(SoundPlayer soundPlayer, String musicName){
        this.soundPlayer = soundPlayer;
        this.musicName = musicName;

    }


}
