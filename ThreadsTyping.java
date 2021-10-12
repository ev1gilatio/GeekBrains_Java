

public class ThreadsTyping {
    private char current = 'A';

    public ThreadsTyping() {
        for (int i = 0; i < 5; i++) {
            createThreads();
        }
    }

    private void createThreads() {
        new Thread(() -> typing('A', 'B')).start();

        new Thread(() -> typing('B', 'C')).start();

        new Thread(() -> {
            typing('C', 'A');
            System.out.print(" ");
        }).start();
    }

    private void typing(char inbound, char next) {
        synchronized (this) {
            while (current != inbound) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.print(inbound);
            current = next;
            notifyAll();
        }
    }
}