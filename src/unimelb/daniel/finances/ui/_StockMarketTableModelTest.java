package unimelb.daniel.finances.ui;

import org.junit.Before;
import org.junit.Test;

import unimelb.daniel.finances.domain.Dollars;
import unimelb.daniel.finances.domain.GrowthRate;
import unimelb.daniel.finances.domain.StockMarket;
import unimelb.daniel.finances.domain.TaxRate;
import unimelb.daniel.finances.domain.Year;

import static org.junit.Assert.*;

public class _StockMarketTableModelTest {

	private static final TaxRate CAPITAL_GAIN_TAX_RATE = new TaxRate(25);
	private static final GrowthRate INTEREST_RATE = new GrowthRate(10);
	private static final Dollars STARTING_PRINCIPAL = new Dollars(7000);
	private static final Dollars STARTING_BALANCE = new Dollars(10000);
	private static final Year STARTING_YEAR = new Year(2010);
	private static final Year ENDING_YEAR = new Year(2050);
    
	private StockMarketTableModel model;
    	
    @Before
    public void init(){
        StockMarket market = new StockMarket(STARTING_YEAR, ENDING_YEAR, STARTING_BALANCE, STARTING_PRINCIPAL, INTEREST_RATE, CAPITAL_GAIN_TAX_RATE, new Dollars(0));
		model = new StockMarketTableModel(market);
    }
    
	@Test
	public void columns() {
		assertEquals(6, model.getColumnCount());
        assertEquals("Year", model.getColumnName(0));
        assertEquals("Starting Balance", model.getColumnName(1));
	}
    
	@Test
	public void oneRow() throws Exception {
		assertEquals("Year", new Year(2010), model.getValueAt(0, 0));
		assertEquals("Starting Balance", STARTING_BALANCE, model.getValueAt(0, 1));
		assertEquals("Starting Principal", STARTING_PRINCIPAL, model.getValueAt(0, 2));
		assertEquals("Withdrawals", new Dollars(0), model.getValueAt(0, 3));
		assertEquals("Appreciation", new Dollars(1000), model.getValueAt(0, 4));
		assertEquals("Ending Balance", new Dollars(11000), model.getValueAt(0, 5));
	}
    
	@Test
	public void multipleRows() throws Exception {
		assertEquals("the number of rows", 41, model.getRowCount());
		assertEquals("firstYear", STARTING_YEAR, model.getValueAt(0, 0)); 
		assertEquals("starting balance in the first year", STARTING_BALANCE, model.getValueAt(0, 1));
		assertEquals("starting balance in the second year", new Dollars(11000), model.getValueAt(1, 1));
		assertEquals("lastYear", ENDING_YEAR, model.getValueAt(40, 0)); 
	}

}
