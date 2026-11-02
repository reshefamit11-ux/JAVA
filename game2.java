import java.util.Scanner;

public class game2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int playerHP = 100;
        int enemyHP = 30;
        int resource = 5;
        boolean defending = false;

        System.out.println("Welcome to Java Battle!");

        while (playerHP > 0 && enemyHP > 0) {

            System.out.println("\n----------------------");
            System.out.println("Player HP: " + playerHP);
            System.out.println("Enemy HP: " + enemyHP);
            System.out.println("Resource: " + resource);

            System.out.println("\nChoose an action:");
            System.out.println("1. Attack");
            System.out.println("2. Defend");
            System.out.println("3. Special");
            System.out.println("4. Recover");

            System.out.print("Your choice: ");

            // Check that the input is an integer
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid choice!");
                scanner.next();
                continue;
            }

            int choice = scanner.nextInt();

            boolean validAction = false;
            defending = false;

            // Attack
            if (choice == 1) {

                enemyHP = Math.max(0, enemyHP - 10);

                System.out.println("You attacked the enemy!");
                System.out.println("Enemy lost 10 HP.");

                validAction = true;

            }

            // Defend
            else if (choice == 2) {

                defending = true;

                System.out.println("You are defending!");

                validAction = true;

            }

            // Special
            else if (choice == 3) {

                if (resource >= 3) {

                    resource = resource - 3;
                    enemyHP = Math.max(0, enemyHP - 15);

                    System.out.println("You used Special!");
                    System.out.println("Enemy lost 15 HP.");

                    validAction = true;

                } else {

                    System.out.println("Not enough resources!");

                }
            }

            // Recover
            else if (choice == 4) {

                if (resource < 10) {

                    resource = Math.min(10, resource + 3);

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
            if (validAction && enemyHP > 0) {

                int damage = 10;

                if (defending) {
                    damage = 5;
                }

                playerHP = Math.max(0, playerHP - damage);

                System.out.println("Enemy attacks back!");
                System.out.println("You lost " + damage + " HP.");
            }

            // Reset defense
            defending = false;
        }

        // End of game
        System.out.println("\n----------------------");

        if (enemyHP == 0) {
            System.out.println("You win!");
        } else {
            System.out.println("Game Over! You lose!");
        }

        System.out.println("Final Player HP: " + playerHP);
        System.out.println("Final Enemy HP: " + enemyHP);

        scanner.close();
    }
}