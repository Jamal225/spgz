package MP3Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MP3Player mp3Player = new MP3Player();
        Scanner scanner = new Scanner(System.in);
        //String fileName = "C:\\Juice_WRLD_Lil_Uzi_Vert_-_Wasted_Murkish__Huken_Nightcore_Remix.mp3";
        GUIController controller = new GUIController(mp3Player);
        controller.control();
    }
}
