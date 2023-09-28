import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {

        // Creating a Random object
        Random random = new Random();

        // Generating a random number between 0 and 100
        int randomNumber = random.nextInt(100); // This generates a random 32 bit integer

        int min = 1;
        int max = 100;
        int RandomInRange = random.nextInt(max - min) + min; // This generates a random number between 0 and 100

        // Creating a Scanner object
        Scanner scanner = new Scanner(System.in);

        // Getting the user's input
        int number = 0;

        // Checking if the user's input is equal to the random number in loop
        while (number != randomNumber) {

            System.out.print("Enter a number: ");
            number = scanner.nextInt();


            // Checking if the user's input is less than the random number
            if (number < randomNumber) {
                System.out.println("The number is greater than " + number);
            }

            // Checking if the user's input is greater than the random number
            else if (number > randomNumber) {
                System.out.println("The number is less than " + number);
            }

            else {
                System.out.println("CORRECT!");
            }
        }
            
            System.out.println("Do you want to play again?");
            System.out.println("1. Yes");
            System.out.println("2. No");
            int playAgain = scanner.nextInt();

            if (playAgain == 1) {
                System.out.println("Let's play again!");
            }

            else if (playAgain == 2) {
                System.out.println("Thanks for playing!");
            }

            else {
                System.out.println("Invalid input");
            }

        // Closing the Scanner object
        scanner.close();

    }
}