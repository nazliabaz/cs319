/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package breakit.Menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Usama Moin
 */
public class HelpMenu extends MainMenu{
        int currentSlide=1;
        int maxSlide=3;
    private final String menuStrings[]={"HELP","Go Back","This is Margaret","This is Abraham",
        "Press LEFT and RIGHT arrow keys to","scroll through the help.","Show Slide 2 content here","Show Slide 3 content here"};
    
    protected final Image margaret=Toolkit.getDefaultToolkit().getImage("margaret.png");
    protected final Image abraham=Toolkit.getDefaultToolkit().getImage("abraham.png");
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(backgroud, 0, 0, 600, 600, this);
        
        
        g.setColor(Color.white);
        g.setFont(new Font("Arial",1,50));
        
        g.drawString(menuStrings[0], 600/2-60, 90);
        g.setFont(new Font("Arial",1,25));
        
        if(currentSlide==1){
            g.drawString(menuStrings[2], 10, 400);
            g.drawString(menuStrings[3], 380, 400);

            g.drawImage(margaret, 10, 150, 200, 200, this);
            g.drawImage(abraham, 380, 150, 200, 200, this);
        }
        else if(currentSlide==2){
            g.drawString(menuStrings[6], 600/2-170, 300);
        }
        else if(currentSlide==3){
            g.drawString(menuStrings[7], 600/2-170, 300);
        }
        
        
        
        g.drawString(menuStrings[4], 80, 447);
        g.drawString(menuStrings[5], 155, 480);
        g.setColor(Color.yellow.brighter().brighter());
        
        
        g.drawString(menuStrings[1], 600/2-55, 530);
        
        
        
        
        
      //  System.out.println("HELP MENU DISPLAYED");
    }
    
    /**
     *
     */
    public void nextSlide(){
        if(currentSlide<maxSlide){
            currentSlide++;
        }
    }
    
        @Override
    public void previousSlide(){
        if(currentSlide>1){
            currentSlide--;
        } 
    }

}
