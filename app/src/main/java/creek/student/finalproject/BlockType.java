package creek.student.finalproject;

public interface BlockType {
    int type = 0;
    int points = 0;
//helps store block ID's and types.
    int getType();

    void setType(int id);

    int getPoints();

    void setPoints(int id);
}