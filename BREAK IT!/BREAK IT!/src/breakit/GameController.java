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
    
    private final Image backgroud=Toolkit.getDefaultToolkit().getImage("background.jpg");
    protected final Image margaret=Toolkit.getDefaultToolkit().getImage("margaret.png");
    protected final Image abraham=Toolkit.getDefaultToolkit().getImage("abraham.png");
    protected final Image returnToMenu=Toolkit.getDefaultToolkit().getImage("return.png");
    
    public int totalLife=200;
    public int remainingLife=200;
    
    
    public GameController(){
        inpManager=new InputManager();
        sManager=new SoundManager();
        sManager.startBGMusic();
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
            if(mapManager.shouldGameStop()==false){
                int playerX=mapManager.returnPlayerX()/2;
                int playerY=mapManager.returnPlayerY()/2-30;
                g.drawImage(margaret, playerX, playerY, 60, 60, gPanel);


                int columnX=mapManager.returnColumnX()/2;
                //System.out.println("COlumnx="+columnX);
                if(columnX!=-2){
                    g.setColor(Color.red.darker().darker().darker());
                    g.fillRect(columnX-10, 0, 40, 600);                

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
            }
            else{
                g.setColor(Color.white);
                g.setFont(new Font("Arial",1,50));
        
                g.drawString("Game Over!", 600/2-135, 90);
                
                
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
                    System.out.println("Do sound=false");
            }
            else if(menuSelection.equals("Switch Margaret's Hair")){
                    System.out.println("Do mHair==0?mHair=1,mHair=0");
            }
            else if(menuSelection.equals("Toggle Abraham's Glasses")){
                    System.out.println("Do aGlasses==0?aGlasses=1,aGlasses=0");
            }
            else if(menuSelection.equals("Switch Background")){
                    System.out.println("Do bg==0?bg=1,bg=0");
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
            if(pressedKeys[3]==true){
                mapManager.playerMoveRight();
                inpManager.keyHandled(3);
            }
            else if(pressedKeys[2]==true){
                mapManager.playerMoveLeft();
                inpManager.keyHandled(2);
            }
            else if(pressedKeys[4]==true){
                mapManager.playerShoot();
                inpManager.keyHandled(4);
            }
            if(pressedKeys[6]==true){
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
            
            gPanel.repaint();
            
            
        }
        
          
        
        
        
        
    }
    
    
    public void reinitializeGame(){
        mapManager.reinitialize();

    }
    
    
}
