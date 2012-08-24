package unimelb.daniel.finances.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class _TaxRateTest {

	@Test
	public void simpleTaxJustAppliesTaxRateToAmount() throws Exception {
        TaxRate taxRate = new TaxRate(25);
        assertEquals("Tax with taxrate", new Dollars(250) , taxRate.simpleTaxFor(new Dollars(1000)));
	}

	@Test
	public void compoundTaxIsTheAmountOfTaxIfYouPayTaxForTax() throws Exception {
        TaxRate taxRate = new TaxRate(25);
        assertEquals("Tax with taxrate", new Dollars(333), taxRate.compundTaxFor(new Dollars(1000)));
	}
    
	@Test
	public void valueObject() throws Exception {
		TaxRate value1a = new TaxRate(20);
		TaxRate value1b = new TaxRate(20);
		TaxRate value2a = new TaxRate(30);
		
		assertEquals("20.0%", value1a.toString());
        assertTrue(value1a.equals(value1b));
        assertFalse(value1a.equals(value2a));
        assertTrue(value1a.hashCode() == value1b.hashCode());
	}
}
