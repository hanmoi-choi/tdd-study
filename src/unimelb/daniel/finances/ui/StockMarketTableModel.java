package unimelb.daniel.finances.ui;

import javax.swing.table.AbstractTableModel;

import unimelb.daniel.finances.domain.StockMarket;
import unimelb.daniel.finances.domain.StockMarketYear;
import unimelb.daniel.finances.util.UnreacheableCodeException;

public class StockMarketTableModel extends AbstractTableModel{

	private static final long serialVersionUID = 1L;

	private static final String[] COLUMN_TITLES = {"Year", "Starting Balance", "Cost Basis", "Sells", "Growth",  "Ending Balance"};
    private StockMarket market;

	public StockMarketTableModel(StockMarket market) {
        this.market = market;
	}

	@Override
	public int getRowCount() {
        return market.numberOfYears();
	}

	@Override
	public int getColumnCount() {
        return COLUMN_TITLES.length;
	}

	@Override
	public String getColumnName(int index){
        return COLUMN_TITLES[index];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		StockMarketYear currentYear = market.getYearOffset(rowIndex);
		switch (columnIndex) {
		case 0: return currentYear.year();
		case 1: return currentYear.startingBalance();
		case 2: return currentYear.startingCostBasis();
		case 3: return currentYear.totalSell();
		case 4: return currentYear.growth();
		case 5: return currentYear.endingBalance();
		default: throw new UnreacheableCodeException();
		}
	}

}
