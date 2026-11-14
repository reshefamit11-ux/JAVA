
package edu.starter;

/**
 * Represents the player in the battle game.
 *
 * Class Invariants:
 * 0 <= hp && hp <= maxHP
 * maxHP == 100
 * 0 <= resource && resource <= 10
 * attackDamage >= 10
 */
public class Player {

    private int hp;
    private final int maxHP;
    private int resource;
    private int attackDamage;
    private boolean defending;

    /**
     * @post hp == 100
     * @post resource == 5
     * @post attackDamage == 10
     * @post defending == false
     */
    public Player() {
        this.hp = 100;
        this.maxHP = 100;
        this.resource = 5;
        this.attackDamage = 10;
        this.defending = false;
    }

    /** @post returns the current HP without changing state */
    public int getHP() {
        return hp;
    }

    /** @post returns the current resource without changing state */
    public int getResource() {
        return resource;
    }

    /** @post returns the current attack damage without changing state */
    public int getAttackDamage() {
        return attackDamage;
    }

    /** @post returns true iff hp > 0 */
    public boolean isAlive() {
        return hp > 0;
    }

    /**
     * @post defending == true
     * @post hp and resource remain unchanged
     */
    public void defend() {
        defending = true;
    }

    /**
     * @post defending == false
     */
    public void clearDefense() {
        defending = false;
    }

    /**
     * @pre damage >= 0
     * @post hp decreases by damage, or by
     *       max(0, damage - 5) if defending
     * @post hp never becomes negative
     * @post defending becomes false if it was true
     */
    public void takeDamage(int damage) {
        if (defending) {
            damage = Math.max(0, damage - 5);
            defending = false;
        }

        hp = Math.max(0, hp - damage);
    }

    /**
     * @pre amount >= 0
     * @post if enough resource exists:
     *       resource decreases by amount
     *       and returns true
     * @post otherwise resource is unchanged
     *       and returns false
     */
    public boolean spendResource(int amount) {
        if (resource < amount) {
            return false;
        }

        resource -= amount;
        return true;
    }

    /**
     * @post if resource < 10:
     *       resource increases by up to 3
     *       and returns true
     * @post otherwise returns false
     *       without changing resource
     */
    public boolean recover() {
        if (resource >= 10) {
            return false;
        }

        resource = Math.min(10, resource + 3);
        return true;
    }

    /**
     * @pre amount > 0
     * @post hp == min(maxHP, old(hp) + amount)
     * @post resource and attackDamage are unchanged
     */
    public void restoreHP(int amount) {
        hp = Math.min(maxHP, hp + amount);
    }

    /**
     * @pre amount > 0
     * @post attackDamage == old(attackDamage) + amount
     * @post hp and resource are unchanged
     */
    public void increaseAttack(int amount) {
        attackDamage += amount;
    }
}
