package unimelb.daniel.spikes.looktest;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class CustomTable extends JTable {
	private static final long serialVersionUID = 1L;

	public CustomTable(DefaultTableModel tableModel) {
		super(tableModel);
	}

	public Component prepareRenderer(TableCellRenderer renderer, int row,
			int column) {
		Component component = super.prepareRenderer(renderer, row, column);
		if (isCellSelected(row, column))
			return component;

		Color background = row % 2 == 0 ? new Color(223, 230, 236)
				: Color.white;
		component.setBackground(background);
		return component;
	}

}
