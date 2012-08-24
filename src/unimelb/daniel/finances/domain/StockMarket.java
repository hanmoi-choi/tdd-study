package unimelb.daniel.finances.domain;

public class StockMarket {

	private Year endingYear;
	private Year startingYear;
	private StockMarketYear[] years;
	private Dollars sellEveryYear;

	public StockMarket(Year startingYear, Year endingYear, Dollars startingBalance, Dollars startingPrincipal, GrowthRate interestRate, TaxRate capitalGainTaxRate, Dollars sellEveryYear) {
        this.startingYear = startingYear;
        this.endingYear = endingYear;
        this.sellEveryYear = sellEveryYear;
        populateYears(startingBalance, startingPrincipal, interestRate, capitalGainTaxRate);
	}

	private void populateYears(Dollars startingBalance, Dollars startingPrincipal, GrowthRate interestRate, TaxRate capitalGainTaxRate) {
		this.years = new StockMarketYear[numberOfYears()];

        years[0] =  new StockMarketYear(startingYear, startingBalance, startingPrincipal, interestRate, capitalGainTaxRate);
        years[0].sell(sellEveryYear);
		for(int i = 1 ; i < numberOfYears() ; i++){
			years[i] = years[i-1].nextYear();
            years[i].sell(sellEveryYear);
		}
	}

	public StockMarketYear getYearOffset(int offset) {
        return years[offset];
	}

	public int numberOfYears() {
        return startingYear.numberOfYearsInclusive(endingYear);
	}

}
