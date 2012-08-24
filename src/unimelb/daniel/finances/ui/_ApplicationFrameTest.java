package unimelb.daniel.finances.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;

import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.table.TableModel;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class _ApplicationFrameTest {

	private ApplicationFrame frame;

    @Before
    public void init() {
		frame = new ApplicationFrame();
	}

    @Test
	public void Application_ShouldExitWhenWindowIsClosed() throws Exception {
    	assertEquals(WindowConstants.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
	}

	@Test
	public void ShouldHaveTitle() {
        assertEquals("Title", ApplicationFrame.TITLE, frame.getTitle());
	}

	@Test
	public void shouldLayoutProperly() throws Exception {
		assertEquals("Layout", BorderLayout.class, frame.getLayout().getClass());
	}

	@Test
	public void ShouldHaveSizeAndPosition() throws Exception {

        assertEquals("Size", ApplicationFrame.INIT_SIZE, frame.getSize());
        assertEquals("Position", ApplicationFrame.INIT_LOCATION, frame.getLocation());
	}

	@Test
	public void shouldContainProperContainer() throws Exception {
        Component[] component = frame.getContentPane().getComponents();

        assertEquals(2, component.length);
        assertEquals("Component #0 should be JScrollPane", JScrollPane.class, component[0].getClass());
        assertEquals("JScrollPane #0 should be ForecastTable", ForecastTable.class,((JScrollPane)component[0]).getViewport().getView().getClass());
	}

    @Test
    public void Container_ShouldContainProperModel(){
    	Component[] component = frame.getContentPane().getComponents();
    	TableModel model = ((ForecastTable)((JScrollPane)component[0]).getViewport().getView()).getModel();
		assertEquals("Model of ForecastTable", StockMarketTableModel.class, model.getClass());
        assertEquals("# of Row in Model", 41, model.getRowCount());
	}



}
