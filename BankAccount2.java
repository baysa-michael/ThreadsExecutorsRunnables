package cit360.threadsexecutorsrunnables;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount2 {
    private String cAccountOwner;
    private double cBankBalance;
    private int cActionIncrement;
    private final Lock cLock;

    public BankAccount2() {
        cAccountOwner = "The Bank";
        cBankBalance = 0;
        cActionIncrement = 0;
        cLock = new ReentrantLock();
    }

    public BankAccount2(String accountOwner, double bankBalance) {
        cAccountOwner = accountOwner;
        cBankBalance = bankBalance;
        cActionIncrement = 0;
        cLock = new ReentrantLock();
    }

    public String getcAccountOwner() {
        return this.cAccountOwner;
    }

    public void setcAccountOwner(String cAccountOwner) {
        cLock.lock();

        try {
            this.cAccountOwner = cAccountOwner;
        } finally {
            cLock.unlock();
        }
    }

    public double getcBankBalance() {
        return this.cBankBalance;
    }

    public int getcActionIncrement() {
        return this.cActionIncrement;
    }

    public void setcBankBalance(double bankBalance) {
        cLock.lock();

        try {
            this.cBankBalance = bankBalance;
        } finally {
            cLock.unlock();
        }
    }

    public void doubleBalance() {
        cLock.lock();

        try {
            this.cBankBalance = this.cBankBalance * 2;
        } finally {
            cLock.unlock();
        }
    }

    public void halfBalance() {
        cLock.lock();

        try {
            this.cBankBalance = this.cBankBalance / 2;
        } finally {
            cLock.unlock();
        }
    }

    public void incrementCounter() {
        cLock.lock();

        try {
            this.cActionIncrement++;
        } finally {
            cLock.unlock();
        }
    }
}
