package unsw.dungeon.entities;

public interface CharacterInterface {
    int getX();
    int getY();
    boolean isAlive();
    void move(int x, int y);
    void kill();
}