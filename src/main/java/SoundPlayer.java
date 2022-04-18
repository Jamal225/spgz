public interface SoundPlayer {
    void play(String pathToFile);

    void pause();

    void resume();

    void stop();

    String getLastPressedButton();
}
