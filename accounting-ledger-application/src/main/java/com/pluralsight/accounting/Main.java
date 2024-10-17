package com.pluralsight.accounting;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    //initializing of an array list of transaction objects
    static ArrayList<Transaction> ledger;
    static final String csvFileName = "./src/main/resources/transaction.csv";

    public static void main(String[] args) throws IOException {
        ledger = new ArrayList<>();

        try {
            loadTransactions();
        } catch (Exception exo) {
            System.out.println(exo.getLocalizedMessage());
        }

        System.out.println(ledger);

        //create Scanner to read user input
        Scanner myScanner = new Scanner(System.in);
        boolean input = true;

        // create a while loop that continuously runs through the prompts until user exits
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

            //Create a switch statement to cover user input
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
                    System.out.println("Exiting");
                    input = false;
                    break;
                default:
                    System.out.println("Invalid option choose on of the following D, P, L,X");
                    break;
            }
        }
    }

    private static void loadTransactions() throws Exception { // this is wrong we should be handling the exception aka a try/catch
        //File reader is reading the transaction.csv file
        FileReader fileReader = new FileReader(csvFileName);

        //Buffer reader is reading and storing data from the file reader
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        //Reading the first line of the transaction.csv but not saving it
        String fileInput = bufferedReader.readLine();


        // reads and goes through the transaction csv until there is nothing left
        while ((fileInput = bufferedReader.readLine()) != null) {

            String[] split = fileInput.split("\\|");

            System.out.println(split.length);




                //  Transaction transaction = new Transaction(LocalDate.parse(split2[0]), LocalTime.parse(split2[1]), split2[2], split2[3], Double.parseDouble(split2[4]));
                Transaction transaction = new Transaction(LocalDate.parse(split[0]), LocalTime.parse(split[1]), split[2], split[3], Double.parseDouble(split[4]));
                ledger.add(transaction);
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

        // hee we don't need to create a new transaction
        //instead we need to take the inout from the user and
        //write it to the csv file
        Transaction deposit = new Transaction(dateTime.toLocalDate(), dateTime.toLocalTime(), desc, payee, amount);


        //  System.out.println(" How much would you like to deposit?");

        System.out.println("Awesome your deposit was " + deposit + ".");


        // depoScanner.nextLine();

        saveTransactionToFile(deposit);
    }
//======================================================================================================================

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
                    (H) Home
                    
                    """);

            //Create a switch statement for home screen prompts to user
            String option = myLedScanner.nextLine().toUpperCase();
            switch (option) {
                case "A":
                    System.out.println("show all entries");
                    viewAllTransactions();
                    //method for all entries
                    break;
                case "D":
                    System.out.println("Showing all deposits");
                    //method for deposits
                    showDeposits();
                    break;
                case "P":
                    System.out.println("Showing all payments");
                    //method for payments
                    showPayments();
                    break;
                case "R":
                    System.out.println("Showing all reports");
                   reports();
                    break;
                case "H":
                    System.out.println("Returning home");
                    input = false;
                    break;
                default:
                    System.out.println("Invalid option choose on of the following (L),(D),(P),(R)");
            }
        }
    }
    //ledger Logic

    //viewAll
    public static void viewAllTransactions() {

        System.out.println("======= All Transactions =======");
        for (Transaction transaction : ledger) {
            System.out.println(transaction);
        }
        // keep in  mind that this array list only includes the transactions that are in the file
        //when the app is initally started
        //any new transaction that were added won't show unless
        // you read from the file again to get an updated

    }

    //helper method to help us write t the csv file
    //aka to save a transaction
    static void saveTransactionToFile(Transaction transaction) {
        StringBuilder sb = new StringBuilder();

        try (FileWriter fw = new FileWriter(csvFileName, true)) {

            sb.append(transaction.getDate().toString());
            sb.append("|");
            sb.append(transaction.getTime().toString());
            sb.append("|");
            sb.append(transaction.getDescription());
            sb.append("|");
            sb.append(transaction.getVendor());
            sb.append("|");
            sb.append(transaction.getAmount());

            String newEntry = sb.toString();
            fw.write(newEntry);
            fw.write("\n");
            // fw.close();

            ledger.add(transaction);
        } catch (Exception ioExp) {
            ioExp.getLocalizedMessage();
            System.out.println("Error");
        }
    }

    public static void showDeposits() {
        for (Transaction transaction : ledger) {
            if (transaction.getAmount() > 0) {
                System.out.println(transaction);
            }
        }
    }

    public static void showPayments() {
        for (Transaction transaction : ledger) {
            if (transaction.getAmount() < 0) {
                System.out.println(transaction);
            }
        }
    }

    private static void reports() {
        System.out.println("Choose a report to display");

        Scanner myReports = new Scanner(System.in);

        Boolean input = true;

        while (input) {
            System.out.println("""
                    1) Month To Date
                    2) Previous Month
                    3) Year to date
                    4) Previous year
                    5) Search by Vendor
                    6) Return to Ledger
                    """);
            String option = myReports.nextLine();

            switch (option) {
                case "1":
                    System.out.println("Displaying month to date");
                    // method for month to date
                    monthToDate();
                    break;
                case "2":
                    System.out.println("Displaying previous month");
                    // method for previous month
                    prevMonth();
                    break;
                case "3":
                    System.out.println("Displaying year to date");
                    // method for year to date
                    yearToDate();
                    break;
                case "4":
                    System.out.println("Previous year");
                    // method for previous year
                    prevYear();
                    break;
                case "5":
                    System.out.println("Search by vendor");
                    // method for searching by vendor
                    vendorSearch();
                    break;
                case "6":
                    System.out.println("Back to Ledger Menu");
                    input = false;
                    break;
                default:
                    System.out.println("Invalid response please choose again");

                    break;
            }
        }
    }
    public static void monthToDate() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayofmonth = today.withDayOfMonth(1);
        System.out.println("======= Month To Date =======");

        for (Transaction transaction: ledger) {
            if (!transaction.getDate().isBefore(firstDayofmonth) && !transaction.getDate().isAfter(today)) {
                System.out.println(transaction);
            }
        }
    }
    public static void prevMonth() {
        LocalDate today = LocalDate.now();
        YearMonth previousMonth = YearMonth.from(today).minusMonths(1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" MMMM, yyyy");
        String formattedPrevMonth = previousMonth.format(formatter);

        System.out.println("previous month" + formattedPrevMonth);
    }
    public static void yearToDate() {
        LocalDate today = LocalDate.now();

        LocalDate startOfYear = LocalDate.of(today.getYear(),1, 1);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" MMMM dd yyyy ");
        String formattedStartOfYear = startOfYear.format(formatter);
        String formattedToday = today.format(formatter);

        System.out.println("year to date" + formattedStartOfYear + "to" + formattedToday);
    }
    public static void prevYear() {

        LocalDate today = LocalDate.now();

        int prevYear = today.getYear() -1;
        LocalDate startOfPrevYear = LocalDate.of(prevYear,1,1);
        LocalDate endOfPrevOfYear = LocalDate.of(prevYear, 12, 31);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        String formattedStartOfPrevYear = startOfPrevYear.format(formatter);
        String formattedEndOfYear = endOfPrevOfYear.format(formatter);

        System.out.println(" previous year " + formattedStartOfPrevYear + " to " + formattedEndOfYear);
    }
    public static void vendorSearch() {
        Scanner searchVendorScanner = new Scanner(System.in);

        System.out.println("Please enter the name of the vendor you're searching for ");
        String search = searchVendorScanner.nextLine().trim().toLowerCase();

        boolean vendorFound = false;

        for (Transaction transaction: ledger) {
            if (transaction.getVendor().toLowerCase().contains(search)) {
                System.out.println("Found vendor" + transaction.getVendor());
                System.out.println(transaction);
                vendorFound = true;
            }
            if (!vendorFound) {
                System.out.println("No vendor found" + search);
            }
        }
    }
}


