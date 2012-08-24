package unimelb.daniel.finances.domain;

public class Dollars {

	private double amount;

	public Dollars(int amount) {
        this.amount = amount;
	}

	public Dollars(double amount) {
        this.amount = amount;
	}

	public Dollars plus(Dollars dollars) {
        return new Dollars(this.amount + dollars.amount);
	}

	public Dollars minus(Dollars dollar) {
        return new Dollars(this.amount - dollar.amount);
	}

	public Dollars minusToZero(Dollars dollars) {
		int result = (int)(this.amount - dollars.amount);

        return new Dollars(Math.max(0, result));
	}

	public Dollars percentage(double rate) {
        return new Dollars(amount * rate / 100);
	}

	private long roundOffPennies() {
		return Math.round(amount);
	}
	public static Dollars min(Dollars first, Dollars second) {
		return new Dollars(Math.min(first.amount, second.amount));
	}

	@Override
	public int hashCode() {
		return (int) roundOffPennies();
	}

	@Override
	public boolean equals(Object obj) {
		Dollars that = (Dollars)obj;

		return roundOffPennies() == that.roundOffPennies();
	}

	@Override
	public String toString(){
		return "$" + roundOffPennies();
	}






}
