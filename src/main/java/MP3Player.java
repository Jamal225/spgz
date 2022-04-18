import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class MP3Player implements SoundPlayer {
    private Player player;
    private int songTotalLength;
    private int pauseLocation;
    private BufferedInputStream bufferedInputStream;
    private FileInputStream fileInputStream;
    private String pathToFile;
    private Thread threadPlayer;

    public void play(String pathToFile) {
        try {
            this.pathToFile = pathToFile;
            fileInputStream = new FileInputStream(pathToFile);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            player = new Player(bufferedInputStream);
            songTotalLength = fileInputStream.available();
            threadPlayer = new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            });
            threadPlayer.setDaemon(true);
            threadPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        try {
            pauseLocation = fileInputStream.available();
            player.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        try {
            fileInputStream = new FileInputStream(pathToFile);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            bufferedInputStream.skip(songTotalLength - pauseLocation);
            player = new Player(bufferedInputStream);
            threadPlayer = new Thread(() -> {
                try {
                    player.play(Integer.MAX_VALUE);
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            });
            threadPlayer.setDaemon(true);
            threadPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        player.close();
        songTotalLength = 0;
        pauseLocation = 0;
    }
}
