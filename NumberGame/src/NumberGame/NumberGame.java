package NumberGame;
import java.util.Random;
import java.util.Scanner;

public class NumberGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int gamesPlayed = 0;
        int gamesWon = 0;
        int highestScore = 0;

        char playAgain;

        System.out.println("====================================");
        System.out.println("     ADVANCED NUMBER GUESS GAME");
        System.out.println("====================================");

        do {
            gamesPlayed++;

            int range;
            int maxAttempts;

            System.out.println("\nChoose Difficulty:");
            System.out.println("1. Easy (1-50, 10 attempts)");
            System.out.println("2. Medium (1-100, 7 attempts)");
            System.out.println("3. Hard (1-500, 5 attempts)");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    range = 50;
                    maxAttempts = 10;
                    break;

                case 2:
                    range = 100;
                    maxAttempts = 7;
                    break;

                case 3:
                    range = 500;
                    maxAttempts = 5;
                    break;

                default:
                    System.out.println("Invalid choice! Medium selected.");
                    range = 100;
                    maxAttempts = 7;
            }

            int secretNumber = random.nextInt(range) + 1;
            int attempts = 0;
            boolean won = false;

            System.out.println("\nGuess a number between 1 and " + range);

            while (attempts < maxAttempts) {

                System.out.print("Enter your guess: ");

                while (!sc.hasNextInt()) {
                    System.out.println("Invalid input! Enter numbers only.");
                    sc.next();
                }

                int guess = sc.nextInt();
                attempts++;

                if (guess == secretNumber) {
                    won = true;
                    gamesWon++;

                    int roundScore = (maxAttempts - attempts + 1) * 20;
                    totalScore += roundScore;

                    if (roundScore > highestScore)
                        highestScore = roundScore;

                    System.out.println("\nCongratulations!");
                    System.out.println("Correct Number = " + secretNumber);
                    System.out.println("Attempts Used = " + attempts);
                    System.out.println("Round Score = " + roundScore);

                    break;
                }

                else if (guess > secretNumber) {
                    System.out.println("Too High!");
                }

                else {
                    System.out.println("Too Low!");
                }

                int difference = Math.abs(secretNumber - guess);

                if (difference <= 5)
                    System.out.println("Hint: Very Close!");
                else if (difference <= 15)
                    System.out.println("Hint: Close!");
                else
                    System.out.println("Hint: Far Away!");

                System.out.println("Attempts Remaining: "
                        + (maxAttempts - attempts));
            }

            if (!won) {
                System.out.println("\nGame Over!");
                System.out.println("Correct Number was: " + secretNumber);
            }

            System.out.println("\n========== STATISTICS ==========");
            System.out.println("Games Played : " + gamesPlayed);
            System.out.println("Games Won    : " + gamesWon);
            System.out.println("Games Lost   : " + (gamesPlayed - gamesWon));
            System.out.println("Highest Score: " + highestScore);
            System.out.println("Total Score  : " + totalScore);
            System.out.println("================================");

            System.out.print("\nPlay Again? (Y/N): ");
            playAgain = sc.next().charAt(0);

        } while (playAgain == 'Y' || playAgain == 'y');

        System.out.println("\nThanks for playing!");

        sc.close();
    }
}