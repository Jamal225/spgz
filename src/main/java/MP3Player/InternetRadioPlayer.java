package MP3Player;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class InternetRadioPlayer implements StreamPlayer {
    private AudioInputStream audioInputStream;
    private AudioFileFormat audioFileFormat;
    private SourceDataLine sourceDataLine;
    private FloatControl gainControl;
    private Thread writingBuffer;

    @Override
    public void open(URL url) {
        InputStream inputStream = null;
        try {
            inputStream = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert inputStream != null;
        InputStream buff = new BufferedInputStream(inputStream);
        try {
            audioInputStream = AudioSystem.getAudioInputStream(inputStream);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        try {
            AudioFileFormat audioFileFormat = AudioSystem.getAudioFileFormat(inputStream);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        try {
            createLine();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
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
    public void play() {
        try {
            this.initLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (sourceDataLine != null && !sourceDataLine.isRunning()) {
            sourceDataLine.start();
        }

        writingBuffer = new Thread(() -> {
            byte[] bytesBuffer = new byte[4096];
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
        writingBuffer.start();
    }

    @Override
    public void stop() {
        sourceDataLine.stop();
        writingBuffer.interrupt();
    }

    @Override
    public void setVolume(int gain) {
        float newRange = (gainControl.getMaximum() - gainControl.getMinimum());
        float newGain = (((gain) * newRange) / 100) + gainControl.getMinimum();
        gainControl.setValue(newGain);
    }
}
