package com.pluralsight.accounting;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //create Scanner to read user input
        Scanner myScanner = new Scanner(System.in);
        boolean input = true;

        // create a while loop to run through the prompts
        while (input) {
            System.out.println("\n==========Home Screen========== ");
            System.out.println();
            System.out.println("\tD    Add (D)eposit ");
            System.out.println("\tP    make (P)ayment (Debit) ");
            System.out.println("\tL    (L)edger ");
            System.out.println("\tX    E(x)it ");
            String choice = myScanner.nextLine().toUpperCase();

            //Create a switch statement for home screen prompts to user
            switch (choice) {
                case "D":
                    System.out.println("Enter a deposit ");
                    // method for a deposit
                    makeDeposit();
                    break;
                case "P":
                    System.out.println("Make payment ");
                    //method for a payment
                    makePayment();
                    break;
                case "L":
                    System.out.println("Ledger");
                    break;
                case "X":
                    System.out.println("Exit");
                    input = false;
                    break;
                default:
                    System.out.println("Invalid option choose on of the following D, P, L,X");
                    break;

            }
        }
    }

    private static void makeDeposit() {
    // Get amount
        System.out.println(" How much would you like to deposit?");
        Scanner depoScanner = new Scanner(System.in);
        double amount = depoScanner.nextDouble();
        depoScanner.nextLine();

        // Get payee
        System.out.println("Who's the payee");
        String payee = depoScanner.nextLine();

        //Get description
        System.out.println("Transaction description");
        String desc = depoScanner.nextLine();

        //Get date and time
        LocalDateTime dateTime = LocalDateTime.now();
        Transaction deposit = new Transaction(dateTime.toLocalDate(), dateTime.toLocalTime(), desc, payee, amount);


        System.out.println(" How much would you like to deposit?");



        System.out.println("Awesome your deposit was " + deposit + ".");

        depoScanner.nextLine();

    }
    private static void makePayment() {
        System.out.println("How much would you like to pay? ");

        Scanner payScanner = new Scanner(System.in);
        double payment = payScanner.nextDouble();

        System.out.println("Your payment was successful. ");
    }
    private static void Ledger() {
        System.out.println("Choose an entry to display ");

        Scanner myLedScanner = new Scanner(System.in);
        String Ledger = myLedScanner.nextLine();

        String option = myLedScanner.nextLine().toUpperCase();
        switch (option) {
            case "A":
                System.out.println();
                break;
            case "D":
                System.out.println();
                break;
            case  "P":
                System.out.println();
                break;
            case "R":
                System.out.println();
                break;
        }
    }

}
