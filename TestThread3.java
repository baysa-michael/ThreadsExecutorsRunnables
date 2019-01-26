package cit360.threadsexecutorsrunnables;

import java.util.concurrent.ThreadLocalRandom;

public class TestThread3 implements Runnable {
    BankAccount1 cAccount;

    public TestThread3() {
        cAccount = new BankAccount1();
    }

    public TestThread3(BankAccount1 threadAccount) {
        this.cAccount = threadAccount;
    }

    public void run() {
        // Generate a random number between 0 and 3 to determine an action
        int i = ThreadLocalRandom.current().nextInt(4);

        // Based on the random number, perform a specified action
        // Key storage variables
        String accountName;
        double accountBalance;
        int actionNumber;

        switch (i) {
            case 0:
                // Retrieve the account information in a synchronized method
                synchronized (cAccount) {
                    accountName = cAccount.getcAccountOwner();
                    accountBalance = cAccount.getcBankBalance();
                    cAccount.incrementCounter();
                    actionNumber = cAccount.getcActionIncrement();
                }

                // Display the current bank account information
                System.out.format("Action (%1$d) Thread:  %2$s" +
                        "\nAccount Owner: %3$s" +
                        "\nAccount Balance: %4$,.2f" +
                        "\nTimestamp: %5$tc\n\n",
                        actionNumber,
                        Thread.currentThread().getName(),
                        accountName,
                        accountBalance,
                        System.currentTimeMillis());
                break;
            case 1:
                // Retrieve the account information in a synchronized method
                synchronized (cAccount) {
                    cAccount.doubleBalance();
                    cAccount.incrementCounter();
                    actionNumber = cAccount.getcActionIncrement();
                }
                // Double the Bank Balance
                System.out.println("Action (" + actionNumber + ") Doubled the Balance\n");
                break;
            case 2:
                // Half the Bank Balance
                synchronized (cAccount) {
                    cAccount.halfBalance();
                    cAccount.incrementCounter();
                    actionNumber = cAccount.getcActionIncrement();
                }
                System.out.println("Action (" + actionNumber + ") Halved the Balance\n");
                break;
            case 3:
                // Add a random amount of money between 0 and 100
                Double newBalance;
                Double addRandom = ThreadLocalRandom.current().nextDouble(100);

                synchronized (cAccount) {
                    accountBalance = cAccount.getcBankBalance();
                    newBalance = accountBalance + addRandom;
                    cAccount.setcBankBalance(newBalance);
                    cAccount.incrementCounter();
                    actionNumber = cAccount.getcActionIncrement();
                }

                System.out.format("Action (%1$d) Added:  %2$,.2f\n\n", actionNumber, addRandom);
                break;
            default:
                System.out.println(Thread.currentThread().getName() +
                        " - Illegal Action Selected");
        }
    }
}
