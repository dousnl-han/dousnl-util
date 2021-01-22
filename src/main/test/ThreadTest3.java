public class ThreadTest3 extends Thread{
	volatile int a=0;
	 
	public ThreadTest3(int i) {
		this.a=i;
	}

	public synchronized void option(int b){
		a-=b;
		System.out.println("u.int.a===>"+a+"线程id:"+Thread.currentThread().getId());
	}
	
	@Override
	public void run() {
		System.out.println("Thread name:"+Thread.currentThread().getName()+" run:"+a);
	}
//	public static void main(String[] args) throws InterruptedException {
//		ThreadTest3 u=new ThreadTest3(1);
//		MyThread1 my1=new MyThread1(u);
//		MyThread1 my2=new MyThread1(u);
//		MyThread1 my3=new MyThread1(u);
//		MyThread1 my4=new MyThread1(u);
//		my1.start();//子线程1
//		my2.start();//子线程2
//		my3.start();//子线程3
//		my4.start();//子线程4
//		//Thread t1=new Thread(my);
//		//Thread t2=new Thread(my);
//		//Thread t3=new Thread(my);
//			for(int i=0;i<3;i++){
//				//synchronized (u) {
//					Thread t1=new MyThread1(u);
//					t1.start();
//			//}
//		}
//		//t1.start();
//		//t2.start();
//		//t3.start();
//		//A a=new A();
//		System.out.println("主线程执行........");//主线程，可看到主线程执行，无需等待子线程，主线程执行最快
//	}

}



