package unsw.dungeon;

import java.util.ArrayList;

public class Enemy extends Entity {
    
    private Dungeon dungeon;
    private boolean alive;
    /**
     * Create an enemy positioned in square (x,y)
     * @param x
     * @param y
     */
    public Enemy(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
        this.alive = true;
        hunt.start();
    }

    public void moveUp() {
        if (!collisionHandler(getX(), getY()-1)) return;
        if (getY() > 0)
            y().set(getY() - 1);
    }

    public void moveDown() {
        if (!collisionHandler(getX(), getY()+1)) return;
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
        if (!collisionHandler(getX()-1, getY())) return;
        if (getX() > 0)
            x().set(getX() - 1);
    }

    public void moveRight() {
        if (!collisionHandler(getX()+1, getY())) return;
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }

    public boolean collisionHandler(int targetX, int targetY) {
        Entity target = dungeon.detectCollision(targetX, targetY);
        if (target == null) return true;
        String entity = target.getClass().getName();

        switch (entity) {
            case "unsw.dungeon.Wall":
                return false;
            case "unsw.dungeon.Boulder":
                return false;
            default:
                return true;
        }
    }
    private Thread hunt = new Thread() {

        private ArrayList<Cell> updateNeighbours(ArrayList<Cell> neighbours) {
            if (getY() < dungeon.getHeight() - 1 && !(collisionHandler(getY() + 1, getX())))
                neighbours.add(dungeon.getGrid().getCell(getY() + 1, getX()));

            if (getY() > 0 && !(collisionHandler(getY() - 1, getX())))
                neighbours.add(dungeon.getGrid().getCell(getY() - 1, getX()));

            if (getX() < dungeon.getWidth() && !(collisionHandler(getY(), getX() + 1)))
                neighbours.add(dungeon.getGrid().getCell(getY(), getX() + 1));

            if (getX() > 0 && !(collisionHandler(getY(), getX() - 1)))
                neighbours.add(dungeon.getGrid().getCell(getY(), getX() - 1));
            
            return neighbours;
        }

        public void run() {
            Player player = dungeon.getPlayer();
            // ArrayList<Cell> neighbours = new ArrayList<Cell>();
            while(alive) {
                int playerX = player.positionBroadcast()[0];
                int playerY = player.positionBroadcast()[1];
                double currDist = calcDist(getX(), getY(), playerX, playerY);
                
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (alive && !player.isInvincible() && getX() == playerX && getY() == playerY) {
                    killPlayer();
                }
            }
        }

        private double calcDist(int ax, int ay, int bx, int by) {
            return Math.sqrt(Math.pow((by - ay), 2) + Math.pow((bx - ax), 2));
        }

        public void killPlayer() {
            System.exit(0);
        }
    };

    public void kill() {
        alive = false;
    }
}
