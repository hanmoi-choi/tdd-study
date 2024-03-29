package unimelb.daniel.finances.domain;

import org.junit.Ignore;
import org.junit.Test;


import static org.junit.Assert.*;

public class _StockMarketTest {

	private static final Year ENDING_YEAR = new Year(2050);
	private static final Year STARTING_YEAR = new Year(2010);
	private static final Dollars STARTING_BALANCE = new Dollars(10000);
	private static final TaxRate CAPITAL_GAIN_TAX_RATE = new TaxRate(25);
	private static final GrowthRate INTEREST_RATE = new GrowthRate(10);
	private static final Dollars STARTING_PRINCIPAL= new Dollars(3000);

	@Test
	public void yearRange() {
        StockMarket account = new StockMarket(STARTING_YEAR, ENDING_YEAR, STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAIN_TAX_RATE, new Dollars(0));
        assertEquals(41, account.numberOfYears());
        assertEquals(STARTING_BALANCE, account.getYearOffset(0).startingBalance());
        assertEquals(new Dollars(11000), account.getYearOffset(1).startingBalance());
        assertEquals(new Dollars(12100), account.getYearOffset(2).startingBalance());
        assertEquals(new Year(2050), account.getYearOffset(40).year());
	}

    @Test
	public void stockMarketWithdrawsAStandardAmountEveryYear() throws Exception {
        StockMarket account = new StockMarket(STARTING_YEAR, ENDING_YEAR, STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAIN_TAX_RATE, new Dollars(10));
        assertEquals("Year 0", new Dollars(10), account.getYearOffset(0).totalSellOrders());
        assertEquals("Year 1", new Dollars(10), account.getYearOffset(1).totalSellOrders());
        assertEquals("Year 40", new Dollars(10), account.getYearOffset(40).totalSellOrders());
	}
	@Test
	public void noAccumulatedInterestErrorDueToRounding() throws Exception {
        StockMarket account = new StockMarket(STARTING_YEAR, ENDING_YEAR, STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAIN_TAX_RATE, new Dollars(0));

        assertEquals(new Dollars(497852), account.getYearOffset(40).endingBalance());
	}

}
