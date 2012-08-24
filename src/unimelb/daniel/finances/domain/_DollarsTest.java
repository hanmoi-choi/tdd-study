package unimelb.daniel.finances.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class _DollarsTest {

	@Test
	public void additions() {
        assertEquals(new Dollars(20), new Dollars(10).plus(new Dollars(10)));
	}

	@Test
	public void subtraction() {
        assertEquals("positive", new Dollars(0), new Dollars(10).minus(new Dollars(10)));
        assertEquals("negative", new Dollars(-10), new Dollars(10).minus(new Dollars(20)));
	}

	@Test
	public void percentage() throws Exception {
		assertEquals(new Dollars(20), new Dollars(100).percentage(20));
	}

	@Test
	public void equalsIngnorePenny() throws Exception {
		assertTrue("should round down", new Dollars(10).equals(new Dollars(10.10)));
		assertTrue("should round up", new Dollars(10).equals(new Dollars(9.90)));
		assertTrue("should round up on .50", new Dollars(10).equals(new Dollars(9.50)));
	}

	@Test
	public void hashCodeIngnorePennyToo() throws Exception {
		assertTrue(new Dollars(10).hashCode() == new Dollars(10.10).hashCode());
		assertTrue(new Dollars(10).hashCode() == new Dollars(9.90).hashCode());
		assertTrue(new Dollars(10).hashCode() == new Dollars(9.50).hashCode());
	}

	@Test
	public void toStringIngnorePenny() throws Exception {
		assertEquals("should round down", "$10", new Dollars(10.10).toString());
		assertEquals("should round down", "$10", new Dollars(9.90).toString());
		assertEquals("should round down", "$10", new Dollars(9.50).toString());
	}
	@Test

	public void min() throws Exception {
		Dollars dollar1 = new Dollars(30);
		Dollars dollar2 = new Dollars(20);

		assertEquals(new Dollars(20), Dollars.min(dollar1, dollar2));
		assertEquals(new Dollars(20), Dollars.min(dollar2, dollar1));

	}
    @Test
	public void subtractionToZero() throws Exception {
        assertEquals("positive", new Dollars(10), new Dollars(20).minusToZero(new Dollars(10)));
        assertEquals("negative", new Dollars(0), new Dollars(10).minusToZero(new Dollars(20)));
	}
	@Test
	public void valueObject() throws Exception {
		Dollars value1a = new Dollars(20);
		Dollars value1b = new Dollars(20);
		Dollars value2a = new Dollars(30);

		assertEquals("$20", value1a.toString());
        assertTrue(value1a.equals(value1b));
        assertFalse(value1a.equals(value2a));
        assertTrue(value1a.hashCode() == value1b.hashCode());
	}

}
