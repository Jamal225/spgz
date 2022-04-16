import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.JavaSoundAudioDevice;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Scanner;

public class Main {
    private static int pausedOnFrame = 0;

    public static void main(String[] args) throws IOException, JavaLayerException, UnsupportedAudioFileException, LineUnavailableException {
        MP3Player mp3Player = new MP3Player();
        Scanner scanner = new Scanner(System.in);
        while (true){
            int x = scanner.nextInt();
            if (x == 1){
                mp3Player.play("A:\\Альбом\\Для видео\\Planet Ragtime - Don't Lose Faith.mp3");
            }
            if (x == 2){
                mp3Player.pause();
            }
            if (x == 3){
                mp3Player.resum();
            }
        }
    }
}
