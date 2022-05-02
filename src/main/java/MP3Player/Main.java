package MP3Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MP3Player mp3Player = new MP3Player();
        Scanner scanner = new Scanner(System.in);
        String fileName = "C:\\Juice_WRLD_Lil_Uzi_Vert_-_Wasted_Murkish__Huken_Nightcore_Remix.mp3";
        GUIController controller = new GUIController(mp3Player, fileName);
        controller.control();
     //   while (true) {
      //      int x = scanner.nextInt();
      //      if (x == 1) {
      //          mp3Player.play("C:\\Users\\1\\Desktop\\Joy_Division_-_Atrocity_Exhibition_48117435 (1).mp3");
      //      }
      //      if (x == 2) {
          //      mp3Player.pause();
        //    }
        //    if (x == 3) {
        //        mp3Player.resume();
        //    }
         //   if (x == 4) {
       //         mp3Player.stop();
       //     }
     //   }
    }
}
