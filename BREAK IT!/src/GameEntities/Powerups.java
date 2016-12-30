/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameEntities;

import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Usama Moin
 */
public class Powerups extends MapObject {
    private PowerupType type;
    private int disappearTime;
    private Image img;
    public Powerups(int x,int y,int typeNum){
        super(x,y);
        if(typeNum==1){
            type=PowerupType.MEAL;
            disappearTime=-5;
            img=Toolkit.getDefaultToolkit().getImage("mealPowerup.png");
        }
        else if(typeNum==2){
            type=PowerupType.MUSHROOM;
            disappearTime=400;
            img=Toolkit.getDefaultToolkit().getImage("mushroomPowerup.png");
        }
        else{
            type=PowerupType.IMMORTALITY;
            disappearTime=300;
            img=Toolkit.getDefaultToolkit().getImage("immortalityPowerup.png");
        }
    }
    
    public PowerupType getType(){
        return type;
    }
    
    public void reduceDisappearTime(){
        disappearTime--;
    }
    
    public int getDisappearTime(){
        return disappearTime;
    }
    
    public Image getImage(){
        return img;
    }
}
