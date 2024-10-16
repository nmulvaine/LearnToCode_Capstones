package com.pluralsight.accounting;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        //File reader is reading the transaction.csv file
        FileReader fileReader = new FileReader("./src/main/resources/transaction.csv");

        //Buffer reader is reading and storing data from the file reader
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        //Reading the first line of the transaction.csv but not saving it
        String fileInput = bufferedReader.readLine();

        //initializing of an array list of transaction objects
        ArrayList<Transaction> ledger = new ArrayList<>();

        // reads and goes through the transaction csv until there is nothing left
        while ((fileInput = bufferedReader.readLine()) != null) {
            String[] split = fileInput.split("[|]");
            Transaction transaction = new Transaction(LocalDate.parse(split[0]), LocalTime.parse(split[1]), split[2], split[3], Double.parseDouble(split[4]));
            ledger.add(transaction);

        }

        //create Scanner to read user input
        Scanner myScanner = new Scanner(System.in);
        boolean input = true;

        // create a while loop to run through the prompts
        while (input) {
            System.out.println("\n==========Home Screen========== ");
            System.out.println();
            System.out.println("""
                    (D) add deposit
                    (p) make payment
                    (L) Ledger
                    (X) Exit
                    """);
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
                    //method for ledger
                    Ledger();
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


        boolean input = true;

        while (input) {
            System.out.println("""
                    (A) Display all entries
                    (D) deposits
                    (P) payments
                    (R) reports
                    
                    """);

            //Create a switch statement for home screen prompts to user
            String option = myLedScanner.nextLine().toUpperCase();
            switch (option) {
                case "A":
                    System.out.println("show all entries");
                    //method for all entries
                    break;
                case "D":
                    System.out.println("Showing all deposits");
                    //method for deposits
                    break;
                case "P":
                    System.out.println("Showing all payments");
                    //method for payments
                    break;
                case "R":
                    System.out.println("Showing all reports");
                    input = false;
                    break;
                default:
                    System.out.println("Invalid option choose on of the following (L),(D),(P),(R)");
            }
        }


    }
}
