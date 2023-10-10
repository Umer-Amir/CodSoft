package ATMInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Class to represent a User's Bank Account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public double checkBalance() {
        return this.balance;
    }
}

public class ATMInterface {

    private JFrame frame;
    private JTextField inputField;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton checkBalanceButton;
    private JLabel resultLabel;

    private BankAccount account;

    public ATMInterface() {
        // Initializing account with some balance for testing
        account = new BankAccount(1000.0);

        frame = new JFrame("ATM Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new GridLayout(5, 2));

        // Components for the GUI
        inputField = new JTextField();
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        checkBalanceButton = new JButton("Check Balance");
        resultLabel = new JLabel("Result will appear here");

        // Action listeners for the buttons
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(inputField.getText());
                    account.deposit(amount);
                    resultLabel.setText("Deposited: $" + amount);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid amount!");
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(inputField.getText());
                    if (account.withdraw(amount)) {
                        resultLabel.setText("Withdrew: $" + amount);
                    } else {
                        resultLabel.setText("Insufficient funds!");
                    }
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid amount!");
                }
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultLabel.setText("Balance: $" + account.checkBalance());
            }
        });

        // Adding components to the frame
        frame.add(new JLabel("Enter Amount:"));
        frame.add(inputField);
        frame.add(depositButton);
        frame.add(withdrawButton);
        frame.add(checkBalanceButton);
        frame.add(resultLabel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ATMInterface();
        });
    }
}
