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
    
    public int checkForCollision(int[][] map){
        for (int i=599;i<601;i++){
            for(int j=0;j<1200;j++){
                if(map[i][j]==5){
                    System.out.println("Collision with columns detected");
                    return 1;//collision with column
                }
            }
        }
        return 0;//no collision
    }

}
