package task;

import java.math.BigInteger;

public class FactorialTask implements Runnable {

	private int number;
	private BigInteger result;

	public FactorialTask(int number) {
		super();
		this.number = number;
		result = BigInteger.ZERO;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public BigInteger getResult() {
		return result;
	}

	public void setResult(BigInteger result) {
		this.result = result;
	}

	public BigInteger factorial(int n) {
		BigInteger fact = BigInteger.ONE;
		for (int i = 1; i <= n; i++) {
			fact = fact.multiply(BigInteger.valueOf(i));
		}
		return fact;
	}

	@Override
	public void run() {
		Thread thr = Thread.currentThread();
		BigInteger fact = factorial(number);
		result = result.add(fact);
		System.out.println(thr.getName() + ": " + "!" + number + "= " + result);
	}
}
