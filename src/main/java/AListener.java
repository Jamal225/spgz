import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AListener implements ActionListener {
    SoundPlayer soundPlayer;
    String musicName;

    @Override
    public void actionPerformed(ActionEvent e) {
        var lastPressedButton = soundPlayer.getLastPressedButton();
        if (e.getActionCommand()=="Play" && lastPressedButton != "Play"
                && lastPressedButton != "Resume") {
            soundPlayer.play(musicName);
        }
        if (e.getActionCommand()=="Stop" && ( lastPressedButton == "Play"
                || lastPressedButton == "Resume")){
            soundPlayer.stop();
        }
        if (e.getActionCommand()=="Pause" && ( lastPressedButton == "Play"
                || lastPressedButton == "Resume")){
            soundPlayer.pause();
        }
        if (e.getActionCommand()=="Resume" && lastPressedButton == "Pause"){
            soundPlayer.resume();
        }

    }

    public AListener(SoundPlayer soundPlayer,String musicName ){
        this.soundPlayer = soundPlayer;
        this.musicName = musicName;
    }
}
