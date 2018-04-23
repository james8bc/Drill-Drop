package creek.student.finalproject;

/**
 * Created by student on 4/13/18.
 */

public abstract class Item {
    private int xPos;
    private int yPos;
    private Image image;
    void hit(){

    }
    int getPosX(){
        System.out.println(xPos);
        return xPos;
    }
    int getPosY(){
        return yPos;
    }

    public Image getImage(){
        return image;
    }
}
