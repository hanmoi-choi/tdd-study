package unimelb.daniel.finances.domain;

public class StockMarketYear {

    private Dollars startingBalance;
	private GrowthRate growthRate;
	private Dollars totalSellOrders;
	private Dollars costBasis;
	private TaxRate capitalGainsTaxRate;
	private Year year;

	public StockMarketYear(Year year, Dollars startingBalance, Dollars costBasis, GrowthRate growthRate, TaxRate capitalGainTaxRate) {
        this.year = year;
        this.startingBalance = startingBalance;
        this.costBasis= costBasis;
        this.growthRate = growthRate;
        this.capitalGainsTaxRate = capitalGainTaxRate;
        this.totalSellOrders = new Dollars(0);
	}

	public Dollars startingBalance() {
        return startingBalance;
	}
	public Dollars startingCostBasis() {
        return costBasis;
	}

	public GrowthRate growthRate() {
        return growthRate;
	}

	public Dollars totalSell() {
        return totalSellOrders.plus(capitalGainTaxIncurred());
	}

	public Dollars totalSellOrders() {
        return totalSellOrders;
	}

	public Dollars endingCostBasis() {
		Dollars purchasesSold = totalSell().minusToZero(startingCapitalGains());
        return startingCostBasis().minus(purchasesSold);
	}

	private Dollars startingCapitalGains() {
		return startingBalance.minus(costBasis);
	}

	public Dollars endingBalance() {
        return startingBalance.minus(totalSell()).plus(growth());
	}

    public void sell(Dollars amount) {
        totalSellOrders = totalSellOrders.plus(amount);
	}

	public StockMarketYear nextYear() {
        return new StockMarketYear(year.nextYear(), endingBalance(), endingCostBasis(), growthRate, capitalGainsTaxRate);
	}


	public Dollars captialGainSold() {
        return Dollars.min(startingCapitalGains(), totalSellOrders());
	}

	public Dollars capitalGainTaxIncurred() {
		return capitalGainsTaxRate.compundTaxFor(captialGainSold());
	}

	public Dollars growth() {
		return growthRate.growthFor(startingBalance.minus(totalSell()));
	}

	public TaxRate capitalGainsTaxRate() {
        return capitalGainsTaxRate;
	}

	public Year year() {
		return year;
	}



}
