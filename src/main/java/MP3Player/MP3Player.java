package MP3Player;

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
    private String lastPressButton;
    private PlayerState playerState = PlayerState.STATE_STOP;
    public void play(String pathToFile) {
        try {
            if (playerState != PlayerState.STATE_PAUSE && playerState != PlayerState.STATE_STOP)
                return;
            playerState = PlayerState.STATE_PLAYING;
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
            if (playerState != PlayerState.STATE_PLAYING && playerState != PlayerState.STATE_RESUME)
                return;
            playerState = PlayerState.STATE_PAUSE;
            pauseLocation = fileInputStream.available();
            player.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resume() {
        try {
            if (playerState != PlayerState.STATE_PAUSE)
                return;
            playerState = PlayerState.STATE_RESUME;
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
        if (playerState == PlayerState.STATE_STOP)
            return;
        player.close();
        songTotalLength = 0;
        pauseLocation = 0;
        playerState = PlayerState.STATE_STOP;
    }
}
