package unsw.dungeon;

public class Invincibility extends Consumable {
    private Player player;
    private boolean active;
    /**
     * Create an invincibility potion positioned in square (x,y)
     * @param x
     * @param y
     */
    public Invincibility(Dungeon dungeon, int x, int y) {
        super(dungeon, x, y);
        this.active = false;
    }

    public void use(Player player) {
        this.player = player;
        invincibleState.run();
    }

    public Thread invincibleState = new Thread() {
        public void run() {
            player.toggleInvincible(true);
            System.out.println("Invincible Start");
            long StoppingTime = 5 ;
            int loop = 1;
            long StartTime = System.currentTimeMillis() / 1000 ;
            for (int i=0; i<loop; i++) {
                loop++;
                if (((System.currentTimeMillis()/1000) - StartTime) > StoppingTime)
                    loop=0;
            }
            System.out.println("Invincible End");
            player.toggleInvincible(false);
        }
    };
}