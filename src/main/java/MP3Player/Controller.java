package MP3Player;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private SoundPlayer soundPlayer;
    public DefaultListModel<String> soundNames;
    private final StreamReceiver streamReceiver;
    private final Map<String, String> playContent;
    private String currentPlay;
    private boolean isUrl;
    private Long skipBytes = 0L;
    private int currentVolume;
    private String trackDuration;
    private int bytesAvailable;

    public Controller(StreamReceiver streamReceiver) {
        this.streamReceiver = streamReceiver;
        soundNames = new DefaultListModel<>();
        playContent = new HashMap<>();
    }

    public String getTrackDuration() {
        return trackDuration;
    }

    public void setVolume(int volume) {
        if (soundPlayer != null) {
            currentVolume = volume;
            soundPlayer.setVolume(volume);
        }
    }

    public void open(String content) {
        currentPlay = playContent.get(content);
    }

    public void saveFile() {
        IChooser chooser = new FileChooser();
        File file = chooser.getFile();
        if (file != null) {
            String fileName = file.getName();
            soundNames.addElement(fileName);
            if (!playContent.containsKey(fileName))
                playContent.put(file.getName(), file.getAbsolutePath());
            else {
                playContent.replace(fileName, file.getAbsolutePath());
            }
        }
    }

    private AudioInputStream getAudioInputStream() {
        AudioInputStream audioInputStream;
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
            soundNames.addElement(url);
            playContent.put(url, url);
        }
    }

    private String getCorrectStringDuration(int hours, int min, int sec) {
        String strDuration = "";
        if (hours > 1)
            strDuration += hours + ":";

        if (min < 10 && min > 1)
            strDuration += "0" + min + ":";
        else if (min > 1)
            strDuration += min + ":";
        else if (min == 0)
            strDuration += "00:";

        if (sec < 10)
            strDuration += "0" + sec;
        else
            strDuration += sec;
        return strDuration;
    }

    private String getDuration() {
        AudioFileFormat fileFormat = null;
        try {
            fileFormat = AudioSystem.getAudioFileFormat(new File(currentPlay));
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        String strDuration = "";
        if (fileFormat != null) {
            Map<?, ?> properties = fileFormat.properties();
            String key = "duration";
            Long microseconds = (Long) properties.get(key);
            int millisecond = (int) (microseconds / 1000);
            int sec = (millisecond / 1000) % 60;
            int min = ((millisecond / 1000) / 60) % 60;
            int hours = (millisecond / 1000) / 3600;
            strDuration = getCorrectStringDuration(hours, min, sec);
        }
        return strDuration;
    }

    public void changeTrackPosition(int skipDuration) {
        this.skipBytes = ((long) skipDuration * bytesAvailable) / 100;
        play();
        setVolume(currentVolume);
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
        if (!isUrl)
            trackDuration = getDuration();
        else
            trackDuration = "0:00";
        try {
            bytesAvailable = audioInputStream.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            audioInputStream.skip(skipBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            if (!isUrl) {
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
            if (!isUrl) {
                soundPlayer.pause();
            }
        }
    }
}
