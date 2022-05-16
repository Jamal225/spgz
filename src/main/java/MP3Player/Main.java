package MP3Player;

public class Main {
    public static void main(String[] args) {
        MP3Player mp3Player = new MP3Player();
        Controller controller = new Controller(mp3Player);
        GUI gui = new GUI(controller);
        gui.setGUI();
    }
}
