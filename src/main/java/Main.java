import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MP3Player mp3Player = new MP3Player();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int x = scanner.nextInt();
            if (x == 1) {
                mp3Player.play("A:\\Альбом\\Для видео\\Planet Ragtime - Don't Lose Faith.mp3");
            }
            if (x == 2) {
                mp3Player.pause();
            }
            if (x == 3) {
                mp3Player.resume();
            }
            if (x == 4) {
                mp3Player.stop();
            }
        }
    }
}
