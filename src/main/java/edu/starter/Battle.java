
package edu.starter;

import java.util.Scanner;

public class Battle {

    private static final int DELAY = 800;

    /**
     * Pauses the game between messages.
     *
     * @pre milliseconds >= 0
     * @post Waits approximately the requested time,
     *       unless the thread is interrupted.
     */
    private static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Prints a message and pauses briefly.
     *
     * @pre message != null
     * @post The message is printed before the pause.
     */
    private static void slowPrint(String message) {
        System.out.println(message);
        System.out.flush();
        pause(DELAY);
    }

    /**
     * Displays the battle rules.
     *
     * @post The rules are printed.
     * @post Player and enemy states are unchanged.
     */
    private static void showRules() {
        slowPrint("\n========== BATTLE RULES ==========");
        slowPrint("Defeat the enemy before your HP reaches 0.");
        slowPrint("1. Attack: Deal your basic attack damage.");
        slowPrint("2. Defend: Reduce the next enemy attack by 5 HP.");
        slowPrint("3. Special: Deal 15 damage for 3 resources.");
        slowPrint("4. Recover: Restore 3 resources (maximum 10).");
        slowPrint("The enemy deals 10 damage after a valid turn.");
        slowPrint("If you defeat the enemy, it cannot attack back.");
        slowPrint("==================================\n");
    }

    /**
     * Displays the player's current status.
     *
     * @pre player != null
     * @pre enemy != null
     * @post Current HP, resource and attack
     *       values are printed.
     * @post Player and enemy states are unchanged.
     */
    private static void showStatus(Player player, Enemy enemy) {
        slowPrint("\n----------------------");
        slowPrint("Player HP: " + player.getHP());
        slowPrint("Enemy HP: " + enemy.getHP());
        slowPrint("Resource: " + player.getResource());
        slowPrint("Attack Damage: " + player.getAttackDamage());
    }

    /**
     * Displays the available actions.
     *
     * @post All four actions are printed.
     */
    private static void showMenu() {
        slowPrint("\nChoose an action:");
        slowPrint("1. Attack");
        slowPrint("2. Defend");
        slowPrint("3. Special");
        slowPrint("4. Recover");

        System.out.print("Your choice: ");
        System.out.flush();
    }

    /**
     * Runs a battle between a player and an enemy.
     *
     * @pre player != null
     * @pre enemy != null
     * @pre scanner != null
     * @pre player.isAlive()
     * @pre enemy.isAlive()
     *
     * @post Returns true iff the player wins.
     * @post Returns false iff the player loses.
     * @post The battle ends when either
     *       player or enemy reaches 0 HP.
     */
    public static boolean playBattle(
            Player player, Enemy enemy, Scanner scanner) {

        slowPrint("\nBattle against " + enemy.getName());

        showRules();
        player.clearDefense();

        while (player.isAlive() && enemy.isAlive()) {

            // Display status and menu slowly
            showStatus(player, enemy);
            showMenu();

            if (!scanner.hasNextInt()) {
                slowPrint("Invalid choice!");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            boolean validAction = false;

            // Attack
            if (choice == 1) {

                int damage = player.getAttackDamage();
                enemy.takeDamage(damage);

                slowPrint("You attacked the enemy!");
                slowPrint("Enemy lost " + damage + " HP.");

                validAction = true;

            }

            // Defend
            else if (choice == 2) {

                player.defend();

                slowPrint("You are defending!");
                validAction = true;

            }

            // Special
            else if (choice == 3) {

                validAction = special(player, enemy);

            }

            // Recover
            else if (choice == 4) {

                if (player.recover()) {

                    slowPrint("You recovered resources!");
                    validAction = true;

                } else {

                    slowPrint("Resources are already full!");
                }
            }

            // Invalid action
            else {

                slowPrint("Invalid choice!");
            }

            // Enemy's turn
            if (validAction && enemy.isAlive()) {

                if (choice == 2) {
                    slowPrint(
                        "Enemy attacks, but your defense reduces the damage!"
                    );
                } else if (choice == 4) {
                    slowPrint("Enemy attacks while you recover!");
                } else {
                    slowPrint("Enemy attacks back!");
                }

                int previousHP = player.getHP();

                enemy.attack(player);

                int damageTaken = previousHP - player.getHP();

                slowPrint("You lost " + damageTaken + " HP.");
            }
        }

        // Battle result
        if (player.isAlive()) {

            slowPrint("You defeated " + enemy.getName() + "!");
            return true;

        } else {

            slowPrint("You lost the battle!");
            return false;
        }
    }

    /**
     * Performs a special attack.
     *
     * @pre player != null
     * @pre enemy != null
     *
     * @post If the player has at least 3 resources:
     *       resource decreases by 3,
     *       enemy receives 15 damage,
     *       and returns true.
     *
     * @post Otherwise player and enemy
     *       states remain unchanged
     *       and returns false.
     */
    public static boolean special(Player player, Enemy enemy) {

        if (player.spendResource(3)) {

            enemy.takeDamage(15);

            slowPrint("You used Special!");
            slowPrint("Enemy lost 15 HP.");

            return true;
        }

        slowPrint("Not enough resources!");
        return false;
    }
}
