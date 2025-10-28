package testeo;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Grabadora {

    private TargetDataLine targetDataLine;
    private AudioFileFormat.Type targetType = AudioFileFormat.Type.WAVE;
    private File audioFile;

    public void iniciarGrabacion() {
        try {
            AudioFormat audioFormat = getAudioFormat();
            DataLine.Info dataLineInfo = new DataLine.Info(TargetDataLine.class, audioFormat);
            targetDataLine = (TargetDataLine) AudioSystem.getLine(dataLineInfo);
            targetDataLine.open(audioFormat);
            targetDataLine.start();

            audioFile = new File("grabacion.wav");

            Thread captureThread = new Thread(new Capturador());
            captureThread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void detenerGrabacion() {
        if (targetDataLine != null) {
            targetDataLine.stop();
            targetDataLine.close();
        }
    }

    private AudioFormat getAudioFormat() {
        float sampleRate = 44100;
        int sampleSizeInBits = 16;
        int channels = 1;
        boolean signed = true;
        boolean bigEndian = false;
        return new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    }

    class Capturador implements Runnable {
        @Override
        public void run() {
            AudioFileFormat.Type fileType = targetType;
            File file = audioFile;

            try {
                targetDataLine.open();
                targetDataLine.start();
                AudioSystem.write(new AudioInputStream(targetDataLine), fileType, file);
            } catch (LineUnavailableException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Grabadora grabadora = new Grabadora();
        grabadora.iniciarGrabacion();
        System.out.println("Grabando... Presiona Enter para detener la grabación.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        grabadora.detenerGrabacion();
        System.out.println("Grabación detenida.");
    }
}
