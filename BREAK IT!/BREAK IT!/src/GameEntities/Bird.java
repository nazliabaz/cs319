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
public class Bird extends MapObject{
 
    private BirdType type;
    private int remainLive;
    private int bonusDuration;
    private Image images[];
    
    public Bird(int x,int y){
        super(x,y);
        
        type=BirdType.MARGARET;
        remainLive=200;
        bonusDuration=0;
        images=new Image[2];
        images[0]=Toolkit.getDefaultToolkit().getImage("margaret.png");
        images[1]=Toolkit.getDefaultToolkit().getImage("abraham.png");
        

    }
}
