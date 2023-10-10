// package NumberGuessGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessGame {

    private JFrame frame;
    private JTextField guessField;
    private JButton submitButton;
    private JButton playAgainButton;
    private JLabel feedbackLabel;
    private JLabel attemptsLabel;
    private JLabel scoreLabel;

    private int generatedNumber;
    private int attempts;
    private int maxAttempts = 10;
    private int score = 0;

    public NumberGuessGame() {
        frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new FlowLayout());

        guessField = new JTextField(10);
        submitButton = new JButton("Guess");
        playAgainButton = new JButton("Play Again");
        feedbackLabel = new JLabel("");
        attemptsLabel = new JLabel("Attempts left: " + maxAttempts);
        scoreLabel = new JLabel("Score: " + score);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int userGuess = Integer.parseInt(guessField.getText());
                    checkGuess(userGuess);
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Please enter a valid number!");
                }
            }
        });

        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewRound();
            }
        });

        frame.add(new JLabel("Enter a guess (1-100):"));
        frame.add(guessField);
        frame.add(submitButton);
        frame.add(feedbackLabel);
        frame.add(attemptsLabel);
        frame.add(scoreLabel);
        frame.add(playAgainButton);

        startNewRound();
        
        frame.setVisible(true);
    }

    private void checkGuess(int guess) {
        attempts--;
        if (guess == generatedNumber) {
            feedbackLabel.setText("Correct! Well done.");
            score++;
            submitButton.setEnabled(false);
        } else if (guess < generatedNumber) {
            feedbackLabel.setText("Too low. Try again!");
        } else {
            feedbackLabel.setText("Too high. Try again!");
        }

        if (attempts <= 0 && guess != generatedNumber) {
            feedbackLabel.setText("You're out of attempts! Number was: " + generatedNumber);
            submitButton.setEnabled(false);
        }

        attemptsLabel.setText("Attempts left: " + attempts);
        scoreLabel.setText("Score: " + score);
    }

    private void startNewRound() {
        generatedNumber = new Random().nextInt(100) + 1;
        attempts = maxAttempts;
        feedbackLabel.setText("Enter your guess!");
        submitButton.setEnabled(true);
        attemptsLabel.setText("Attempts left: " + attempts);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NumberGuessGame();
        });
    }
}
