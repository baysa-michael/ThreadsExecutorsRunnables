package cit360.threadsexecutorsrunnables;

public class BankAccount1 {
    private String cAccountOwner;
    private double cBankBalance;
    private int cActionIncrement;

    public BankAccount1() {
        cAccountOwner = "The Bank";
        cBankBalance = 0;
        cActionIncrement = 0;
    }

    public BankAccount1(String accountOwner, double bankBalance) {
        cAccountOwner = accountOwner;
        cBankBalance = bankBalance;
        cActionIncrement = 0;
    }

    public synchronized String getcAccountOwner() {
        return this.cAccountOwner;
    }

    public void setcAccountOwner(String cAccountOwner) {
        this.cAccountOwner = cAccountOwner;
    }

    public synchronized double getcBankBalance() {
        return this.cBankBalance;
    }

    public synchronized int getcActionIncrement() {
        return this.cActionIncrement;
    }

    public synchronized void setcBankBalance(double bankBalance) {
        this.cBankBalance = bankBalance;
    }

    public synchronized void doubleBalance() {
        this.cBankBalance = this.cBankBalance * 2;
    }

    public synchronized void halfBalance() {
        this.cBankBalance = this.cBankBalance / 2;
    }

    public synchronized void incrementCounter() {
        this.cActionIncrement++;
    }
}
