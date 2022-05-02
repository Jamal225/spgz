package MP3Player;

import javax.sound.sampled.AudioInputStream;
import java.io.File;

public interface SoundPlayer {
    void play(File file);

    void pause();

    void resume();

    void stop();

    void setVolume(int gain);
}
