package MP3Player;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class StreamReceiver {
    public AudioInputStream open(String string_url) {
        URL url = null;
        try {
            url = new URL(string_url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        InputStream inputStream = null;
        try {
            assert url != null;
            inputStream = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert inputStream != null;
        AudioInputStream audioInputStream = null;
        InputStream buff = new BufferedInputStream(inputStream);
        try {
            audioInputStream = AudioSystem.getAudioInputStream(buff);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        return audioInputStream;
    }

    public AudioInputStream open(File file){
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        return audioInputStream;
    }
}
