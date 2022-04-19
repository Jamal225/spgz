import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AListener implements ActionListener {
    SoundPlayer soundPlayer;
    String musicName;

    @Override
    public void actionPerformed(ActionEvent e) {

        if (Objects.equals(e.getActionCommand(), "Play")) {
            soundPlayer.play(musicName);
        }
        if (Objects.equals(e.getActionCommand(), "Stop")){
            soundPlayer.stop();
        }
        if (Objects.equals(e.getActionCommand(), "Pause")){
            soundPlayer.pause();
        }
        if (Objects.equals(e.getActionCommand(), "Resume")){
            soundPlayer.resume();
        }

    }

    public AListener(SoundPlayer soundPlayer,String musicName ){
        this.soundPlayer = soundPlayer;
        this.musicName = musicName;
    }
}
