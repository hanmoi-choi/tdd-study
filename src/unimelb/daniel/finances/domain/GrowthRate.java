package unimelb.daniel.finances.domain;

public class GrowthRate {

	private double rateAsPercentage;

	public GrowthRate(int rateAsPercentage) {
        this.rateAsPercentage = rateAsPercentage;
	}

	public Dollars growthFor(Dollars dollars) {
        return dollars.percentage(rateAsPercentage);
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
		GrowthRate other = (GrowthRate) obj;
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
