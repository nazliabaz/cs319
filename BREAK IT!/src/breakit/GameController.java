/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package breakit;

import GameMapping.GameMap;
import InputManagement.InputManager;
import breakit.Menus.HelpMenu;
import breakit.Menus.MainMenu;
import breakit.Menus.SettingsMenu;
import breakit.Menus.StartMenu;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Usama Moin
 */
public class GameController implements ActionListener {

    /**
     * @param args the command line arguments
     */
    public static GameController gameController;
    public final int WIDTH=600,HEIGHT=600;
    public GamePanel gPanel;
    public MainMenu menu;
    JFrame jframe;
    InputManager inpManager;
    SoundManager sManager;
    private int currentScreen=0; //0 for start screen
    private final GameMap mapManager;
    
    private  Image backgroud=Toolkit.getDefaultToolkit().getImage("background.jpg");
    private boolean backgroundNight=true;
    protected Image margaret=Toolkit.getDefaultToolkit().getImage("margaret.png");
    private boolean mHairYellow=true;
    protected Image abraham=Toolkit.getDefaultToolkit().getImage("abraham.png");
    private boolean aGlasses=false;
    protected final Image returnToMenu=Toolkit.getDefaultToolkit().getImage("return.png");
    protected Image bullet=Toolkit.getDefaultToolkit().getImage("smallBullet.png");
    protected final Image enemyBullet=Toolkit.getDefaultToolkit().getImage("enemyBullet.png");
    protected Image enemy=Toolkit.getDefaultToolkit().getImage("cat.png");
    protected Image powerup=Toolkit.getDefaultToolkit().getImage("mealPowerup.png");
    
    public int totalLife=200;
    public int remainingLife=200;
    
    public boolean isMargaretAlive=true;
    protected Image playerImage=Toolkit.getDefaultToolkit().getImage("margaret.png");
    
    
    
    public GameController(){
        inpManager=new InputManager();
        sManager=new SoundManager();
        sManager.startMusic();
        jframe = new JFrame();
        gPanel=new GamePanel();
        menu=new StartMenu();
        
        mapManager=new GameMap();
        
        Timer timer=new Timer(20,this);
        
        

        jframe.add(menu);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setTitle("BREAK IT!");
        jframe.addKeyListener(inpManager);
        jframe.setVisible(true);
        
        timer.start();
    }
    
    
    public static void main(String[] args) {
        
        gameController= new GameController();
        
    }
    
    void repaint(Graphics g) {
            //System.out.println("Hello");

            g.drawImage(backgroud, 0, 0, WIDTH, HEIGHT, gPanel);
            if(mapManager.shouldGameStop()==false && mapManager.didPlayerWin()==false){

                
                
                
                int bulletX=mapManager.returnBulletX();
                int bulletY=mapManager.returnBulletY();
                //System.out.println(bulletX+"   "+bulletY+" Player X position="+playerX);
                if(bulletX!=0){
                    bullet=mapManager.returnPlayerBullet();
                    g.drawImage(bullet, bulletX, bulletY,20,10, gPanel);
                }
                
                //System.out.println("bullet x= "+bulletX+" y="+bulletY);
                
                
                int enemyBulletX=mapManager.returnEnemyBulletX();
                int enemyBulletY=mapManager.returnEnemyBulletY()+10;
                //System.out.println(bulletX+"   "+bulletY+" Player X position="+playerX);
                if(enemyBulletX!=0){
                    g.drawImage(enemyBullet, enemyBulletX, enemyBulletY,30,10, gPanel);
                     
                }
                
                
                int powerUpX=mapManager.returnPowerupX();
                int powerUpY=mapManager.returnPowerupY();
                //System.out.println("Enemy X="+enemy1X+" Y="+enemy1Y);
                if(powerUpX!=0){
                    powerup=mapManager.returnPowerupImage();
                    g.drawImage(powerup, powerUpX, powerUpY,20,20, gPanel);
                }
                
                
                
                
                int playerX=mapManager.returnPlayerX();
                int playerY=mapManager.returnPlayerY();
                //System.out.println("X="+playerX+" Y="+playerY);
                g.drawImage(playerImage, playerX, playerY-30, 60, 60, gPanel);


                int columnX=mapManager.returnColumnX();
                //System.out.println("COlumnx="+columnX);
                if(columnX!=-5){
                    int colType=mapManager.returnColumnType();
                    if(colType==0){
                        g.setColor(Color.red.darker().darker().darker());
                    }
                    else{
                        g.setColor(Color.gray.darker().darker());
                    }
                    g.fillRect(columnX-10, 0, 40, 600);                

                }
                
                int enemy1X=mapManager.returnEnemy1X();
                int enemy1Y=mapManager.returnEnemy1Y();
                //System.out.println("Enemy X="+enemy1X+" Y="+enemy1Y);
                if(enemy1X!=0){
                    enemy=mapManager.returnEnemyImage();
                    g.drawImage(enemy, enemy1X, enemy1Y,70,30, gPanel);
                }
                
                

                



                g.drawImage(returnToMenu, 155, 500, 311, 59, gPanel);
                //g.drawRect(200, 15, 200, 30);
                //g.drawRoundRect(200, 15, 200, 30, 10, 10);
                g.setColor(Color.red.darker());
                g.fillRoundRect(160, 15, 260, 20, 20, 20);
                float lifeRatio=(float)remainingLife/totalLife;
                //System.out.println(lifeRatio);
                g.setColor(Color.green.darker());
                g.fillRoundRect(160, 15, (int) (260*lifeRatio), 20, 20, 20);
                
                g.setColor(Color.white);
                
                g.setFont(new Font("Arial",1,20));
        
                g.drawString("Level "+mapManager.getLevel(), 300-50, 60);
                
                
                
            }
            else if(mapManager.shouldGameStop()==true ){
                g.setColor(Color.white);
                g.setFont(new Font("Arial",1,50));
        
                g.drawString("Game Over!", 600/2-135, 90);
                
                
                g.setFont(new Font("Arial",1,25));
        
                g.drawString("Press ESC to go back to Main Menu.", 600/2-200, 300);
            }
            else{
                g.setColor(Color.white);
                g.setFont(new Font("Arial",1,50));
        
                g.drawString("You Won!", 600/2-125, 90);
                
                
                g.setFont(new Font("Arial",1,25));
        
                g.drawString("Press ESC to go back to Main Menu.", 600/2-200, 300);
            }
            

            
            
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean pressedKeys[]=inpManager.getButtonsList();
    /*
        Index 0 signifies Up
        Index 1 signifies Down
        Index 2 signifies Left
        Index 3 signifies Right
        Index 4 signifies Shoot
        Index 5 signifies Select
    */

        if(currentScreen==0){
            String menuSelection= "";
            if(pressedKeys[0]==true){
                menu.selectPrevious();
                inpManager.keyHandled(0);
            }
            if(pressedKeys[1]==true){
                menu.selectNext();
                inpManager.keyHandled(1);
            }
            if(pressedKeys[5]==true){
                menuSelection=menu.selectCurrentOption();
                inpManager.keyHandled(5);
                
            }
            
            if(pressedKeys[6]==true){
                jframe.setVisible(false); 
                jframe.dispose(); //Called in order to destroy the JFrame object 
                System.exit(0);
            }
            if(menuSelection.equals("Play Game")){
                jframe.remove(menu);
                gPanel=new GamePanel();
                reinitializeGame();
                jframe.add(gPanel); 
                jframe.invalidate();
                jframe.validate();
                jframe.repaint(); 
                currentScreen=10;
                mapManager.setGameStop(false);
            }
            else if(menuSelection.equals("Change Settings")){
                jframe.remove(menu);
                
                menu=new SettingsMenu();
                
                jframe.add(menu); 
                jframe.invalidate();
                jframe.validate();
                jframe.repaint(); 
                
                
                currentScreen=1;
            }
            else if(menuSelection.equals("View Help")){
                jframe.remove(menu);
                
                menu=new HelpMenu();
                
                jframe.add(menu); 
                jframe.invalidate();
                jframe.validate();
                jframe.repaint(); 
                
                
                currentScreen=2;
            }
            else if(menuSelection.equals("Train Margaret")){
                System.out.println("Display Train Margaret Screen Here");
            }
            else if(menuSelection.equals("Exit Game")){
                jframe.setVisible(false); 
                jframe.dispose(); //Called in order to destroy the JFrame object 
                System.exit(0);
            }
            menu.repaint();
        }
        else if(currentScreen==1){
            String menuSelection= "";
            if(pressedKeys[0]==true){
                menu.selectPrevious();
                inpManager.keyHandled(0);
            }
            if(pressedKeys[1]==true){
                menu.selectNext();
                inpManager.keyHandled(1);
            }
            if(pressedKeys[5]==true){
                menuSelection=menu.selectCurrentOption();
                inpManager.keyHandled(5);
                
            }
            if(pressedKeys[6]==true){
                jframe.setVisible(false); 
                jframe.dispose(); //Called in order to destroy the JFrame object 
                System.exit(0);
            }
            if(menuSelection.equals("Disable Sound")){
                    sManager.switchState();
            }
            else if(menuSelection.equals("Switch Margaret's Hair")){
                    System.out.println("Do mHair==0?mHair=1,mHair=0");
                    if(mHairYellow==true){
                        margaret=Toolkit.getDefaultToolkit().getImage("margaret1.png");
                        mHairYellow=false;
                    }
                    else{
                        margaret=Toolkit.getDefaultToolkit().getImage("margaret.png");
                        mHairYellow=true;
                    }
                    playerImage=margaret;
            }
            else if(menuSelection.equals("Toggle Abraham's Glasses")){
                    System.out.println("Do aGlasses==0?aGlasses=1,aGlasses=0");
                    if(aGlasses==false){
                        abraham=Toolkit.getDefaultToolkit().getImage("abraham1.png");
                        aGlasses=true;
                    }
                    else{
                        abraham=Toolkit.getDefaultToolkit().getImage("abraham.png");
                        aGlasses=false;
                    }

                    
            }
            else if(menuSelection.equals("Switch Background")){
                    System.out.println("Do bg==0?bg=1,bg=0");
                    if(backgroundNight==true){
                        backgroud=Toolkit.getDefaultToolkit().getImage("background1.png");
                        backgroundNight=false;
                    }
                    else{
                        backgroud=Toolkit.getDefaultToolkit().getImage("background.jpg");
                        backgroundNight=true;
                    }
            }
            else if(menuSelection.equals("Configure Keys")){
                    System.out.println("Show Keys configuration Menu");
            }
            else if(menuSelection.equals("Enable Default Settings")){
                    inpManager.setToDefaults();
                    System.out.println("Keys set to default");
            }
            else if(menuSelection.equals("Go Back")){
                jframe.remove(menu);
                
                menu=new StartMenu();
                
                jframe.add(menu); 
                jframe.invalidate();
                jframe.validate();
                jframe.repaint(); 
                
                
                System.out.println("Display Main Screen Here");
                currentScreen=0;
                
            }
            
            
            menu.repaint();
         }
        else if(currentScreen==2){
            
            if(pressedKeys[2]==true){
                menu.previousSlide();
                inpManager.keyHandled(2);
            }
            if(pressedKeys[3]==true){
                menu.nextSlide();
                inpManager.keyHandled(3);
            }

            if(pressedKeys[6]==true){
                jframe.setVisible(false); 
                jframe.dispose(); //Called in order to destroy the JFrame object 
                System.exit(0);
            }
            if(pressedKeys[5]==true){
                jframe.remove(menu);
                
                menu=new StartMenu();
                
                jframe.add(menu); 
                jframe.invalidate();
                jframe.validate();
                jframe.repaint(); 
                
                
                System.out.println("Display Main Screen Here");
                currentScreen=0;
                inpManager.keyHandled(5);
            }
            
            
            
            
            menu.repaint();
        }
        else{
            
            //System.out.println("else Part called");
            if(pressedKeys[0]==true){
                mapManager.playerMoveUp();
                inpManager.keyHandled(0);
            }
            else if(pressedKeys[1]==true){
                mapManager.playerMoveDown();
                inpManager.keyHandled(1);
            }
         /*   else if(pressedKeys[3]==true){
                mapManager.playerMoveRight();
                inpManager.keyHandled(3);
            }
            else if(pressedKeys[2]==true){
                mapManager.playerMoveLeft();
                inpManager.keyHandled(2);
            }*/
            else if(pressedKeys[4]==true){
                mapManager.playerShoot();
                inpManager.keyHandled(4);
            }
            else if(pressedKeys[5]==true){
                mapManager.switchBulletType();
                inpManager.keyHandled(5);
            }
            if(pressedKeys[6]==true){
                reinitializeGame();
                jframe.remove(gPanel);
                
                menu=new StartMenu();
                
                jframe.add(menu); 
                jframe.invalidate();
                jframe.validate();
                jframe.repaint(); 
                
                
                System.out.println("Display Main Screen Here");
                currentScreen=0;
                inpManager.keyHandled(6);
            }
            
            mapManager.update();
            modifyPlayerHealth();
            gPanel.repaint();
            
            
        }
        
          
        
        
        
        
    }
    
    
    public void reinitializeGame(){
        mapManager.reinitialize();
        remainingLife=200;
        totalLife=200;
        isMargaretAlive=true;
        playerImage=margaret;

    }

    private void modifyPlayerHealth() {
        remainingLife-=mapManager.reducePlayerHealth();

            remainingLife+=mapManager.increasePlayerHealth();
            if(remainingLife>200){
                remainingLife=200;
            }
        //System.out.println("Remaining Life is ="+remainingLife);
        if(remainingLife<=0 && isMargaretAlive==true){
            playerImage=abraham;
            isMargaretAlive=false;
            remainingLife=100;
            totalLife=100;
        }
        else if(remainingLife<=0 && isMargaretAlive==false){
            mapManager.setGameStop(true);
        }
        else if(remainingLife==200 && isMargaretAlive==false){
            playerImage=margaret;
            isMargaretAlive=true;
            remainingLife=200;
            totalLife=200;
        }
    }
    
    
}
