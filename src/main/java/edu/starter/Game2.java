package edu.starter;

import java.util.Scanner;

public class Game2 {

    private int playerHP;
    private int enemyHP;
    private int resource;
    private boolean defending;

    public Game2() {
        this.playerHP = 100;
        this.enemyHP = 30;
        this.resource = 5;
        this.defending = false;
    }

    public void play(Scanner scanner) {
        System.out.println("Welcome to Java Battle!");

        while (playerHP > 0 && enemyHP > 0) {
            printStatus();
            printMenu();

            if (!scanner.hasNextInt()) {
                System.out.println("Invalid choice!");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();
            boolean validAction = handlePlayerChoice(choice);

            if (validAction && enemyHP > 0) {
                enemyTurn();
            }

            defending = false;
        }

        printGameOver();
    }

    private void printStatus() {
        System.out.println("\n----------------------");
        System.out.println("Player HP: " + playerHP);
        System.out.println("Enemy HP: " + enemyHP);
        System.out.println("Resource: " + resource);
    }

    private void printMenu() {
        System.out.println("\nChoose an action:");
        System.out.println("1. Attack");
        System.out.println("2. Defend");
        System.out.println("3. Special");
        System.out.println("4. Recover");
        System.out.print("Your choice: ");
    }

    private boolean handlePlayerChoice(int choice) {
        defending = false;

        switch (choice) {
            case 1:
                enemyHP = Math.max(0, enemyHP - 10);
                System.out.println("You attacked the enemy!");
                System.out.println("Enemy lost 10 HP.");
                return true;

            case 2:
                defending = true;
                System.out.println("You are defending!");
                return true;

            case 3:
                if (resource >= 3) {
                    resource -= 3;
                    enemyHP = Math.max(0, enemyHP - 15);
                    System.out.println("You used Special!");
                    System.out.println("Enemy lost 15 HP.");
                    return true;
                } else {
                    System.out.println("Not enough resources!");
                    return false;
                }

            case 4:
                if (resource < 10) {
                    resource = Math.min(10, resource + 3);
                    System.out.println("You recovered resources!");
                    return true;
                } else {
                    System.out.println("Resources are already full!");
                    return false;
                }

            default:
                System.out.println("Invalid choice!");
                return false;
        }
    }

    private void enemyTurn() {
        int damage = defending ? 5 : 10;
        playerHP = Math.max(0, playerHP - damage);

        System.out.println("Enemy attacks back!");
        System.out.println("You lost " + damage + " HP.");
    }

    private void printGameOver() {
        System.out.println("\n----------------------");
        if (enemyHP == 0) {
            System.out.println("You win!");
        } else {
            System.out.println("Game Over! You lose!");
        }
        System.out.println("Final Player HP: " + playerHP);
        System.out.println("Final Enemy HP: " + enemyHP);
    }
}