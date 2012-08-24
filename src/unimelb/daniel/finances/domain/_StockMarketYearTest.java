package unimelb.daniel.finances.domain;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class _StockMarketYearTest {

	private static final TaxRate CAPITAL_GAIN_TAX_RATE = new TaxRate(25);
	private static final GrowthRate INTEREST_RATE = new GrowthRate(10);
	private static final Dollars STARTING_PRINCIPAL= new Dollars(3000);
	private static final Dollars STARTING_BALANCE= new Dollars(10000);
	private static final Year YEAR = new Year(2010);


	@Test
	public void startingValues() throws Exception {
		 StockMarketYear year = newYear();
         assertEquals("Which Year", YEAR, year.year());
         assertEquals("starting balance", STARTING_BALANCE, year.startingBalance());
         assertEquals("starting principal", STARTING_PRINCIPAL, year.startingCostBasis());
         assertEquals("appreciation rate", INTEREST_RATE, year.growthRate());
         assertEquals("capital gains tax rate", CAPITAL_GAIN_TAX_RATE, year.capitalGainsTaxRate());
         assertEquals("total withdrawn default", new Dollars(0), year.totalSell());
	}


	private StockMarketYear newYear() {
		return new StockMarketYear(YEAR, STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAIN_TAX_RATE);
	}

    @Test
	public void totalSold() throws Exception {
		StockMarketYear year = newYear();

		assertEquals("no Sale", new Dollars(0), year.totalSellOrders());

		year.sell(new Dollars(3000));
		assertEquals("one sale", new Dollars(3000), year.totalSellOrders());

		year.sell(new Dollars(750));
		year.sell(new Dollars(1350));
		assertEquals("multiple sales", new Dollars(5100), year.totalSellOrders());
	}


	@Test
	public void interestEarned() throws Exception {
		StockMarketYear year = newYear();
        assertEquals("basic interest earned", new Dollars(1000), year.growth());

        year.sell(new Dollars(2000));
        assertEquals("withdrawals don't earn interest", new Dollars(733), year.growth());

        year.sell(new Dollars(2000));
        assertEquals("captial gain tax withdrawls don't earn interest", new Dollars(467), year.growth());
	}

	@Test
	public void withdrawlsReduceBalanceAfterAllCapitalGainsareWithdrawn() throws Exception {

		StockMarketYear year = newYear();

        year.sell(new Dollars(500));
        assertEquals("withdrawls less than capital gains do not reduce balance", STARTING_PRINCIPAL, year.endingCostBasis());

        year.sell(new Dollars(6500));
        Dollars totalWithdrawn = new Dollars(9333);
        Dollars capitalGains = new Dollars(7000);
        Dollars principalReducedBy = totalWithdrawn.minus(capitalGains);
        Dollars expectedPrnicipal = STARTING_PRINCIPAL.minus(principalReducedBy);
        assertEquals("principal should be reduced by difference between capital gains and total withdrawls", expectedPrnicipal, year.endingCostBasis());

        year.sell(new Dollars(1000));
        assertEquals("Principal goes minus when we're overdrawn", new Dollars(-333), year.endingCostBasis());
	}

	@Test
	public void capitalGainsTax() throws Exception {
		StockMarketYear year = newYear();

        year.sell(new Dollars(4000));

        assertEquals("capital gain taxes", new Dollars(1333), year.capitalGainTaxIncurred());
        assertEquals("total withdrawn", new Dollars(5333), year.totalSell());

	}

	@Test
	public void treatAllwithdrwalsAsSubjectToCaptialGainsUntilAllCapitalGainHaveBeenSold() throws Exception {
		StockMarketYear year = newYear();

		Dollars capitalGains = STARTING_BALANCE.minus(STARTING_PRINCIPAL);

        year.sell(new Dollars(500));
        assertEquals("pay tax on all withdrawn until all capital gain withdrawn", new Dollars(167), year.capitalGainTaxIncurred());

        year.sell(capitalGains);
        assertEquals("pay tax on all withdrawn until all capital gain withdrawn", new Dollars(2333), year.capitalGainTaxIncurred());

        year.sell(new Dollars(1000));
        assertEquals("pay no tax on withdrawn over capital gain", new Dollars(2333), year.capitalGainTaxIncurred());
	}

	@Test
	public void endingBalance() throws Exception {
		StockMarketYear year = newYear();
        assertEquals("ending balance includes interests", new Dollars(11000), year.endingBalance());

        year.sell(new Dollars(1000));
        assertEquals("ending balance includes withdrawls", new Dollars(9533), year.endingBalance());

        year.sell(new Dollars(3000));
        assertEquals("ending balance includes capital gains tax", new Dollars(5133), year.endingBalance());
	}

	@Test
	public void nextYearMatchesThisYearsEndingValues() throws Exception {
		StockMarketYear thisYear = newYear();
		StockMarketYear nextYear = thisYear.nextYear();

        assertEquals("Next Year", new Year(2011), nextYear.year());
		assertEquals("starting balance", thisYear.endingBalance(), nextYear.startingBalance());
		assertEquals("starting principal", thisYear.endingCostBasis(), thisYear.startingCostBasis());
		assertEquals("interest rate", thisYear.growthRate(), nextYear.growthRate());
		assertEquals("capital gain tax rate", thisYear.capitalGainsTaxRate(), nextYear.capitalGainsTaxRate());

	}


}
