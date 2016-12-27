/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameEntities;

/**
 *
 * @author Usama Moin
 */
public class MapObject {
    Location objLocation;

    
    public MapObject(){
        objLocation=new Location(0,0);
    }

    
    
     public MapObject(int x,int y){
        objLocation=new Location(x,y);

    }
     
     public int getXPos(){
         return objLocation.getX();
     }

      
     public int getYPos(){
         return objLocation.getY();
     }
     
     public void setXPos(int x){
         objLocation.setX(x);
     }
     
     public void setYPos(int y){
         objLocation.setY(y);
     }
     
     
    public void isDestroyed(){

    }
    
    public boolean getDestroyedState(){
        return false;
    }
    
    
    public void decreaseLife(int amount){
        
    }
    
    
     
}
