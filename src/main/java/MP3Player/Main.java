package MP3Player;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        GUI gui = new GUI(controller);
        gui.setGUI();
    }
}
