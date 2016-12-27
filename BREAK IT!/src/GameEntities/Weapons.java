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
public class Weapons extends MapObject {
    private final WeaponType type;
    public Weapons(int x,int y){
        super(x,y);
        type=WeaponType.SMALLBLAST;

    }
    
    
}
