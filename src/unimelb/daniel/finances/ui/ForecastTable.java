package unimelb.daniel.finances.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class ForecastTable extends JTable{

	private static final long serialVersionUID = 1L;
	public static final Color STANDARD_BACKGROUND_COLOR = Color.WHITE;
	public static final Color ALTERNATING_BACKGROUND_COLOR = new Color(209, 229, 255);
	public static final Color SELECTION_BACKGROUND_COLOR = new Color(52, 117, 237);

	public ForecastTable(TableModel model) {
        super(model);
	}

	@Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
    	Component cell = super.prepareRenderer(renderer, row, column);

    	if(isCellSelected(row, column)) cell.setBackground(SELECTION_BACKGROUND_COLOR);
    	else if (standardRow(row)) cell.setBackground(STANDARD_BACKGROUND_COLOR);
    	else cell.setBackground(ALTERNATING_BACKGROUND_COLOR);

        return cell;
    }

	private boolean standardRow(int row) {
		return row % 2 == 0;
	}

}
