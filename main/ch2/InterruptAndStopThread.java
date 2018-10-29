package ch2;

/**
 * Created by 13 on 2017/5/4.
 */
public class InterruptAndStopThread {
    public static void main(String args[]) throws InterruptedException {

        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    // 自己检测中断信号
                    if (Thread.currentThread().isInterrupted()) {
                        // 这里可以做一些线程退出前的处理，以保证数据一致性
                        System.out.println("Safely Interrupted");
                        break;// 中断
                    }
                    try {
                        Thread.sleep(600);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        // sleep()时收到中断信号，会报异常，并再次设置自己的中断位，以保证数据一致性
                        Thread.currentThread().interrupt();
                    }
                    System.out.println("Running!");
                    Thread.yield();
                }
            }
        };

        thread.start();
        Thread.sleep(2000);
        thread.interrupt();// 发送中断信号
    }
}
