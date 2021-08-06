package b35.banking.dto;

public class Amount {
	double amount;

	@Override
	public String toString() {
		return "Amount [amount=" + amount + "]";
	}

	public Amount(double amount) {
		super();
		this.amount = amount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Amount() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
