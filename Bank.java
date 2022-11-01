package src.model;

public class Bank {
	
	private double balance;
	
	public Bank() {
		balance = 2000;
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void withdraw(double amount) {
		balance -= amount;
	}
	
	public void desposit(double amount) {
		balance += amount;
	}
	
}
