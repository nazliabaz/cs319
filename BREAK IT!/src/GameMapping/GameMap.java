/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameMapping;

import GameEntities.Bird;
import GameEntities.Column;
import GameEntities.ColumnType;
import GameEntities.Enemy;
import GameEntities.MapObject;
import GameEntities.PowerupType;
import GameEntities.Powerups;
import GameEntities.Weapons;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import static java.lang.System.exit;
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
    private int enemyPerLevelCount=3;
    
    
    //private int playerX=14,playerY=599;
    private final int defaultPlayerX=250,defaultPlayerY=7;
    private final int HEIGHT=600;
    private final int WIDTH=600;
    private int[][] map;
    private boolean gameStop=false;
    private int bulletX,bulletY;
    private Weapons bullet;
    private boolean bulletLaunched=false;
    private Enemy enemy1;
    private Weapons enemyBullet;
    private Powerups powerup;
    private int playerReduceHealthBy=0;
    private int healthReductionAnimator=0;
    private int currentLevel=1;
    private boolean gameWon=false;
    private int playerIncreaseHealthBy;
    private int immortalPeriod=0;
    private int columnType;
    private int playerBulletType=0;
    private Image playerBullet;
    private int playerWeaponPower;
    
    public GameMap(){
        map= new int[WIDTH][HEIGHT];
        for(int i=0;i<HEIGHT;i++){
            for(int j=0;j<WIDTH;j++){
                map[i][j]=0;
            }
        }
        
        playerBullet=Toolkit.getDefaultToolkit().getImage("smallBullet.png");
        playerWeaponPower=25;
        collider=new CollisionManager();
        
        
        
        
        int randomInt = randomGenerator.nextInt(100);
        if(column==null && randomInt>25 && randomInt<35 ){
            int columnRandom = randomGenerator.nextInt(10);
            columnType=columnRandom%3;
            column=new Column(599,0,columnType);
            columnPerLevelCount--;
            
            for(int i=0;i<600;i++){
                map[i][599]=4;
            }
        
        
        }
        
        
        
        
        /*player=new Bird(7,599/2);
        
        
        for(int i=599/2;i<601/2;i++){
            for(int j=7;j<8;j++){
                map[i][j]=1; //represents player
            }
        }*/
        
        
        player=new Bird(250,7);
        for(int i=0;i<60;i++){
            map[250+i][7]=1;
        }

       
    }
    
    public int[][] obtainMap(){
        return map;
    }
    
    public int returnPlayerX(){
        return 7;
    }
    
    public int returnPlayerY(){
        for(int i=0;i<600;i++){
            if(map[i][7]==1){
               // playerX=i;
                player.setYPos(i);
            }
        }
        
        //return playerX;
        return player.getYPos();
    
    }
    

    
    
    public void playerMoveUp(){
        /*if(player.getYPos()>30){
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
        }*/
        
        
        if(player.getXPos()>2){
            for(int i=0;i<60;i++){
                map[player.getXPos()+i][7]-=1;
            }
            
            player.setXPos(player.getXPos()-5);
            
            for(int i=0;i<60;i++){
                map[player.getXPos()+i][7]+=1;
            }
            
        }
        
        
    }
    
    public void playerMoveDown(){
        if(player.getXPos()<440){
            
            for(int i=0;i<60;i++){
                map[player.getXPos()+i][7]-=1;
            }
            
            player.setXPos(player.getXPos()+5);
            
            for(int i=0;i<60;i++){
                map[player.getXPos()+i][7]+=1;
            }
            
            /*player.setYPos(player.getYPos()+5);
            map[player.getYPos()][14]+=1;
            map[player.getYPos()][15]+=1;
            map[player.getYPos()+1][14]+=1;
            map[player.getYPos()+1][15]+=1;

            map[player.getYPos()-4][14]-=1;
            map[player.getYPos()-4][15]-=1;
            map[player.getYPos()-5][14]-=1;
            map[player.getYPos()-5][15]-=1;

            System.out.println(player.getYPos());*/
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
        
        for(int i=0;i<60;i++){
            map[250+i][7]=1;
        }
        column=null;
        columnPerLevelCount=2;
        enemyPerLevelCount=3;
        enemy1=null;
        powerup=null;
        currentLevel=1;
        gameWon=false;
        immortalPeriod=0;

    }

    public void update() {

        int randomInt = randomGenerator.nextInt(300);
        int bulletSpeed=4;
        
        if(column==null && randomInt>22 && randomInt<24 && columnPerLevelCount>0 ){
            int columnRandom = randomGenerator.nextInt(10);
            columnType=columnRandom%3;
            column=new Column(599,0,columnType);
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
        
        if(randomInt==92  && (enemy1==null) && enemyPerLevelCount>0){
            int randomIntForPosition = randomGenerator.nextInt(450)+25;
            int randomIntForType = randomGenerator.nextInt(10);
            if(enemy1==null){
                enemy1=new Enemy(randomIntForPosition,568,randomIntForType%3);
                enemyPerLevelCount--;
                for(int i=randomIntForPosition;i<randomIntForPosition+30;i++){
                    for(int j=0;j<2;j++){
                        map[i][568+j]+=9;
                    }
                }
                System.out.println("Enemy created");
            }
        }
        else if(enemy1!= null ){
            
                int enemCurrX=enemy1.getXPos();
                int enemCurrY=enemy1.getYPos();
                if(enemCurrY>2){

                    for(int i=enemCurrX;i<enemCurrX+30;i++){
                        for(int j=enemCurrY;j<enemCurrY+2;j++){
                            map[i][j]-=9;
                        }
                    }
                    enemy1.setYPos(enemy1.getYPos()-1);
                    enemCurrY=enemy1.getYPos();
                    for(int i=enemCurrX;i<enemCurrX+30;i++){
                        for(int j=enemCurrY;j<enemCurrY+2;j++){
                            map[i][j]+=9;
                        }
                    }
                    
                    int randomInteger = randomGenerator.nextInt(200);
                    if(enemyBullet==null && randomInteger<75 && enemy1.getYPos()>50){
                        enemyBullet=new Weapons(enemy1.getXPos(),enemy1.getYPos());
                        int enemBullX=enemyBullet.getXPos();
                        int enemBullY=enemyBullet.getYPos();
                        map[enemBullX][enemBullY]+=25; // 25 for enemy bullet
                        map[enemBullX][enemBullY-1]+=25;
                        map[enemBullX][enemBullY-2]+=25;
                        map[enemBullX][enemBullY-3]+=25;
                        map[enemBullX][enemBullY-4]+=25;
                        
                    }
                    
                    
                    
                }
                else{

                    enemy1=null;
                    
                }

                
                
            
        }
        
        if(bullet!=null){
            if(bullet.getYPos()!=0){
                if(bullet.getXPos()<590 && bullet.getYPos()<590){
                    int bulletXCurrent=bullet.getXPos();
                    int bulletYCurrent=bullet.getYPos();
                    

                    map[bulletXCurrent][bulletYCurrent]-=3;
                    map[bulletXCurrent][bulletYCurrent-1]-=3;
                    map[bulletXCurrent][bulletYCurrent-2]-=3;
                    map[bulletXCurrent][bulletYCurrent-3]-=3;
                    map[bulletXCurrent][bulletYCurrent-4]-=3;
 
                    bullet.setYPos(bullet.getYPos()+bulletSpeed);
                    bulletYCurrent=bullet.getYPos();
                    
                    map[bulletXCurrent][bulletYCurrent]+=3;
                    map[bulletXCurrent][bulletYCurrent-1]+=3;
                    map[bulletXCurrent][bulletYCurrent-2]+=3;
                    map[bulletXCurrent][bulletYCurrent-3]+=3;
                    map[bulletXCurrent][bulletYCurrent-4]+=3;

                    
                          

                }
                else{
                    bulletLaunched=false;
                    for(int i=0;i<600;i++){
                        for(int j=585;j<600;j++){
                            if(map[i][j]==3){
                                map[i][j]-=3;
                            }
                        }
                    }
                    
                }
            
            
            }
        }
        
        
        
        if(enemyBullet!=null){
            if(enemyBullet.getYPos()>7){
                    int enemBullX=enemyBullet.getXPos();
                    int enemBullY=enemyBullet.getYPos();
                    
                    map[enemBullX][enemBullY]-=25; // 25 for enemy bullet
                    map[enemBullX][enemBullY-1]-=25;
                    map[enemBullX][enemBullY-2]-=25;
                    map[enemBullX][enemBullY-3]-=25;
                    map[enemBullX][enemBullY-4]-=25;
                    
                    enemyBullet.setYPos(enemyBullet.getYPos()-bulletSpeed);
                    enemBullY=enemyBullet.getYPos();
                    
                    map[enemBullX][enemBullY]+=25; // 25 for enemy bullet
                    map[enemBullX][enemBullY-1]+=25;
                    map[enemBullX][enemBullY-2]+=25;
                    map[enemBullX][enemBullY-3]+=25;
                    map[enemBullX][enemBullY-4]+=25;
            }
            else{
                enemyBullet=null;
                    for(int i=0;i<600;i++){
                        for(int j=0;j<20;j++){
                            if(map[i][j]==25){
                                map[i][j]-=25;
                            }
                        }
                    }
            }
                    
        }
        
        
        int randomPowerUpInt = randomGenerator.nextInt(3000);
        if(powerup==null && randomPowerUpInt==2555 || randomPowerUpInt==2666 || randomPowerUpInt==2667 || randomPowerUpInt==2668){
            int randomIntForPositionX = randomGenerator.nextInt(400)+100;
            int randomIntForPositionY = randomGenerator.nextInt(400)+150;
            int randomIntForType = randomGenerator.nextInt(3)+1;
            powerup=new Powerups(randomIntForPositionX,randomIntForPositionY,randomIntForType);
                    for(int i=0;i<20;i++){
                        for(int j=0;j<4;j++){
                            map[randomIntForPositionX+i][randomIntForPositionY+j]+=50;
                        }
                    }
            
        }
        else if(powerup!=null){
            if(powerup.getType()==PowerupType.MUSHROOM || powerup.getType()==PowerupType.IMMORTALITY){
                powerup.reduceDisappearTime();
                System.out.println("Disappear time="+powerup.getDisappearTime());
                if(powerup.getDisappearTime()<=0){
                    powerup=null;
                }
            }
        }
        
        
        
        
        
        
        
      //  for(int i=0;i<1200;i++){
      //      f
      //  }
        
        
        
        if(immortalPeriod>0){
            immortalPeriod--;
            System.out.println("Immortality left="+immortalPeriod);
        }
        
        
        
        
        
        
        

        
      collisionCheck();
        
        
        levelLogicHandler();
        
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
    
    public int returnColumnType(){
       if(columnType<1){
           return 0;
       }
       else{
           return 1;
       }
        
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
    
        public Image returnEnemyImage(){
        if(enemy1!=null){
            return enemy1.getImage();
        }
        else{
            return null;
        }
    }
    
    
    
    
    
    public int returnEnemyBulletX(){
        if(enemyBullet!=null){
            return enemyBullet.getYPos()-30;
        }
        else{
            return 0;
        }
    }
    
    public int returnEnemyBulletY(){
        if(enemyBullet!=null){
            return enemyBullet.getXPos();
        }
        else{
            return 0;
        }
    }
    
    
    
    public int returnPowerupX(){
        if(powerup!=null){
            return powerup.getYPos()-30;
        }
        else{
            return 0;
        }
    }
    
    public int returnPowerupY(){
        if(powerup!=null){
            return powerup.getXPos();
        }
        else{
            return 0;
        }
    }
    
    public Image returnPowerupImage(){
        if(powerup!=null){
            return powerup.getImage();
        }
        else{
            return null;
        }
    }
    
    
    
    
    
    private void collisionCheck(){
        int collisionValue=collider.checkForCollision(map);
        if(collisionValue==1){//collision with columns detected
            if(immortalPeriod<=0){
                gameStop=true;
            }
            
        }
        else if(collisionValue==2){
            System.out.println("Bullet Collided with COlumn");
            if(column!=null)
                column.decreaseLife(playerWeaponPower);
            bulletLaunched=false;
            //bullet=null;
            if(column!=null)
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
        else if(collisionValue==3){//bullet collides with enemy
            enemy1.decreaseLife(playerWeaponPower);
            
            
            if(enemy1.getDestroyedState()==true){
                int enemX=enemy1.getXPos();
                int enemY=enemy1.getYPos();
                
                enemy1=null;
                //System.out.println("enemX= "+enemX+" enemY="+enemY);
                    for(int i= enemX;i< enemX+30;i++){
                        for(int j=enemY;j<enemY+2;j++){
                            map[i][j]-=9;
                        }
                    }
                
                
                
            }
                

            
        }
        else if(collisionValue==4){//collision of player with enemy
            if(immortalPeriod<=0){
                if(enemy1!=null)
                    playerReduceHealthBy=enemy1.returnAttackPower();
                else{
                    playerReduceHealthBy=25;
                }
                healthReductionAnimator=0;
            }
        }
        else if(collisionValue==5){//collision of player with enemy bullet
            if(immortalPeriod<=0){
                if(enemy1!=null)
                    playerReduceHealthBy=enemy1.returnAttackPower();
                else{
                    playerReduceHealthBy=25;
                }
                healthReductionAnimator=0;
            }
        }
        else if(collisionValue==6){//bullet collided with powerup
            if(powerup!=null){
                int powerX=powerup.getXPos();
                int powerY=powerup.getYPos();

                        for(int i=0;i<20;i++){
                            for(int j=0;j<4;j++){
                                map[powerX+i][powerY+j]-=50;
                            }
                        }


                if(powerup.getType()==PowerupType.MEAL){
                    playerIncreaseHealthBy=25;
                }
                else if(powerup.getType()==PowerupType.MUSHROOM){
                    playerIncreaseHealthBy=50;
                }
                else if(powerup.getType()==PowerupType.IMMORTALITY){
                    immortalPeriod=300;
                }

                powerup=null;
            }
            
            
        }
        
        cleanMap();
    }
    
    
    public void cleanMap(){
        for(int i=0;i<600;i++){
            for(int j=0;j<600;j++){
                if(map[i][j]<0){
                    map[i][j]=0;
                }
                if(enemy1==null){
                    if(map[i][j]==9){
                        map[i][j]=0;
                    }
                }
                
                if(map[i][j]==-3){
                    map[i][j]=0;
                }
                if(bullet==null && map[i][j]==3){
                    map[i][j]=0;
                }
                
                
            }
        }
        
    }

    public void playerShoot() {
        

        if(bulletLaunched==false){
            bullet=new Weapons(player.getYPos()+37,40);
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
    
    public boolean didPlayerWin(){
        return gameWon;
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
    
    public int reducePlayerHealth(){
        if(playerReduceHealthBy!=healthReductionAnimator){
            healthReductionAnimator++;
            if(healthReductionAnimator==playerReduceHealthBy){
                return 0;
            }
            return 1;
        }
        else{
            playerReduceHealthBy=0;
            healthReductionAnimator=0;
            return 0;
        }
    }
    
    public int increasePlayerHealth(){
        int temp=playerIncreaseHealthBy;
        playerIncreaseHealthBy=0;
        return temp;
    }
    
    
    
   public int getLevel(){
       return currentLevel;
   }
   
   public void levelLogicHandler(){
       if(columnPerLevelCount==0 && enemyPerLevelCount==0){
           if(enemy1==null && column==null){
               int currLevel=currentLevel+1;
               reinitialize();
               if(currLevel<=5){
                    currentLevel=currLevel;

                    enemyPerLevelCount=currLevel*3;
                    columnPerLevelCount=(int) (currLevel*1.5);
               }
               else{
                   gameWon=true;
               }
           }
       }
   }
   
   
   public void switchBulletType(){
       if(playerBulletType==0){
           playerBulletType=1;
           playerBullet=Toolkit.getDefaultToolkit().getImage("Shuriken.png");
           playerWeaponPower=50;
       }
       else{
           playerBulletType=0;
           playerBullet=Toolkit.getDefaultToolkit().getImage("smallBullet.png");
           playerWeaponPower=25;
       }
   }
   
   public Image returnPlayerBullet(){
       return playerBullet;
   }
   
   

   
   
   
    
}
