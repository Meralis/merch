package task;

public class Main {

	public static void main(String[] args) {
		Thread[] threads = new Thread[100];
		for (int i = 99; i >= 0; i--) {
			FactorialTask task = new FactorialTask(i);
			task.setNumber(i + 1);
			threads[i] = new Thread(task);
			threads[i].setName("Thread-" + (i + 1));
			try {
				threads[i].start();
			} catch (IllegalThreadStateException e) {
				e.printStackTrace();
			}
		}
		for (int i = 99; i >= 0; i--) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
