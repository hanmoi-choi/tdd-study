package unimelb.daniel.finances.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class _ForecastTableTest {
	@Test
	public void tableRowsShouldStandardColor_WhenJustOneRow() {
		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.addRow(new String[] { "" });

		JTable table =new ForecastTable(tableModel);

        assertEquals("1st cell should standard color", ForecastTable.STANDARD_BACKGROUND_COLOR, getCellBackGround(table, 0, 0));
	}

	@Test
	public void tableRowsShouldAlternateColors_WhenMultipleRowsAndNoColumnHeads() {
		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.addRow(new String[] { "" });
		tableModel.addRow(new String[] { "" });
		tableModel.addRow(new String[] { "" });
		tableModel.addRow(new String[] { "" });

		JTable table =new ForecastTable(tableModel);

        assertEquals("1st cell should standard color", ForecastTable.STANDARD_BACKGROUND_COLOR, getCellBackGround(table, 0, 0));
        assertEquals("2nd cell should standard color", ForecastTable.ALTERNATING_BACKGROUND_COLOR, getCellBackGround(table, 1, 0));
        assertEquals("3rd cell should standard color", ForecastTable.STANDARD_BACKGROUND_COLOR, getCellBackGround(table, 2, 0));
        assertEquals("4th cell should standard color", ForecastTable.ALTERNATING_BACKGROUND_COLOR, getCellBackGround(table, 3, 0));

	}

	@Test
	public void tableRowsShouldAlternateColors_WhenHaveColumnHeads() {
		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.addRow(new String[] { "" });
		tableModel.addRow(new String[] { "" });
		tableModel.addRow(new String[] { "" });
		tableModel.addRow(new String[] { "" });

		JTable table =new ForecastTable(tableModel);

        assertEquals("1st cell should standard color", ForecastTable.STANDARD_BACKGROUND_COLOR, getCellBackGround(table, 0, 0));
        assertEquals("2nd cell should standard color", ForecastTable.ALTERNATING_BACKGROUND_COLOR, getCellBackGround(table, 1, 0));
        assertEquals("3rd cell should standard color", ForecastTable.STANDARD_BACKGROUND_COLOR, getCellBackGround(table, 2, 0));
        assertEquals("4th cell should standard color", ForecastTable.ALTERNATING_BACKGROUND_COLOR, getCellBackGround(table, 3, 0));

	}

	@Test
	public void tableRowsShouldUseSelectionColor_WhenSelected() {
		DefaultTableModel tableModel = new DefaultTableModel(0, 1);
		tableModel.addRow(new String[] { "" });

		JTable table =new ForecastTable(tableModel);
        table.setRowSelectionInterval(0, 0);
        table.setColumnSelectionInterval(0, 0);

        assertEquals("selection background color", ForecastTable.SELECTION_BACKGROUND_COLOR, getCellBackGround(table, 0, 0));
	}

	private Color getCellBackGround(JTable table, int row, int column) {
		TableCellRenderer renderer = table.getCellRenderer(row, column);
		Component component = table.prepareRenderer(renderer, row, column);
        Color actualColor = component.getBackground();
		return actualColor;
	}
}
