import java.util.Scanner;

public class game1 {
    public static void main(String[] args) {
        int playerHp = 100;
        int enemyHp = 30;
        int resource = 5;
        boolean defending = false;

        Scanner in = new Scanner(System.in);

        while (playerHp > 0 && enemyHp > 0) {
            System.out.println("HP: " + playerHp + " | Enemy: " + enemyHp
                               + " | Resource: " + resource);
            System.out.println("Choose: attack / defend / special / recover");
            String choice = in.nextLine().trim().toLowerCase();

            if (choice.equals("attack")) {
                enemyHp = Math.max(0, enemyHp - 10);
            } else if (choice.equals("defend")) {
                defending = true;
            } else if (choice.equals("special")) {
                if (resource < 3) {
                    System.out.println("Not enough resource!");
                    continue; // לא מתקדם תור
                }
                resource -= 3;
                enemyHp = Math.max(0, enemyHp - 15);
            } else if (choice.equals("recover")) {
                resource = Math.min(10, resource + 3);
            } else {
                System.out.println("Invalid action.");
                continue; // לא מתקדם תור
            }

            if (enemyHp == 0) break; // האויב מת, אין תגובה

            int damage = defending ? 5 : 10;
            playerHp = Math.max(0, playerHp - damage);
            defending = false;
        }

        System.out.println(playerHp > 0 ? "You win!" : "You lose!");
    }
}