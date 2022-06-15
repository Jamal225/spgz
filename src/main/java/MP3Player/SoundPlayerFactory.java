package MP3Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;

public class SoundPlayerFactory {
    public SoundPlayer createSoundPlayer(AudioFileFormat audioFormat, AudioInputStream audioInputStream) throws LineUnavailableException {
        SoundPlayer soundPlayer = null;
        switch (audioFormat){
            case MP3 -> soundPlayer = new MP3Player(audioInputStream);
        }
        return soundPlayer;
    }
}
