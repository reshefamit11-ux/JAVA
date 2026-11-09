
package edu.starter;

public class Enemy {

    private String name;
    private int hp;

    public Enemy(String name, int hp) {
        if (hp <= 0) {
            throw new IllegalArgumentException("HP must be positive");
        }

        this.name = name;
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return hp;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            return;
        }

        hp = Math.max(0, hp - damage);
    }

    public void attack(Player player) {
        if (isAlive()) {
            player.takeDamage(10);
        }
    }
}
