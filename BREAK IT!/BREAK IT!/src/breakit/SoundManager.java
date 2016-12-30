/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package breakit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

class SoundManager  {
    protected Clip clip;
    protected AudioInputStream audioIn;
   
    SoundManager()
    {
        try {
            audioIn = AudioSystem.getAudioInputStream(SoundManager.class.getResourceAsStream("Nyan Cat.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        }
        catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    
    public void startBGMusic() 
    { 
        clip.start();
    }//Plays the background music
   
}
