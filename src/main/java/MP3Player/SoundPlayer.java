package MP3Player;

import javax.sound.sampled.LineUnavailableException;

public interface SoundPlayer {
    void play() throws LineUnavailableException;

    void pause();

    void resume();

    void stop();

    void setVolume(int gain);
}
