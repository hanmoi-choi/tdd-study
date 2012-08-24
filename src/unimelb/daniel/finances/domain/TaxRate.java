package unimelb.daniel.finances.domain;

import unimelb.daniel.finances.util.Require;

public class TaxRate {

	private double rateAsPercentage;

	public TaxRate(int rateAsPercentage) {
        Require.that(rateAsPercentage > 0, "Taxrate must be positive (and not Zero): but was " + rateAsPercentage);
        this.rateAsPercentage = rateAsPercentage;
	}

	public Dollars simpleTaxFor(Dollars amount) {
        return amount.percentage(rateAsPercentage);
	}

	public Dollars compundTaxFor(Dollars amount) {
		double percentage = (10000 / (100 - rateAsPercentage)) - 100;

        return amount.percentage(percentage);
	}
    
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(rateAsPercentage);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaxRate other = (TaxRate) obj;
		if (Double.doubleToLongBits(rateAsPercentage) != Double
				.doubleToLongBits(other.rateAsPercentage))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return rateAsPercentage + "%";
	}
}
