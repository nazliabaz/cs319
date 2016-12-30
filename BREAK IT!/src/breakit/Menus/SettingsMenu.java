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
public class SettingsMenu extends MainMenu {

    private int currentSelection=1;
    private final String menuStrings[]={"Settings","Disable Sound","Switch Margaret's Hair",
        "Toggle Abraham's Glasses","Switch Background","Configure Keys","Enable Default Settings","Go Back"};
    
    
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(backgroud, 0, 0, 600, 600, this);
        
        
        g.setColor(Color.white);
        g.setFont(new Font("Arial",1,50));
        
        g.drawString(menuStrings[0], 600/2-90, 120);
        
        
        g.setFont(new Font("Arial",1,25));
        
        g.drawString(menuStrings[1], 600/2-78, 210);
        g.drawString(menuStrings[2], 600/2-125, 270);
        g.drawString(menuStrings[3], 600/2-150, 300);
        g.drawString(menuStrings[4], 600/2-110, 330);
        //g.drawString(menuStrings[5], 600/2-85, 385);
        g.drawString(menuStrings[6], 600/2-125, 445);
        g.drawString(menuStrings[7], 600/2-55, 510);
        
        //System.out.println("Settings Screen");
        
        
        g.setColor(Color.yellow.brighter().brighter());
        if(currentSelection==1){
            g.drawString(menuStrings[1], 600/2-78, 210);
        }
        else if(currentSelection==2){
            g.drawString(menuStrings[2], 600/2-125, 270);
        }
        else if(currentSelection==3){
            g.drawString(menuStrings[3], 600/2-150, 300);
        }
        else if(currentSelection==4){
            g.drawString(menuStrings[4], 600/2-110, 330);
        }
        else if(currentSelection==5){
           // g.drawString(menuStrings[5], 600/2-85, 385);
        }
        else if(currentSelection==6){
            g.drawString(menuStrings[6], 600/2-125, 445);
        }
        else{
            g.drawString(menuStrings[7], 600/2-55, 510);
        }
        

    }
    
    
    
    @Override
    public void selectNext(){
        if(currentSelection<7){
            if(currentSelection==4){
                currentSelection++;
            }
            currentSelection++;
           // System.out.println(currentSelection);
        }
    }
    
    @Override
    public void selectPrevious(){
        if(currentSelection>1){
            if(currentSelection==6){
                currentSelection--;
            }
            currentSelection--;
        }
    }
    
    @Override
    public String selectCurrentOption(){
        System.out.println("Option that has been selected is "+ menuStrings[currentSelection]);
        return menuStrings[currentSelection];
    }
    
    
    
    
    

}
