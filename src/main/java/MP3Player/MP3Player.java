package MP3Player;

import javax.sound.sampled.*;
import java.io.IOException;

public class MP3Player implements SoundPlayer {
    private Clip player;
    private Long pauseLocation;
    private PlayerState playerState = PlayerState.STATE_STOP;
    private FloatControl volumeControl;

    public void play(AudioInputStream audioInputStream) {
        try {
            if (playerState != PlayerState.STATE_PAUSE && playerState != PlayerState.STATE_STOP)
                return;
            playerState = PlayerState.STATE_PLAYING;
            player = AudioSystem.getClip();
            player.open(audioInputStream);
            player.setFramePosition(0);
            volumeControl = (FloatControl) player.getControl(FloatControl.Type.MASTER_GAIN);
            player.start();
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public void pause() {
        if (playerState != PlayerState.STATE_PLAYING && playerState != PlayerState.STATE_RESUME)
            return;
        playerState = PlayerState.STATE_PAUSE;
        pauseLocation = player.getMicrosecondPosition();
        player.stop();
    }

    public void resume() {
        if (playerState != PlayerState.STATE_PAUSE)
            return;
        playerState = PlayerState.STATE_RESUME;
        player.setMicrosecondPosition(pauseLocation);
        player.start();
    }

    public void stop() {
        if (playerState == PlayerState.STATE_STOP)
            return;
        player.stop();
        player.close();
        pauseLocation = 0L;
        playerState = PlayerState.STATE_STOP;
    }

    public void setVolume(int gain) {
        if(volumeControl != null) {
            float newRange = (volumeControl.getMaximum() - volumeControl.getMinimum());
            float newGain = (((gain) * newRange) / 100) + volumeControl.getMinimum();
            volumeControl.setValue(newGain);
        }
    }
}
