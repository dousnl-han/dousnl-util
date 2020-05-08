import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 *
 * @author hanliang
 * @version 1.0
 * @date 2020/4/3 13:35
 */
public class ReentrantLockTest  implements Runnable{
    //创建公平锁
    private static ReentrantLock lock=new ReentrantLock(true);
    public void run() {
        while(true){
            lock.lock();
            try{
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally{
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        ReentrantLockTest lft=new ReentrantLockTest();
        Thread th1=new Thread(lft);
        Thread th2=new Thread(lft);
        th1.start();
        Thread.sleep(500);
        th1.interrupt();
        th2.start();
    }
}
