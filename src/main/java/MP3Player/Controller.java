package MP3Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private SoundPlayer soundPlayer;
    public DefaultListModel<String> model = new DefaultListModel<>();
    private final StreamReceiver streamReceiver = new StreamReceiver();
    private final Map<String, String> playContent = new HashMap<>();
    private String currentPlay;
    private boolean isUrl;

    public void volumeControl(int volume) {
        if (soundPlayer != null)
            soundPlayer.setVolume(volume);
    }

    public void open(String content) {
        currentPlay = playContent.get(content);
    }

    public void saveFile() {
        IChooser chooser = new FileChooser();
        File file = chooser.getFile();
        if (file != null) {
            String fileName = file.getName();
            model.addElement(fileName);
            if (!playContent.containsKey(fileName))
                playContent.put(file.getName(), file.getAbsolutePath());
            else {
                playContent.replace(fileName, file.getAbsolutePath());
            }
        }
    }

    private AudioInputStream getAudioInputStream() {
        AudioInputStream audioInputStream = null;
        if (currentPlay.contains("://")) {
            audioInputStream = streamReceiver.open(currentPlay);
            isUrl = true;
        } else {
            audioInputStream = streamReceiver.open(new File(currentPlay));
            isUrl = false;
        }
        return audioInputStream;
    }

    public void saveURL(String url) {
        if (url != null) {
            model.addElement(url);
            playContent.put(url, url);
        }
    }

    public void play() {
        if (soundPlayer != null) {
            stop();
        }
        if (currentPlay == null)
            return;
        AudioInputStream audioInputStream = getAudioInputStream();
        if (audioInputStream == null)
            return;
        try {
            soundPlayer = new MP3Player(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            soundPlayer.play();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        if (soundPlayer != null) {
           if(!isUrl){
                soundPlayer.resume();
            }
        }
    }

    public void stop() {
        if (soundPlayer != null) {
            soundPlayer.stop();
            soundPlayer = null;
        }
    }

    public void pause() {
        if (soundPlayer != null) {
            if(!isUrl){
                soundPlayer.pause();
            }
        }
    }
}
