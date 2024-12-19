//NUMBER GAME(TASK 1)
 
import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int MAX_ATTEMPTS = 10;

    public static void playRound() {
        Random rand = new Random();
        int lower = 1, upper = 100;
        int target = rand.nextInt(upper - lower + 1) + lower;
        int guess, attempts = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess the number between " + lower + " and " + upper + ".");

        while (attempts < MAX_ATTEMPTS) {
            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            attempts++;

            if (guess < target) {
                System.out.println("Your guess is too low.");
            } else if (guess > target) {
                System.out.println("Your guess is too high.");
            } else {
                System.out.println("Congratulations! You guessed the correct number.");
                break;
            }

            if (attempts == MAX_ATTEMPTS) {
                System.out.println("Sorry! You've reached the maximum attempts. The correct number was " + target + ".");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int playAgain;

        do {
            playRound();

            System.out.print("Do you want to play again? (1 for Yes, 0 for No): ");
            playAgain = scanner.nextInt();
        } while (playAgain == 1);

        System.out.println("Thank you for playing!");
    }
}
