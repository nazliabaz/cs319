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
    //bullet is 3 //columns are 4 //player is 1 //enemy is 9 // enemy bullet is 25 //power is 50
    public int checkForCollision(int[][] map){

        
        for(int i=0;i<600;i++){
            for(int j=0;j<600;j++){
                if(map[i][j]==7){
                    System.out.println("Bullet Collided with Column i is "+i + " and j is "+j);
                    map[i][j]-=3;
                    return 2;//collision of bullet with column
                }
                else if(map[i][j]==12){
                    System.out.println("Bullet Collided with Enemy i is "+i + " and j is "+j);
                    map[i][j]-=3;
                    return 3;//collision of bullet with enemy
                }
                else if(map[i][j]==10){
                    System.out.println("Player Collided with Enemy i is "+i + " and j is "+j);
                    map[i][j]-=9;
                    return 4;//collision of player with enemy
                }
                else if(map[i][j]==26){
                    System.out.println("Player Collided with Enemy Bullet i is "+i + " and j is "+j);
                    map[i][j]-=25;
                    return 5;//collision of player with enemy bullet
                }
                else if(map[i][j]==29){
                    System.out.println("Column Collided with Enemy Bullet i is "+i + " and j is "+j);
                    map[i][j]-=25;
                    //return 5;//collision of player with enemy bullet
                }
                else if(map[i][j]==28){
                    System.out.println("Bullet Collided with Enemy Bullet i is "+i + " and j is "+j);
                    //map[i][j]-=25;
                    //return 5;//collision of player with enemy bullet
                }
                else if(map[i][j]==53){
                    System.out.println("Bullet Collided with Powerup i is "+i + " and j is "+j);
                    //map[i][j]-=50;
                    return 6;//collision of bullet with powerup
                }
                else if(map[i][j]>50){
                    System.out.println("Powerup collided with something i is "+i + " and j is "+j);
                    //map[i][j]=50;
                   // return 6;//collision of bullet with powerup
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
