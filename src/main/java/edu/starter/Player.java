package edu.starter;

public class Player {

    private int hp;
    private int resource;
    private boolean defending;

    public Player() {
        this.hp = 100;
        this.resource = 5;
        this.defending = false;
    }

    public int getHP() {
        return hp;
    }

    public int getResource() {
        return resource;
    }

    public boolean isAlive() {
        return hp > 0;
    }

    public void defend() {
        defending = true;
    }

    public void clearDefense() {
        defending = false;
    }

    public void takeDamage(int damage) {
        if (damage < 0) {
            return;
        }

        if (defending) {
            damage = Math.max(0, damage - 5);
            defending = false;
        }

        hp = Math.max(0, hp - damage);
    }

    public boolean spendResource(int amount) {
        if (amount < 0 || resource < amount) {
            return false;
        }

        resource -= amount;
        return true;
    }

    public boolean recover() {
        if (resource >= 10) {
            return false;
        }

        resource = Math.min(10, resource + 3);
        return true;
    }
}
