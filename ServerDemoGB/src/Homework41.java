public class Homework41 {
    static volatile char l = 'A';
    static Object monitor = new Object();

    static class WaitNotifyClass implements Runnable {
        private char curLetter;
        private char nextLetter;

        public WaitNotifyClass(char currentLetter, char nextLetter) {
            this.curLetter = currentLetter;
            this.nextLetter = nextLetter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                synchronized (monitor) {
                    try {
                        while (l != curLetter)
                            monitor.wait();
                        System.out.print(curLetter);
                        l = nextLetter;
                        monitor.notifyAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
