package MP3Player;

import javax.sound.sampled.AudioInputStream;

public interface SoundPlayer {
    void play(AudioInputStream audioInputStream);

    void pause();

    void resume();

    void stop();

    void setVolume(int gain);
}
