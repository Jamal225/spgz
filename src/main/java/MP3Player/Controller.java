package MP3Player;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;

public class Controller {
    SoundPlayer soundPlayer;
    MP3Decoder mp3Decoder;
    File file;
    DefaultListModel<String> model = new DefaultListModel<>();
    public void volumeControl(int volume){
        soundPlayer.setVolume(volume);
    }

    public void openFile(){
        var fileChooser = new FileChooser();
        file = fileChooser.getFile();
        model.addElement(file.getName());
    }

    public void play(){
        soundPlayer.play(file);
    }

    public void resume(){
        soundPlayer.resume();
    }

    public void stop(){
        soundPlayer.stop();
    }

    public void pause(){
        soundPlayer.pause();
    }

    public Controller(SoundPlayer soundPlayer) {
        this.soundPlayer = soundPlayer;
        mp3Decoder = new MP3Decoder();
    }
}
