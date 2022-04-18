public class GUIController {
    SoundPlayer soundPlayer;
    String musicName;
    public void control(){
        GUI gui = new GUI();
        AListener actionListener = new AListener(soundPlayer, musicName);
        gui.button_play.addActionListener(actionListener);
        gui.button_resume.addActionListener(actionListener);
        gui.button_pause.addActionListener(actionListener);
        gui.button_stop.addActionListener(actionListener);
        gui.setGUI();
    }

    public GUIController(SoundPlayer soundPlayer, String musicName){
        this.soundPlayer = soundPlayer;
        this.musicName = musicName;

    }


}
