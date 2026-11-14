
package edu.starter;

/**
 * Represents an enemy in the battle game.
 *
 * Class Invariants:
 * name != null
 * hp >= 0
 */
public class Enemy {

    private final String name;
    private int hp;

    /**
     * @pre name != null
     * @pre hp > 0
     * @post getName().equals(name)
     * @post getHP() == hp
     */
    public Enemy(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    /**
     * @post returns the enemy's name
     * @post object state remains unchanged
     */
    public String getName() {
        return name;
    }

    /**
     * @post returns the current HP
     * @post object state remains unchanged
     */
    public int getHP() {
        return hp;
    }

    /**
     * @post returns true iff hp > 0
     * @post object state remains unchanged
     */
    public boolean isAlive() {
        return hp > 0;
    }

    /**
     * @pre damage >= 0
     * @post hp == max(0, old(hp) - damage)
     */
    public void takeDamage(int damage) {
        hp = Math.max(0, hp - damage);
    }

    /**
     * @pre player != null
     * @post if this enemy is alive,
     *       the player receives 10 damage
     * @post if this enemy is dead,
     *       the player's state is unchanged
     * @post this enemy's state is unchanged
     */
    public void attack(Player player) {
        if (isAlive()) {
            player.takeDamage(10);
        }
    }
}
