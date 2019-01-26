package cit360.threadsexecutorsrunnables;

public class TestThread1 implements Runnable {
    private String cAccountOwner;
    private double cBankBalance;

    public TestThread1() {
        cAccountOwner = "The Bank";
        cBankBalance = 0;
    }

    public TestThread1(String accountOwner, double bankBalance) {
        cAccountOwner = accountOwner;
        cBankBalance = bankBalance;
    }

    public void run() {
        // Print out the account owner and bank balance
        System.out.println("\nAccount Owner:  " + cAccountOwner);
        System.out.format("Current Balance:  %1$,.2f", cBankBalance);
    }
}
