package cit360.threadsexecutorsrunnables;

public class TestThread2 implements Runnable {
    private String cAccountOwner;
    private double cBankBalance;

    public TestThread2() {
        cAccountOwner = "The Bank";
        cBankBalance = 0;
    }

    public TestThread2(String accountOwner, double bankBalance) {
        cAccountOwner = accountOwner;
        cBankBalance = bankBalance;
    }

    public void run() {
       // Print out the account owner and bank balance
        System.out.format("\nAccount Owner:  %s - " +
                "Current Balance:  %2$,.2f\n", cAccountOwner, cBankBalance);
        pauseThread();
        System.out.println("Finished prinitng information for:  " + cAccountOwner);
    }

    public void pauseThread() {
        // Pauses the thread to let the user ponder on the previous output
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("Interruption Encountered:");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
