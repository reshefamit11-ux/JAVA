package edu.starter;

import java.util.Scanner;

public final class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Game2 game = new Game2();
        game.play(scanner);

        scanner.close();
    }
}