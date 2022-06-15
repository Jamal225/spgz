package MP3Player;

import javax.sound.sampled.*;
import java.io.IOException;

public class MP3Player implements SoundPlayer {
    private AudioInputStream audioInputStream;
    private SourceDataLine sourceDataLine;
    private FloatControl gainControl;
    private Thread writingBuffer;
    private PlayerState playerState = PlayerState.STATE_STOP;
    private final int buff_size = 4096;

    public MP3Player(AudioInputStream audioInputStream) throws LineUnavailableException {
        this.audioInputStream = audioInputStream;
        createLine();
    }

    private void openLine() throws LineUnavailableException {
        AudioFormat audioFormat = audioInputStream.getFormat();
        int currentLineBufferSize = sourceDataLine.getBufferSize();
        sourceDataLine.open(audioFormat, currentLineBufferSize);
        if (sourceDataLine.isOpen())
            gainControl = sourceDataLine.isControlSupported(FloatControl.Type.MASTER_GAIN) ? (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN) : null;
    }

    private void initLine() throws LineUnavailableException {
        if (!sourceDataLine.isOpen()) {
            openLine();
        } else if (!sourceDataLine.getFormat().equals(audioInputStream == null ? null : audioInputStream.getFormat())) {
            sourceDataLine.close();
            openLine();
        }

    }

    private void createLine() throws LineUnavailableException {
        if (sourceDataLine != null)
            return;
        AudioFormat sourceFormat = audioInputStream.getFormat();
        int nSampleSizeInBits = sourceFormat.getSampleSizeInBits();
        if (sourceFormat.getEncoding() == AudioFormat.Encoding.ULAW || sourceFormat.getEncoding() == AudioFormat.Encoding.ALAW
                || nSampleSizeInBits != 8) {
            nSampleSizeInBits = 16;
        }
        AudioFormat targetFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, (float) ((double) sourceFormat.getSampleRate()),
                nSampleSizeInBits, sourceFormat.getChannels(), nSampleSizeInBits / 8 * sourceFormat.getChannels(),
                sourceFormat.getSampleRate(), false);
        audioInputStream = AudioSystem.getAudioInputStream(targetFormat, audioInputStream);
        DataLine.Info lineInfo = new DataLine.Info(SourceDataLine.class, audioInputStream.getFormat(), -1);
        sourceDataLine = (SourceDataLine) AudioSystem.getLine(lineInfo);
    }

    @Override
    public void play() throws LineUnavailableException {
        if (playerState != PlayerState.STATE_STOP)
            return;
        playerState = PlayerState.STATE_PLAYING;
        initLine();
        if (sourceDataLine != null) {
            sourceDataLine.start();
        }
        writingBuffer = new Thread(() -> {
            byte[] bytesBuffer = new byte[buff_size];
            int bytesRead = -1;
            while (!writingBuffer.isInterrupted()) {
                try {
                    if ((bytesRead = audioInputStream.read(bytesBuffer)) == -1) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sourceDataLine.write(bytesBuffer, 0, bytesRead);
            }
        });
        writingBuffer.setDaemon(true);
        writingBuffer.start();
    }

    @Override
    public void pause() {
        if (playerState != PlayerState.STATE_PLAYING)
            return;
        playerState = PlayerState.STATE_PAUSE;
        sourceDataLine.stop();
        writingBuffer.interrupt();
    }

    @Override
    public void resume() {
        if (playerState != PlayerState.STATE_PAUSE)
            return;
        playerState = PlayerState.STATE_PLAYING;
        if (sourceDataLine != null) {
            sourceDataLine.start();
        }
        writingBuffer = new Thread(() -> {
            byte[] bytesBuffer = new byte[buff_size];
            int bytesRead = -1;
            while (!writingBuffer.isInterrupted()) {
                try {
                    if ((bytesRead = audioInputStream.read(bytesBuffer)) == -1) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                sourceDataLine.write(bytesBuffer, 0, bytesRead);
            }
        });
        writingBuffer.setDaemon(true);
        writingBuffer.start();
    }

    @Override
    public void stop() {
        sourceDataLine.stop();
        writingBuffer.interrupt();
        playerState = PlayerState.STATE_STOP;
    }

    @Override
    public void setVolume(int gain) {
        float newRange = (gainControl.getMaximum() - gainControl.getMinimum());
        float newGain = (((gain) * newRange) / 100) + gainControl.getMinimum();
        gainControl.setValue(newGain);
    }
}
