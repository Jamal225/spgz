package MP3Player;

public class Main {
    public static void main(String[] args) {
        StreamReceiver streamReceiver = new StreamReceiver();
        FileChooserFactory fileChooserFactory = new FileChooserFactory();
        SoundPlayerFactory soundPlayerFactory = new SoundPlayerFactory();
        Controller controller = new Controller(streamReceiver, soundPlayerFactory, fileChooserFactory);
        GUI gui = new GUI(controller);
        gui.setGUI();
    }
}
