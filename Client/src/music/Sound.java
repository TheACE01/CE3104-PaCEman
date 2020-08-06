package music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Can crete a new Thread and play .wav files when a file path is given
 * @author kevin Avevedo
 */
public class Sound {

    /**
     * Play the wav files using Threads
     * @param fileName The path of the file
     */
    public static synchronized void play(final String fileName)
    {
        // Note: use .wav files
        new Thread(new Runnable() {
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(fileName));
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    System.out.println("play sound error: " + e.getMessage() + " for " + fileName);
                }
            }
        }).start();
    }
}