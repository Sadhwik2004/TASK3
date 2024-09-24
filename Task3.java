import java.util.Scanner;

class Account {

    String accountHolderName;
    String username;
    String userPassword;
    String accountNumber;
    float accountBalance = 100000f;
    int transactionCount = 0;
    String transactionLog = "";

    

    public void registerAccount() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Your Name - ");
        this.accountHolderName = sc.nextLine();
        System.out.print("\nEnter Your Username - ");
        this.username = sc.nextLine();
        System.out.print("\nEnter Your Password - ");
        this.userPassword = sc.nextLine();
        System.out.print("\nEnter Your Account Number - ");
        this.accountNumber = sc.nextLine();
        System.out.println("\nRegistration completed..kindly login");
    }

    public boolean loginAccount() {
        boolean isLoggedIn = false;
        Scanner sc = new Scanner(System.in);
        while (!isLoggedIn) {
            System.out.print("\nEnter Your Username - ");
            String inputUsername = sc.nextLine();
            if (inputUsername.equals(username)) {
                while (!isLoggedIn) {
                    System.out.print("\nEnter Your Password - ");
                    String inputPassword = sc.nextLine();
                    if (inputPassword.equals(userPassword)) {
                        System.out.print("\nLogin successful!!");
                        isLoggedIn = true;
                    } else {
                        System.out.println("\nIncorrect Password");
                    }
                }
            } else {
                System.out.println("\nUsername not found");
            }
        }
        return isLoggedIn;
    }

    public void withdrawAmount() {

        System.out.print("\nEnter amount to withdraw - ");
        Scanner sc = new Scanner(System.in);
        float withdrawalAmount = sc.nextFloat();
        try {

            if (accountBalance >= withdrawalAmount) {
                transactionCount++;
                accountBalance -= withdrawalAmount;
                System.out.println("\nWithdrawn Successfully");
                String transactionEntry = withdrawalAmount + " Rs Withdrawn\n";
                transactionLog = transactionLog.concat(transactionEntry);

            } else {
                System.out.println("\nInsufficient Balance");
            }

        } catch (Exception e) {
        }
    }

    public void depositAmount() {

        System.out.print("\nEnter amount to deposit - ");
        Scanner sc = new Scanner(System.in);
        float depositAmount = sc.nextFloat();

        try {
            if (depositAmount <= 100000f) {
                transactionCount++;
                accountBalance += depositAmount;
                System.out.println("\nSuccessfully Deposited");
                String transactionEntry = depositAmount + " Rs Deposited\n";
                transactionLog = transactionLog.concat(transactionEntry);
            } else {
                System.out.println("\nSorry...Deposit limit is 100000.00");
            }

        } catch (Exception e) {
        }
    }

    public void transferAmount() {

        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter Recipient's Name - ");
        String recipientName = sc.nextLine();
        System.out.print("\nEnter amount to transfer - ");
        float transferAmount = sc.nextFloat();

        try {
            if (accountBalance >= transferAmount) {
                if (transferAmount <= 50000f) {
                    transactionCount++;
                    accountBalance -= transferAmount;
                    System.out.println("\nSuccessfully Transferred to " + recipientName);
                    String transactionEntry = transferAmount + " Rs Transferred to " + recipientName + "\n";
                    transactionLog = transactionLog.concat(transactionEntry);
                } else {
                    System.out.println("\nSorry...Transfer limit is 50000.00");
                }
            } else {
                System.out.println("\nInsufficient Balance");
            }
        } catch (Exception e) {
        }
    }

    public void checkBalance() {
        System.out.println("\n" + accountBalance + " Rs");
    }

    public void showTransactionHistory() {
        if (transactionCount == 0) {
            System.out.println("\nNo Transactions Yet");
        } else {
            System.out.println("\n" + transactionLog);
        }
    }
}


public class Task3 {

    public static int takeInputWithLimit(int limit) {
        int input = 0;
        boolean isValid = false;

        while (!isValid) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                isValid = true;

                if (isValid && (input > limit || input < 1)) {
                    System.out.println("Choose a number between 1 and " + limit);
                    isValid = false;
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid integer value");
                isValid = false;
            }
        }
        return input;
    }

    public static void main(String[] args) {

        System.out.println("\n**********WELCOME TO SBI ATM SYSTEM**********\n");
        System.out.println("1. Register \n2. Exit");
        System.out.print("Enter Your Choice - ");
        int choice = takeInputWithLimit(2);

        if (choice == 1) {
            Account account = new Account();
            account.registerAccount();
            while (true) {
                System.out.println("\n1. Login \n2. Exit");
                System.out.print("Enter Your Choice - ");
                int option = takeInputWithLimit(2);
                if (option == 1) {
                    if (account.loginAccount()) {
                        System.out.println("\n\n**********WELCOME BACK " + account.accountHolderName + " **********\n");
                        boolean isSessionActive = false;
                        while (!isSessionActive) {
                            System.out.println("\n1. Withdraw \n2. Deposit \n3. Transfer \n4. Check Balance \n5. Transaction History \n6. Exit");
                            System.out.print("\nEnter Your Choice - ");
                            int actionChoice = takeInputWithLimit(6);
                            switch (actionChoice) {
                                case 1:
                                    account.withdrawAmount();
                                    break;
                                case 2:
                                    account.depositAmount();
                                    break;
                                case 3:
                                    account.transferAmount();
                                    break;
                                case 4:
                                    account.checkBalance();
                                    break;
                                case 5:
                                    account.showTransactionHistory();
                                    break;
                                case 6:
                                    isSessionActive = true;
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}

