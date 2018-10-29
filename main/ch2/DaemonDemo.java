package ch2;

/**
 *
 * @author 13
 * @date 2017/5/4
 */
public class DaemonDemo {
    public static class DaemonT extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("I am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     *
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String args[]) throws InterruptedException {
        Thread thread = new DaemonT();
        // 设置thread为守护线程
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(3000);
    }
}
