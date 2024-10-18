# Accounting Ledger Application

This Accounting Ledger application is designed to manage and track financial transactions such as deposits and payments. Users can interact with the application via a command-line interface (CLI), where they can view their transaction history, make deposits, payments, and generate various financial reports. Transactions are stored in a CSV file for persistence.



## Features

* Add Deposits: Allows users to record deposits to their account.

* Make Payments: Allows users to record payments made from their account.

* View Ledger: Displays all transactions, categorized by deposits, payments, or full transaction history.

* Generate Reports: Provides options to generate:

* Month-to-date transactions

* Previous month transactions

* Year-to-date transactions

* Previous year transactions

* Search transactions by vendor

* Data Persistence: All transactions are stored in a CSV file for easy retrieval and modification.



### Usage

When you run the application, you will be presented with a home screen offering various options:

* D - Add a deposit
* P - Make a payment
* L - View ledger (transactions)
* X - Exit the application



#### Example: Adding a Deposit

* Select D from the home screen.

* Enter the amount, payee, and description for the deposit.

* The transaction will be saved and added to the ledger.



##### Example: Viewing Ledger

* Select L from the home screen.

* Choose from options like A (All Entries), D (Deposits), P (Payments), or R (Reports).

* You can also generate reports for specific date ranges or search by vendor.




##### CSV Format

* The application reads and writes transactions to a CSV file (transaction.csv). Each transaction is stored in the following format:

* Date: The date of the transaction in YYYY-MM-DD format.

* Time: The time of the transaction in HH:MM:SS format.

* Description: A brief description of the transaction.

* Vendor: The payee or vendor associated with the transaction.

Amount: The transaction amount (positive for deposits, negative for payments).

###### Interesting code

![Screenshot 2024-10-18 084800](https://github.com/user-attachments/assets/d94eb4b1-7376-4232-af6c-86ccccb84ae3)

The switch statement checks the user's choice and performs different actions based on what the user selected:

###### Screens

![Screenshot 2024-10-17 204442](https://github.com/user-attachments/assets/7a60f3a0-4ca8-4710-9c69-ff1eeee1c95f)

![Screenshot 2024-10-17 204641](https://github.com/user-attachments/assets/0f76823c-0dce-46eb-857a-8bfbd30b33a9)

![Screenshot 2024-10-17 205000](https://github.com/user-attachments/assets/12a91f61-2f4b-4d6b-932a-30e7bdecf854)








