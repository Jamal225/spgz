import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MP3Player{
    private AdvancedPlayer advancedPlayer;
    private Thread threadPlayer;
    private int pausedOnFrame = 0;
    private String file;
    private AudioDevice audioDevice;

    public void play(String file) throws IOException, JavaLayerException {
        this.file = file;
        audioDevice = new JavaSoundAudioDevice();
        FileInputStream fis = new FileInputStream(file);
        advancedPlayer = new AdvancedPlayer(fis, audioDevice);
        advancedPlayer.setPlayBackListener(new PlaybackListener() {
            @Override
            public void playbackFinished(PlaybackEvent event) {
                pausedOnFrame = audioDevice.getPosition();
            }
        });
        threadPlayer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    advancedPlayer.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPlayer.start();
    }

    public void pause(){
        advancedPlayer.stop();
    }

    public void resum() throws JavaLayerException, FileNotFoundException {
        audioDevice = new JavaSoundAudioDevice();
        FileInputStream fis = new FileInputStream(file);
        advancedPlayer = new AdvancedPlayer(fis, audioDevice);
        pausedOnFrame = pausedOnFrame / 25;
        advancedPlayer.setPlayBackListener(new PlaybackListener() {
            @Override
            public void playbackFinished(PlaybackEvent event) {
                pausedOnFrame = audioDevice.getPosition();
            }
        });
        threadPlayer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    advancedPlayer.play(pausedOnFrame, Integer.MAX_VALUE);
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        });
        threadPlayer.start();
    }
}
