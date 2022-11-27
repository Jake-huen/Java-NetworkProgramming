package Threads;

class Producer extends Thread {
	private Buffer blank;

	public Producer(Buffer b) {
		this.blank = b;
	}

	public void run() {
		for (int i=0; i<10; i++) {
			try {
				synchronized (blank){
					blank.put(i);
					System.out.println("Producer: Produced" + i);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.err.println(e);
			}
			try {
				sleep((int)(Math.random()*100));
			} catch (InterruptedException e) {
				System.err.println(e);
			}
		}
	}
}

class Consumer extends Thread {
	private Buffer blank;

	public Consumer(Buffer b) {
		this.blank = b;
	}


	public void run() {
		int value = 0;
		while(value!=9) {
			try {
				value = blank.get();
				System.out.println("Consumer: Consumed" + value);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Buffer {
	private int contents;
	private boolean available = false;

	public synchronized int get() throws InterruptedException {
		if(!this.available){
			wait();
			// System.out.println("waiting consumer");
		} else{
			// System.out.println("consumer 실행");
			System.out.println(contents);
			notify();
			available = false;
		}
		return contents;
	}

	public synchronized void put(int value) throws InterruptedException {
		if(this.available){
			wait();
			// System.out.println("waiting producer");
		}else {
			// System.out.println("producer 실행");
			contents = value;
			notify();
			available = true;
		}
	}
}


public class Homework01ProducerConsumerProblem {

	public static void main(String[] args) {
		Buffer b = new Buffer();
		Producer p1 = new Producer(b);
		Consumer c1 = new Consumer(b);

		p1.start();
		c1.start();

	}
}
