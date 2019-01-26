package cit360.threadsexecutorsrunnables;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to this tutorial on Threads, Executables, and Runnables");

        System.out.println("-------------------------EXAMPLE 1---------------------------");
        System.out.println("The first example demonstrates setting up a number of");
        System.out.println("threads using an ExecutorService class.  The action to be");
        System.out.println("executed comes from a class which extends Runnable and");
        System.out.println("contains the run function.");
        System.out.println("A map containing 4 records is used to create 4 separate");
        System.out.println("threads.  As no implicit or explicit locks are put on any of");
        System.out.println("the threads, rerunning this section of code will");
        System.out.println("produce different output results each time as the output ");
        System.out.println("is not in a guaranteed order.  Even the two println commands");
        System.out.println("per account can print at random places.");

        // Collection for Initial Bank Balances
        Map<String, Double> initialBalances = new HashMap<String, Double>();

        initialBalances.put("Michael", 223.54);
        initialBalances.put("John", 110.00);
        initialBalances.put("Harry", 524.89);
        initialBalances.put("Masaki", 293.50);

        // Print out original data set
        System.out.println("\n\nOriginal Map Data & Order:");
        for(Map.Entry<String, Double> entry : initialBalances.entrySet()) {
            System.out.format("Account Name:  %s - Account Balance:  %2$,.2f\n",
                    entry.getKey(), entry.getValue());
        }
        System.out.println();


        // Create a new Executor Service to handle the threads for displaying
        // account balances
        ExecutorService myExecutor1 = Executors.newFixedThreadPool(initialBalances.size());

        // Create an iterator for the map
        Iterator<Map.Entry<String, Double>> myIterator =
                initialBalances.entrySet().iterator();

        // Send all instances of initial balances to separate threads to read & display
        while(myIterator.hasNext()) {
            // Get the next entry values
            Map.Entry<String, Double> currentEntry = myIterator.next();

            // Create a new runnable thread
            TestThread1 newAccount = new TestThread1(currentEntry.getKey(),
                    currentEntry.getValue());

            // Run the thread with the executor
            myExecutor1.execute(newAccount);
        }

        // Shut down the executor when all processes are done - loop until done
        myExecutor1.shutdown();
        while (!myExecutor1.isTerminated()) { }

        // Let the user know that all threads have terminated
        System.out.println("\n\nAll Threads Completed");
        System.out.println("------------------------END EXAMPLE 1------------------------");
        System.out.println();
        System.out.println("-------------------------EXAMPLE 2---------------------------");
        System.out.println("Threads can also be paused using the Thread.sleep() command.");
        System.out.println("This example pauses a thread for 5 seconds before displaying");
        System.out.println("a finish notification on the console.");
        System.out.println("Again, order is not guaranteed with this execution.");

        // As the previous executor was shut down, create a new executor to handle this set of code
        ExecutorService myExecutor2 = Executors.newFixedThreadPool(initialBalances.size());

        // Loop through and create 4 new threads with the second TestThread method
        initialBalances.forEach((name, balance) ->
                myExecutor2.execute(new TestThread2(name, balance)));

        // Shut down the executor when all processes are done - loop until done
        myExecutor2.shutdown();
        while (!myExecutor2.isTerminated()) { }

        // Let the user know that all threads have terminated
        System.out.println("\n\nAll Threads Completed");
        System.out.println("------------------------END EXAMPLE 2------------------------");
        System.out.println();
        System.out.println("-------------------------EXAMPLE 3---------------------------");
        System.out.println("Synchronization of a class or methods within a class provides");
        System.out.println("a solution to multiple threads accessing a mutable variable.");
        System.out.println("Utilizing this method properly will help to make sure that");
        System.out.println("data retrieved on a thread separate from data being set on");
        System.out.println("another thread will be accessed in a way to maintain its");
        System.out.println("consistency.");

        System.out.println("\nThis example sets up a bank account and then uses");
        System.out.println("multiple threads to multiply, divide, and add to the bank");
        System.out.println("account balance, and display the account information in");
        System.out.println("a synchronized and safe manner.\n");

        // Create a General Bank Balance
        BankAccount1 threadAccount = new BankAccount1("Severa",
                100);

        System.out.println("\n\nStartingAccount Information:");
        System.out.println("Account Owner:  " + threadAccount.getcAccountOwner());
        System.out.format("Bank Balance:  %1$,.2f\n", threadAccount.getcBankBalance());

        System.out.println("\nTransactions:");

        // As the previous executor was shut down, create a new executor to handle this set of code
        ExecutorService myExecutor3 = Executors.newFixedThreadPool(5);

        // Create separate threads of random actions
        for (int i = 0; i < 5; i++) {
            // Create a new thread
            TestThread3 newThread = new TestThread3(threadAccount);

            // Start the new thread
            myExecutor3.execute(newThread);
        }

        // Shut down the executor when all processes are done - loop until done
        myExecutor3.shutdown();
        while (!myExecutor3.isTerminated()) { }

        System.out.println("\n\nEnding Account Information:");
        System.out.println("Account Owner:  " + threadAccount.getcAccountOwner());
        System.out.format("Bank Balance:  %1$,.2f\n", threadAccount.getcBankBalance());

        System.out.println("\n\nIf you trace the data output, you will notice that all");
        System.out.println("actions to the balance happened in order as expected, even");
        System.out.println("though the lines printed out of order.");
        // Synchronization using Synchronize
        System.out.println("------------------------END EXAMPLE 3------------------------");

        System.out.println();

        System.out.println("-------------------------EXAMPLE 4---------------------------");
        // Locked Objects
        System.out.println("An alternative to using synchronization code is to use locks");
        System.out.println("when you need to prevent returning inaccurate data due to out");
        System.out.println("of order sequences.  There are many different kinds of locks,");
        System.out.println("with the most common being ReentrantLock, ReadWriteLock,");
        System.out.println("and StampLock.  This example will use ReentrantLock to rework");
        System.out.println("the Synchronization example.");

        // Create a General Bank Balance
        BankAccount2 threadAccount2 = new BankAccount2("Keola",
                50);

        System.out.println("\n\nStartingAccount Information:");
        System.out.println("Account Owner:  " + threadAccount2.getcAccountOwner());
        System.out.format("Bank Balance:  %1$,.2f\n", threadAccount2.getcBankBalance());

        System.out.println("\nTransactions:");

        // As the previous executor was shut down, create a new executor to handle this set of code
        ExecutorService myExecutor4 = Executors.newFixedThreadPool(5);

        // Create separate threads of random actions
        for (int i = 0; i < 5; i++) {
            // Create a new thread
            TestThread4 newThread2 = new TestThread4(threadAccount2);

            // Start the new thread
            myExecutor4.execute(newThread2);
        }

        // Shut down the executor when all processes are done - loop until done
        myExecutor4.shutdown();
        while (!myExecutor4.isTerminated()) { }

        System.out.println("\n\nEnding Account Information:");
        System.out.println("Account Owner:  " + threadAccount2.getcAccountOwner());
        System.out.format("Bank Balance:  %1$,.2f\n", threadAccount2.getcBankBalance());

        System.out.println("\n\nIf you trace the data output, you will notice that all");
        System.out.println("actions to the balance happened in order as expected, even");
        System.out.println("though the lines printed out of order.");

        System.out.println("------------------------END EXAMPLE 4------------------------");

        System.out.println("-------------------------EXAMPLE 5---------------------------");
        // Atomic Variables
        System.out.println("For certain data types such as integers, booleans, and a few");
        System.out.println("other types, you can use a special type of class called Atomic");
        System.out.println("which is innately locked completes a similar job to");
        System.out.println("synchronizing code with mutable variables or using locks.");
        System.out.println("I have not found out if there is a way to implement an ");
        System.out.println("'AtomicDouble', but this will re-utilize the prior program");
        System.out.println("by implementing AtomicInteger for the incrementing variable.");

        // Create a General Bank Balance
        BankAccount3 threadAccount3 = new BankAccount3("Seven",
                150);

        System.out.println("\n\nStartingAccount Information:");
        System.out.println("Account Owner:  " + threadAccount3.getcAccountOwner());
        System.out.format("Bank Balance:  %1$,.2f\n", threadAccount3.getcBankBalance());

        System.out.println("\nTransactions:");

        // As the previous executor was shut down, create a new executor to handle this set of code
        ExecutorService myExecutor5 = Executors.newFixedThreadPool(5);

        // Create separate threads of random actions
        for (int i = 0; i < 5; i++) {
            // Create a new thread
            TestThread5 newThread3 = new TestThread5(threadAccount3);

            // Start the new thread
            myExecutor5.execute(newThread3);
        }

        // Shut down the executor when all processes are done - loop until done
        myExecutor5.shutdown();
        while (!myExecutor5.isTerminated()) { }

        System.out.println("\n\nEnding Account Information:");
        System.out.println("Account Owner:  " + threadAccount3.getcAccountOwner());
        System.out.format("Bank Balance:  %1$,.2f\n", threadAccount3.getcBankBalance());

        System.out.println("\n\nIf you trace the data output, you will notice that all");
        System.out.println("actions to the balance happened in order as expected, even");
        System.out.println("though the lines printed out of order.");

        System.out.println("------------------------END EXAMPLE 5------------------------");

    }
}
