/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package breakit.Menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Usama Moin
 */
public class StartMenu extends MainMenu {
    
    private int currentSelection=1;
    private final String menuStrings[]={"Play Game","Change Settings","View Help","Train Margaret","Exit Game"};

        @Override
    protected void paintComponent(Graphics g) {
        
        g.drawImage(backgroud, 0, 0, 600, 600, this);
        g.drawImage(logo, (600/2)-(350/2),50, 350, 164, this);
        
        
        g.setColor(Color.white);
        g.setFont(new Font("Arial",1,25));
        
        g.drawString(menuStrings[0], 600/2-59, 390);
        g.drawString(menuStrings[1], 600/2-93, 425);
        g.drawString(menuStrings[2], 600/2-60, 460);
        //g.drawString(menuStrings[3], 600/2-85, 495);
        g.drawString(menuStrings[4], 600/2-60, 530);
        
        
        g.setColor(Color.yellow.brighter().brighter());
        if(currentSelection==1){
            g.drawString(menuStrings[0], 600/2-59, 390);
        }
        else if(currentSelection==2){
            g.drawString(menuStrings[1], 600/2-93, 425);
        }
        else if(currentSelection==3){
            g.drawString(menuStrings[2], 600/2-60, 460);
        }
        else if(currentSelection==4){
            g.drawString(menuStrings[3], 600/2-85, 495);
        }
        else{
            g.drawString(menuStrings[4], 600/2-60, 530);
        }

        //super.paintComponent(g); 
       // System.out.println("Menu 1");
        
        
        
    }
    
    @Override
    public void selectNext(){
        if(currentSelection<5){
            if(currentSelection==3){
                currentSelection++;
            }
            currentSelection++;
        }
    }
    
    @Override
    public void selectPrevious(){
        if(currentSelection>1){
            if(currentSelection==5){
                currentSelection--;
            }
            currentSelection--;
        }
    }
    
    @Override
    public String selectCurrentOption(){
        System.out.println("Option that has been selected is "+ menuStrings[currentSelection-1]);
        return menuStrings[currentSelection-1];
    }
    

    
    
}
