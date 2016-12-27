/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GameEntities;

/**
 *
 * @author Usama Moin
 */
public class Enemy extends MapObject {
    private EnemyType type;
    private int remainLife;
    private int attackPower;
    
    
    public Enemy(int x,int y){
        super(x,y);
        type=EnemyType.CAT;
        remainLife=25;
        attackPower=25;
        //destroyed=false;
    }
    
    
}
