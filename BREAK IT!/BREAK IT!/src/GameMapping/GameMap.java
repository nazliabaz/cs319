/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameMapping;

import GameEntities.Bird;
import GameEntities.Column;
import GameEntities.MapObject;
import java.util.Random;

/**
 *
 * @author Usama Moin
 */


public class GameMap {
    private final MapObject player;
    private MapObject column;
    
    private CollisionManager collider;
    
    
    Random randomGenerator = new Random();
    
    private int columnPerLevelCount=2;
    
    
    //private int playerX=14,playerY=599;
    private final int defaultPlayerX=14,defaultPlayerY=599;
    private final int HEIGHT=1200;
    private final int WIDTH=1200;
    private int[][] map;
    private boolean gameStop=false;
    
    
    public GameMap(){
        map= new int[WIDTH][HEIGHT];
        for(int i=0;i<HEIGHT;i++){
            for(int j=0;j<WIDTH;j++){
                map[i][j]=0;
            }
        }
        
        collider=new CollisionManager();
        
        
        
        int randomInt = randomGenerator.nextInt(100);
        if(column==null && randomInt>25 && randomInt<35 ){
            column=new Column(1199,0);
            columnPerLevelCount--;
            
            for(int i=0;i<1200;i++){
                map[i][1199]=4;
            }
        
        
        }
        
        
        
        
        player=new Bird(14,599);
        
        
        for(int i=599;i<601;i++){
            for(int j=14;j<17;j++){
                map[i][j]=1; //represents player
            }
        }
    }
    
    public int[][] obtainMap(){
        return map;
    }
    
    public int returnPlayerX(){
        for(int i=0;i<1200;i++){
            if(map[599][i]==1){
               // playerX=i;
                player.setXPos(i);
            }
        }
        
        //return playerX;
        return player.getXPos();
    }
    
    public int returnPlayerY(){

        //return playerY;
        return player.getYPos();
    }
    
    public void playerMoveRight(){
        System.out.println("Player Moved Right");
       /* if(playerX<1000){
            map[599][playerX+4]+=1;
            map[600][playerX+4]+=1;
            map[599][playerX+5]+=1;
            map[600][playerX+5]+=1;
            
            map[599][playerX]-=1;
            map[600][playerX]-=1;
            map[599][playerX+1]-=1;
            map[600][playerX+1]-=1;

            playerX+=4;
        }*/
        
        if(player.getXPos()<1000){
            map[599][player.getXPos()+4]+=1;
            map[600][player.getXPos()+4]+=1;
            map[599][player.getXPos()+5]+=1;
            map[600][player.getXPos()+5]+=1;
            
            map[599][player.getXPos()]-=1;
            map[600][player.getXPos()]-=1;
            map[599][player.getXPos()+1]-=1;
            map[600][player.getXPos()+1]-=1;

            //playerX+=4;
            player.setXPos(player.getXPos()+4);
        }
        
        
    }
    
    
    
    public void playerMoveLeft(){
        System.out.println("Player Moved Left");
        /*if(playerX>14){
            playerX-=5;
            
            map[599][playerX]+=1;
            map[600][playerX]+=1;
            map[599][playerX+1]+=1;
            map[600][playerX+1]+=1;
            
            map[599][playerX+4]-=1;
            map[600][playerX+4]-=1;
            map[599][playerX+5]-=1;
            map[600][playerX+5]-=1;
            


            
        }*/
        
        if(player.getXPos()>14){
            //playerX-=5;
            player.setXPos(player.getXPos()-5);
            map[599][player.getXPos()]+=1;
            map[600][player.getXPos()]+=1;
            map[599][player.getXPos()+1]+=1;
            map[600][player.getXPos()+1]+=1;
            
            map[599][player.getXPos()+4]-=1;
            map[600][player.getXPos()+4]-=1;
            map[599][player.getXPos()+5]-=1;
            map[600][player.getXPos()+5]-=1;
            


            
        }
        
        
    }
    
    public void reinitialize(){
        //playerX=defaultPlayerX;
        player.setXPos(defaultPlayerX);
       // playerY=defaultPlayerY;
        player.setYPos(defaultPlayerY);
        for(int i=0;i<HEIGHT;i++){
            for(int j=0;j<WIDTH;j++){
                map[i][j]=0;
            }
        }
        
        for(int i=599;i<601;i++){
            for(int j=14;j<17;j++){
                map[i][j]=1; //represents player
            }
        }
        column=null;
    }

    public void update() {

        int randomInt = randomGenerator.nextInt(100);
        if(column==null && randomInt>25 && randomInt<35 ){
            column=new Column(1199,0);
            columnPerLevelCount--;
            
            for(int i=0;i<1200;i++){
                map[i][1199]=4;
            }
        
        
        }
        else if(column!=null){

                    column.setXPos(column.getXPos()-2);
                    int columnXCurrent=column.getXPos();
                    for(int i=0;i<1200;i++){
                        if(columnXCurrent>10){
                            map[i][columnXCurrent-2]+=4;
                            map[i][columnXCurrent]-=4;
                        }
                    }

                
        }

        
      collisionCheck();
        
        
        
        
    }
    
    public int returnColumnX(){
        if(column!=null && column.getDestroyedState()==false){

            for(int i=0;i<1200;i++){
                if(map[0][i]==4){
                   // playerX=i;
                    column.setXPos(i);
                    break;
                }
            }

            //return playerX;
            return column.getXPos();
        }
        else{
            return -5;
        }
    }
    
    public int returnColumnY(){

        //return playerY;
        return column.getYPos();
    }
    
    
    
    private void collisionCheck(){
        
        if(collider.checkForCollision(map)==1){//collision with columns detected
            
            //change player's health to 0 or game over
            gameStop=true;
            
        }   
    }

    public void playerShoot() {
        System.out.println("Player Can shoot here");
    }
    
    public boolean shouldGameStop(){
        return gameStop;
    }
    
    public void setGameStop(boolean value){
        gameStop=value;
    }
    
}
