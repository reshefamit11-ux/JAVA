
package edu.starter;

import java.util.Scanner;

public class Main {

    /**
     * Runs the complete game.
     *
     * @post The second battle starts only
     *       after winning the first battle.
     * @post A reward is offered exactly once
     *       after the first victory.
     * @post No reward is offered after a loss.
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Player player = new Player();

        Enemy enemy1 = new Enemy("Enemy 1", 30);
        Enemy enemy2 = new Enemy("Enemy 2", 45);

        boolean firstBattleWon = false;
        boolean rewardClaimed = false;

        System.out.println("Welcome to Java Battle!");

        // First battle
        firstBattleWon =
                Battle.playBattle(player, enemy1, scanner);

        if (!firstBattleWon) {
            System.out.println("Game Over!");
            scanner.close();
            return;
        }

        // Reward is available only after victory
        if (firstBattleWon && !rewardClaimed) {

            Reward reward = chooseReward(scanner);

            rewardClaimed =
                    applyReward(player, reward, rewardClaimed);
        }

        // Second battle
        player.clearDefense();

        boolean secondWin =
                Battle.playBattle(player, enemy2, scanner);

        if (secondWin) {
            System.out.println("Overall Victory!");
        } else {
            System.out.println("Game Over!");
        }

        scanner.close();
    }

    /**
     * Asks the player to select a reward.
     *
     * @pre scanner != null
     * @post Returns RECOVERY or POWER.
     * @post Invalid input does not select
     *       a reward.
     */
    private static Reward chooseReward(Scanner scanner) {

        while (true) {

            System.out.println("\nChoose your reward:");
            System.out.println("1. RECOVERY (+25 HP)");
            System.out.println("2. POWER (+2 Attack Damage)");
            System.out.print("Your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid reward choice!");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            if (choice == 1) {
                return Reward.RECOVERY;
            }

            if (choice == 2) {
                return Reward.POWER;
            }

            System.out.println("Invalid reward choice!");
        }
    }

    /**
     * Applies a reward if it was not claimed.
     *
     * @pre player != null
     * @pre reward != null
     * @pre player.isAlive()
     * @pre The first battle has been won.
     *
     * @post If already claimed, player
     *       state remains unchanged.
     * @post Otherwise exactly one reward
     *       is applied and returns true.
     */
    private static boolean applyReward(
            Player player,
            Reward reward,
            boolean rewardClaimed) {

        if (rewardClaimed) {
            System.out.println("Reward already claimed!");
            return true;
        }

        if (reward == Reward.RECOVERY) {

            player.restoreHP(25);

            System.out.println("Recovery reward applied!");
            System.out.println("Player HP: " + player.getHP());

        } else if (reward == Reward.POWER) {

            player.increaseAttack(2);

            System.out.println("Power reward applied!");
            System.out.println(
                    "Attack Damage: " + player.getAttackDamage());
        }

        return true;
    }
}
