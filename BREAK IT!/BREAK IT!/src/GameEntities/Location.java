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
public class Location {
    private int positionX;
    private int positionY;
    
    Location(int x,int y){
        positionX=x;
        positionY=y;
    }
    
    public void setX(int x){
        positionX=x;
    }
    
    public void setY(int y){
        positionY=y;
    }
    
    public int getX(){
        return positionX;
    }
    
    public int getY(){
        return positionY;
    }
}
