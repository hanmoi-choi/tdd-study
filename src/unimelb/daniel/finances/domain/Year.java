package unimelb.daniel.finances.domain;

public class Year {

	private int year;

	public Year(int year) {
        this.year = year;
	}

	public int toInt() {
        return this.year;
	}

	public Year nextYear() {
        return new Year(year + 1);
	}

	public int numberOfYearsInclusive(Year endingYear) {
        return endingYear.year - year + 1;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + year;
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
		Year other = (Year) obj;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "" + year;
	}

	
}
