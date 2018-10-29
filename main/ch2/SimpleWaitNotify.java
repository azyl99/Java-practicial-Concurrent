package ch2;



public class SimpleWaitNotify {
	final static Object object = new Object();
	public static class T1 extends Thread{
        @Override
        public void run()
        {
            synchronized (object) {
                System.out.println(System.currentTimeMillis()+":T1 start! ");
                try {
                	System.out.println(System.currentTimeMillis()+":T1 wait for object ");
                	object.wait();
                    System.out.println("终于等到了t2的锁");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis()+":T1 end!");
            }
        }
	}
	public static class T2 extends Thread{
        @Override
        public void run()
        {
            synchronized (object) {
                System.out.println(System.currentTimeMillis()+":T2 start! notify one thread");
                object.notify();
                System.out.println(System.currentTimeMillis()+":T2 end!");
                try {
                    // Thread.sleep()不会释放对象的锁，而obj.wait()会释放
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
            }
        }
	}
	public static void main(String[] args) throws InterruptedException {
        Thread t1 = new T1() ;
        Thread t2 = new T2() ;
//        Thread t1_1 = new T1() ;
//        t1_1.start();
        t1.start();
        Thread.sleep(100);// 确保t1在t2之前被执行
        t2.start();
	}
} 
