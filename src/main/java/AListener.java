import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AListener implements ActionListener {
    SoundPlayer soundPlayer;
    String musicName;
    @Override
    public void actionPerformed(ActionEvent e) {
        soundPlayer.play(musicName);
    }

    public AListener(SoundPlayer soundPlayer,String musicName ){
        this.soundPlayer = soundPlayer;
        this.musicName = musicName;
    }
}
