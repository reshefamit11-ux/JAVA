package edu.starter;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Player player = new Player();

        Enemy enemy1 = new Enemy("Enemy 1", 30);
        Enemy enemy2 = new Enemy("Enemy 2", 45);

        System.out.println("Welcome to Java Battle!");

        boolean firstWin = Battle.playBattle(
            player, enemy1, scanner
        );

        if (!firstWin) {
            System.out.println("Game Over!");
            scanner.close();
            return;
        }

        player.clearDefense();

        boolean secondWin = Battle.playBattle(
            player, enemy2, scanner
        );

        if (secondWin) {
            System.out.println("Overall Victory!");
        } else {
            System.out.println("Game Over!");
        }

        scanner.close();
    }
}
