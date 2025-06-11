package Exercise11;

public class Main {
    public static void main(String[] args) {
        BankAccountWithLock account = new BankAccountWithLock(1000.00);

        Runnable reader = () -> {
            for (int i = 0; i < 3; i++) {
                account.getBalance();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable depositor = () -> {
            for (int i = 0; i < 2; i++) {
                account.deposit(200);
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Runnable withdrawer = () -> {
            for (int i = 0; i < 2; i++) {
                account.withdraw(150);
                try {
                    Thread.sleep(120);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        Thread t1 = new Thread(reader, "Reader-1");
        Thread t2 = new Thread(depositor, "Depositor-1");
        Thread t3 = new Thread(withdrawer, "Withdrawer-1");

        t1.start();
        t2.start();
        t3.start();
    }
}
