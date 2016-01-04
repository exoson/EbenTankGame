
package Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.*;

public class Sound 
{
    private static final ArrayList<Clip> clips = new ArrayList<>();
    private Clip clip;
    
    public Sound(String track) {
        try {
            clip = AudioSystem.getClip();
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File("res/sounds/" + track + ".wav"));
            
            clip.open(ais);
            clips.add(clip);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(Sound.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void playClip() {
        clip.loop(0);
    }
    public void startLooping() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void endLooping() {
        clip.stop();
    }
    public static void resetSounds() {
        for(Clip c : clips) {
            c.stop();
        }
        clips.removeAll(clips);
    }
}
