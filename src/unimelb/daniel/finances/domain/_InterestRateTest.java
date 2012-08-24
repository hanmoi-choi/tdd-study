package unimelb.daniel.finances.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class _InterestRateTest {

	@Test
	public void test() {
		GrowthRate interestRate = new GrowthRate(10);
        assertEquals("interestRate:", new Dollars(100), interestRate.growthFor(new Dollars(1000)));
	}

	@Test
	public void testName() throws Exception {

	}

	@Test
	public void valueObject() throws Exception {
		GrowthRate value1a = new GrowthRate(20);
		GrowthRate value1b = new GrowthRate(20);
		GrowthRate value2a = new GrowthRate(30);

		assertEquals("20.0%", value1a.toString());
        assertTrue(value1a.equals(value1b));
        assertFalse(value1a.equals(value2a));
        assertTrue(value1a.hashCode() == value1b.hashCode());
	}


}
