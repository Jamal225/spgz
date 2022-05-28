package MP3Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;

public interface SoundPlayer {
    void play() throws LineUnavailableException;

    void pause();

    void resume();

    void stop();

    void setVolume(int gain);
}
