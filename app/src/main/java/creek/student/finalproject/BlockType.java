package creek.student.finalproject;

import sun.jvm.hotspot.opto.Block;

/**
 * Created by student on 4/17/18.
 */

public class BlockType extends Block {
    private String name;
    private int pointsGained;
    public int getPoitnsGained(){
        return pointsGained;
    }
    public String getName(){
        return name;
    }
}
