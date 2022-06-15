package MP3Player;

public class Main {
    public static void main(String[] args) {
        StreamReceiver streamReceiver = new StreamReceiver();
        Controller controller = new Controller(streamReceiver);
        Settings settings = new Settings();
        GUI gui = new GUI(controller, settings);
        gui.setGUI();
    }
}
