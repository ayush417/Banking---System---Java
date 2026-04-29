import DAO.AccountDAO;
import DAO.UserDAO;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserDAO dao = new UserDAO();
        AccountDAO ad = new AccountDAO();

        while (true) {

            System.out.println("\nWelcome to XYZ Bank");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int ch = sc.nextInt();
            String email = null;

            switch (ch) {

                case 1:
                    System.out.println("Enter your email:");
                    email = sc.next();

                    System.out.println("Enter your name:");
                    String full_name = sc.next();

                    System.out.println("Enter your password:");
                    String password = sc.next();

                    boolean isuser = dao.registerUser(email, full_name, password);
                    if (isuser) {
                        System.out.println("Registration Successful!");
                    }
                    else {
                        System.out.println("User already exists please login");
                    }
                    break;

                case 2:
                    System.out.println("Enter your email:");
                    email = sc.next();

                    System.out.println("Enter your password:");
                    String pass = sc.next();

                    boolean isvalid = dao.loginUser(email, pass);

                    if (isvalid) {
                        System.out.println("Login Successful");

                        System.out.println("1. Create Account");
                        System.out.println("2. Continue to Banking");
                        int accchoice = sc.nextInt();

                        if (accchoice == 1) {

                            System.out.println("Enter 4-digit pin:");
                            String pin = sc.next();
                            boolean success = ad.createAccount(email , pin);
                            if (success){
                                System.out.println("Account Created Successfully");
                            }
                            else{
                                System.out.println("Account creation failed(already exist or invalid)");
                            }


                        }

                        // Banking Menu
                        while (true) {
                            System.out.println("\n--- Banking Menu ---");
                            System.out.println("1. Show Balance");
                            System.out.println("2. Deposit");
                            System.out.println("3. Withdraw");
                            System.out.println("4. Logout");

                            int choice = sc.nextInt();

                            switch (choice) {

                                case 1:
                                    System.out.println("Enter your pin:");
                                    String pin = sc.next();

                                    double balnc = ad.showBalance(pin, email);
                                    if (balnc == -1) {
                                        System.out.println("Invalid User");
                                    } else {
                                        System.out.println("Your balance is: " + balnc);
                                    }
                                    break;

                                case 2:
                                    System.out.println("Enter your pin:");
                                    pin = sc.next();

                                    System.out.println("Enter the amount:");
                                    double amount = sc.nextDouble();

                                    if (amount <= 0) {
                                        System.out.println("Enter valid amount");
                                    } else {
                                        boolean istrue = ad.depositMoney(pin, email, amount);

                                        if (istrue) {
                                            System.out.println("Balance added successfully");
                                            double balance = ad.showBalance(pin, email);
                                            if (balance == -1) {
                                                System.out.println("Invalid User");
                                            } else {
                                                System.out.println("Updated balance: " + balance);
                                            }
                                        }
                                    }
                                    break;

                                case 3:

                                    System.out.println("Enter your pin:");
                                    pin = sc.next();

                                    System.out.println("Enter the amount:");
                                    amount = sc.nextDouble();
                                    double bl = ad.showBalance(pin, email);

                                    if (amount <= 0) {
                                        System.out.println("Enter valid amount");
                                    } else if (amount > bl ) {
                                        System.out.println("Insufficent Balance");
                                    } else {
                                        boolean valid = ad.withdraw(pin, email, amount);

                                        if (valid) {
                                            double updated = ad.showBalance(pin, email);
                                            System.out.println("Updated balance: " + updated);
                                        } else {
                                            System.out.println("Insufficient balance or invalid user");
                                        }
                                    }
                                    break;

                                case 4:
                                    System.out.println("Logged Out");
                                    break;

                                default:
                                    System.out.println("Invalid Option");
                            }

                            if (choice == 4) {
                                break;
                            }
                        }

                    } else {
                        System.out.println("Wrong Credentials");
                    }

                    break;

                case 3:
                    System.out.println("Thank you for using XYZ Bank");
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}