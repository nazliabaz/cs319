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
public class Enemy extends MapObject {
    private EnemyType type;
    private int remainLife;
    private int attackPower;
    private boolean destroyed;
    private Image img;
    
    public Enemy(int x,int y,int enemType){
        super(x,y);
        if(enemType<1){
            type=EnemyType.CAT;
            remainLife=25;
            attackPower=25;
            img=Toolkit.getDefaultToolkit().getImage("cat.png");
        }
        else{
            type=EnemyType.HUMAN;
            remainLife=75;
            attackPower=45;
            img=Toolkit.getDefaultToolkit().getImage("human.png");
        }
        //destroyed=false;
    }
    
    
    @Override
    public void decreaseLife(int amount){
        remainLife-=amount;
        if(remainLife<=0){
            isDestroyed();
        }
    }
    
    
     @Override
    public void isDestroyed(){
            //super.setXPos(599);
            destroyed=true;
            remainLife=25;
    }
    
    
    @Override
     public boolean getDestroyedState(){
        return destroyed;
    }
     
     public EnemyType getType(){
         return type;
     }
     
     public int returnAttackPower(){
         return attackPower;
     }
     
     public Image getImage(){
         return img;
     }
    
}
