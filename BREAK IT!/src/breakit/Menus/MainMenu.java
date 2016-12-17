/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package breakit.Menus;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

/**
 *
 * @author Usama Moin
 */
public class MainMenu extends JPanel  {

    
    protected final Image backgroud=Toolkit.getDefaultToolkit().getImage("background.jpg");
    protected final Image logo=Toolkit.getDefaultToolkit().getImage("logo.png");
    
    @Override
    protected void paintComponent(Graphics g) {
        
        g.drawImage(backgroud, 0, 0, 600, 600, this);
          
    }

    public void selectPrevious() {
        
    }
   
    public void selectNext(){

    }
    
    public String selectCurrentOption(){
        return "";
    }

    
}
