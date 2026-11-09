
package edu.starter;

import java.util.Scanner;
public class Battle {
    public static boolean playBattle(
            Player player, Enemy enemy, Scanner scanner) {
        System.out.println("\nBattle against " + enemy.getName());
        while (player.isAlive() && enemy.isAlive()) {
            System.out.println("\n----------------------");
            System.out.println("Player HP: " + player.getHP());
            System.out.println("Enemy HP: " + enemy.getHP());
            System.out.println("Resource: " + player.getResource());
            System.out.println("\nChoose an action:");
            System.out.println("1. Attack");
            System.out.println("2. Defend");
            System.out.println("3. Special");
            System.out.println("4. Recover");
            System.out.print("Your choice: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid choice!");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            boolean validAction = false;

            // Attack
            if (choice == 1) {

                enemy.takeDamage(10);
                System.out.println("You attacked the enemy!");
                validAction = true;

            }

            // Defend
            else if (choice == 2) {

                player.defend();
                System.out.println("You are defending!");
                validAction = true;

            }

            // Special
            else if (choice == 3) {

                validAction = special(player, enemy);

            }

            // Recover
            else if (choice == 4) {

                if (player.recover()) {
                    System.out.println("You recovered resources!");
                    validAction = true;
                } else {
                    System.out.println("Resources are already full!");
                }
            }

            // Invalid choice
            else {
                System.out.println("Invalid choice!");
            }

            // Enemy counterattack
            if (validAction && enemy.isAlive()) {

                int previousHP = player.getHP();

                enemy.attack(player);

                int damage = previousHP - player.getHP();

                System.out.println("Enemy attacks back!");
                System.out.println("You lost " + damage + " HP.");
            }
        }

        if (player.isAlive()) {
            System.out.println("You defeated " + enemy.getName() + "!");
            return true;
        }

        System.out.println("You lost the battle!");
        return false;
    }

    public static boolean special(Player player, Enemy enemy) {

        if (player.spendResource(3)) {
            enemy.takeDamage(15);
            System.out.println("You used Special!");
            return true;
        }

        System.out.println("Not enough resources!");
        return false;
    }
}
