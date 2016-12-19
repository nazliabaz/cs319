/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InputManagement;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Usama Moin
 */
public class InputManager implements KeyListener {
    
    private String buttonList[]={"38"/*Up*/,"40"/*down*/,"37"/*left*/,"39"/*right*/,"32"/*space*/,"10"/*enter*/,"27"};
    private String defaultButtonList[]={"38"/*Up*/,"40"/*down*/,"37"/*left*/,"39"/*right*/,"32"/*space*/,"10"/*enter*/,"27"};
    /*
        Index 0 signifies Up
        Index 1 signifies Down
        Index 2 signifies Left
        Index 3 signifies Right
        Index 4 signifies Shoot
        Index 5 signifies Select
    */
    private boolean pressedKeys[]={false,false,false,false,false,false,false};
    
    public InputManager(){
        
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println("Key pressed code=" + e.getKeyCode() + ", char=" + e.getKeyChar());
        for(int i=0;i<7;i++){
            if(buttonList[i].equals(Integer.toString(e.getKeyCode()))){
                pressedKeys[i]=true;
                System.out.println("value at "+i+" set to true ");
            }
        }
    
    }

    @Override
    public void keyReleased(KeyEvent e) {
 
    }
    
    
    public boolean[] getButtonsList(){
        return pressedKeys;
    }
    
    public void keyHandled(int indexValue){
        pressedKeys[indexValue]=false;
    }
    
    public void setToDefaults(){
        for(int i=0;i<7;i++){
            buttonList[i]=defaultButtonList[i];
            //Not TESTED YET
        }
        System.out.println("Default settings set");
    }

}
