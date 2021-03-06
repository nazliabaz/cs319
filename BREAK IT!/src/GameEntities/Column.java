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
public class Column extends MapObject {
    private ColumnType type;
    private int remainLife;
    private boolean destroyed;
    
    public Column(int x,int y,int colType){
        super(x,y);
        if(colType<1){
            type=ColumnType.WOODEN;
            remainLife=75;
        }
        else{
            type=ColumnType.STONE;
            remainLife=150;
        }
        destroyed=false;
    }
    @Override
    public void isDestroyed(){
            super.setXPos(599);
            destroyed=true;
            remainLife=75;
    }
    
    @Override
    public void decreaseLife(int amount){
        remainLife-=amount;
        if(remainLife<=0){
            isDestroyed();
        }
    }
    
    /**
     *
     * @return
     */
    public boolean getDestroyedState(){
        return destroyed;
    }
    
    public ColumnType getType(){
        return type;
    }
}
