/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package breakit;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;



/**
 *
 * @author Usama Moin
 */
class SoundManager  {
   
    private Clip clip;
    private boolean musicOn=true;

    public SoundManager(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File("music.wav"));
            clip = AudioSystem.getClip();
            
            clip.open(audioInputStream);
            
            clip.start();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public void stopMusic(){
        clip.stop();
    }

    public void startMusic(){
        clip.start();
        musicOn=true;
    }
    
    public void switchState(){
        if(musicOn==true){
            stopMusic();
            musicOn=false;
        }
        else{
            startMusic();
            musicOn=true;
        }
    }
    
    


}
