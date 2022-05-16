package MP3Player;

import java.io.InputStream;
import java.net.URL;

public interface StreamPlayer {
    void open(URL url);

    void play();

    void stop();

    void setVolume(int gain);
}
