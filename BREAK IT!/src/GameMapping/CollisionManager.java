/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameMapping;

/**
 *
 * @author Usama Moin
 */
public class CollisionManager {
    //bullet is 3 //columns are 4 //player is 1
    public int checkForCollision(int[][] map){

        
        for(int i=0;i<600;i++){
            for(int j=0;j<600;j++){
                if(map[i][j]==7){
                    System.out.println("Bullet Collided with Column i is "+i + " and j is "+j);
                    map[i][j]-=3;
                    return 2;//collision of bullet with column
                }
            }
        }
        
        
        for (int i=0;i<600;i++){
            for(int j=14/2;j<17/2;j++){
                if(map[i][j]==5){//player+column
                    System.out.println("Collision with columns detected");
                    return 1;//collision with column
                }
            }
            
         
        }
        

        
        
        
        
        return 0;//no collision
    }

}
