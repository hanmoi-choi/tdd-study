package unimelb.daniel.finances.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class _YearTest {

	@Test
	public void yearTest() {
        Year year1a = new Year(2010);
        Year year1b = new Year(2010);
        Year year2a = new Year(2050);
        
        assertEquals(2010, year1a.toInt());
        assertTrue(year1a.equals(year1b));
        assertFalse(year1a.equals(year2a));
        assertEquals("2010", year1a.toString());
	}
	
	@Test
	public void endingYearInclusive() throws Exception {
		Year thisYear = new Year(2010);
		assertEquals(41, thisYear.numberOfYearsInclusive(new Year(2050)));
	}
    
	@Test
	public void nextYear() throws Exception {
		Year thisYear = new Year(2010);
		
		assertEquals(new Year(2011), thisYear.nextYear());
	}

}
