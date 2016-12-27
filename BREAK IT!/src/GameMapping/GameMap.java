/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameMapping;

import GameEntities.Bird;
import GameEntities.Column;
import GameEntities.Enemy;
import GameEntities.MapObject;
import GameEntities.Weapons;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import static java.lang.System.exit;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usama Moin
 */


public class GameMap {
    private final MapObject player;
    private MapObject column;
    
    private CollisionManager collider;
    
    
    Random randomGenerator = new Random();
    
    private int columnPerLevelCount=3;
    private int enemyPerLevelCount=3;
    
    
    //private int playerX=14,playerY=599;
    private final int defaultPlayerX=7,defaultPlayerY=599/2;
    private final int HEIGHT=600;
    private final int WIDTH=600;
    private int[][] map;
    private boolean gameStop=false;
    private int bulletX,bulletY;
    private Weapons bullet;
    private boolean bulletLaunched=false;
    private Enemy enemy1,enemy2;
    
    
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
            column=new Column(599,0);
            columnPerLevelCount--;
            
            for(int i=0;i<600;i++){
                map[i][599]=4;
            }
        
        
        }
        
        
        
        
        player=new Bird(14,599/2);
        
        
        for(int i=599/2;i<601/2;i++){
            for(int j=7;j<8;j++){
                map[i][j]=1; //represents player
            }
        }
    }
    
    public int[][] obtainMap(){
        return map;
    }
    
    public int returnPlayerX(){
        for(int i=0;i<1200/2;i++){
            if(map[599/2][i]==1){
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
    

    
    
    public void playerMoveUp(){
        if(player.getYPos()>30){
            player.setYPos(player.getYPos()-5);
            map[player.getYPos()][14]+=1;
            map[player.getYPos()][15]+=1;
            map[player.getYPos()+1][14]+=1;
            map[player.getYPos()+1][15]+=1;

            map[player.getYPos()+4][14]-=1;
            map[player.getYPos()+4][15]-=1;
            map[player.getYPos()+5][14]-=1;
            map[player.getYPos()+5][15]-=1;

            System.out.println(player.getYPos());
        }
        
        
        
    }
    
    public void playerMoveDown(){
        if(player.getYPos()<550){
            player.setYPos(player.getYPos()+5);
            map[player.getYPos()][14]+=1;
            map[player.getYPos()][15]+=1;
            map[player.getYPos()+1][14]+=1;
            map[player.getYPos()+1][15]+=1;

            map[player.getYPos()-4][14]-=1;
            map[player.getYPos()-4][15]-=1;
            map[player.getYPos()-5][14]-=1;
            map[player.getYPos()-5][15]-=1;

            System.out.println(player.getYPos());
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
        
        for(int i=599/2;i<601/2;i++){
            for(int j=14/2;j<17/2;j++){
                map[i][j]=1; //represents player
            }
        }
        column=null;
        columnPerLevelCount=3;
        enemyPerLevelCount=3;
    }

    public void update() {

        int randomInt = randomGenerator.nextInt(400);
        int bulletSpeed=4;
        
        if(column==null && randomInt>22 && randomInt<24 && columnPerLevelCount>0 ){
            column=new Column(599,0);
            columnPerLevelCount--;
            
            for(int i=0;i<600;i++){
                map[i][599]=4;//4 for columns
                map[i][598]=4;
            }
        
        
        }
        else if(column!=null){
            
            

                    
                    int columnXCurrent=column.getXPos();
                    column.setXPos(column.getXPos()-1);
                    for(int i=0;i<600;i++){
                        if(columnXCurrent>5){
                            map[i][columnXCurrent-1]+=4;
                            map[i][columnXCurrent]-=4;

                        }
                    }

                
        }
        
        if(randomInt==92 && (enemy1==null || enemy2==null) && enemyPerLevelCount>0){
            int randomIntForPosition = randomGenerator.nextInt(500)+25;
            if(enemy1==null){
                enemy1=new Enemy(randomIntForPosition,598);
                enemyPerLevelCount--;
                
                map[randomIntForPosition][598]+=9;
                map[randomIntForPosition][599]+=9;
                map[randomIntForPosition-1][598]+=9;
                map[randomIntForPosition-1][599]+=9;
                System.out.println("Enemy created");
            }
        }
        else if(enemy1!= null || enemy2!= null){
            if(enemy1!=null){
                int enemCurrX=enemy1.getXPos();
                int enemCurrY=enemy1.getYPos();
                if(enemCurrY>5){
                    map[enemCurrX][enemCurrY-1]-=9;
                    map[enemCurrX][enemCurrY]-=9;
                    map[enemCurrX-1][enemCurrY-1]-=9;
                    map[enemCurrX-1][enemCurrY]-=9;

                    enemy1.setYPos(enemy1.getYPos()-1);

                    map[enemCurrX][enemCurrY-1]+=9;
                    map[enemCurrX][enemCurrY]+=9;
                    map[enemCurrX-1][enemCurrY-1]+=9;
                    map[enemCurrX-1][enemCurrY]+=9;
                }
                else{
                    map[enemCurrX][enemCurrY-1]-=9;
                    map[enemCurrX][enemCurrY]-=9;
                    map[enemCurrX-1][enemCurrY-1]-=9;
                    map[enemCurrX-1][enemCurrY]-=9;
                    enemy1=null;
                    
                }
                
                
                
                //System.out.println("Enemy created");
                
                
            }
        }
        
        if(bullet!=null){
            if(bullet.getYPos()!=0){
                if(bullet.getXPos()<590 && bullet.getYPos()<590){
                    int bulletXCurrent=bullet.getXPos();
                    int bulletYCurrent=bullet.getYPos();
                    

                    map[bullet.getXPos()][bullet.getYPos()]-=3;
                    map[bullet.getXPos()][bullet.getYPos()-1]-=3;
                    map[bullet.getXPos()][bullet.getYPos()-2]-=3;
                    map[bullet.getXPos()][bullet.getYPos()-3]-=3;
                    map[bullet.getXPos()][bullet.getYPos()-4]-=3;
 
                    bullet.setYPos(bullet.getYPos()+bulletSpeed);
                    
                    map[bullet.getXPos()][bullet.getYPos()]+=3;
                    map[bullet.getXPos()][bullet.getYPos()-1]+=3;
                    map[bullet.getXPos()][bullet.getYPos()-2]+=3;
                    map[bullet.getXPos()][bullet.getYPos()-3]+=3;
                    map[bullet.getXPos()][bullet.getYPos()-4]+=3;

                    
                          

                }
                else{
                    bulletLaunched=false;
                }
            
            
            }
        }
        
      //  for(int i=0;i<1200;i++){
      //      f
      //  }

        
      collisionCheck();
        
        
        
        
    }
    
    public int returnColumnX(){
        if(column!=null && column.getDestroyedState()==false){

            for(int i=0;i<600;i++){
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
    
    public int returnBulletX(){
        if(bullet!=null){
            return bullet.getYPos();
        }
        else{
            return 0;
        }
    }
    
    public int returnBulletY(){
        if(bullet!=null){
            return bullet.getXPos();
        }
        else{
            return 0;
        }
    }
    
    
        public int returnEnemy1X(){
        if(enemy1!=null){
            return enemy1.getYPos();
        }
        else{
            return 0;
        }
    }
    
    public int returnEnemy1Y(){
        if(enemy1!=null){
            return enemy1.getXPos();
        }
        else{
            return 0;
        }
    }
    
    
    
    
    
    private void collisionCheck(){
        int collisionValue=collider.checkForCollision(map);
        if(collisionValue==1){//collision with columns detected
            //change player's health to 0 or game over
            gameStop=true;
            
        }
        else if(collisionValue==2){
            System.out.println("Bullet Collided with COlumn");
            column.decreaseLife(25);
            bulletLaunched=false;
            //bullet=null;
            if(column.getDestroyedState()==true){
                column=null;
                int index=0;
                for(int i=0;i<600;i++){
                    if(map[0][i]==4){
                        index=i;
                        break;
                    }
                }
                
                for(int i=0;i<600;i++){
                    if(map[i][index]==4){
                        map[i][index]-=4;
                    }
                }
                
                System.out.println(columnPerLevelCount);
                
            }
        }
    }

    public void playerShoot() {
        

        if(bulletLaunched==false){
            bullet=new Weapons(player.getYPos()+10,40);
            bulletX=bullet.getXPos();
            bulletY=bullet.getYPos();

            map[bulletX][bulletY]+=3; // 3 for bullet
            map[bulletX][bulletY-1]+=3;
            map[bulletX][bulletY-2]+=3;
            map[bulletX][bulletY-3]+=3;
            map[bulletX][bulletY-4]+=3;

            
            bulletLaunched=true;
        }
        

        
        
    }
    
    public boolean shouldGameStop(){
        return gameStop;
    }
    
    public void setGameStop(boolean value){
        gameStop=value;
    }
    
    
    public void writeMap() throws FileNotFoundException, UnsupportedEncodingException{// for testing purposes
            PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
            for(int i=0;i<600;i=i+1){
                for(int j=0;j<600;j=j+1){
                    writer.print(map[i][j]);
                }
                writer.println();
                
            }
    writer.close();
    }
    
}
