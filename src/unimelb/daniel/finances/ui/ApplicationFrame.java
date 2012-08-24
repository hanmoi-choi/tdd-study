package unimelb.daniel.finances.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import unimelb.daniel.finances.domain.Dollars;
import unimelb.daniel.finances.domain.GrowthRate;
import unimelb.daniel.finances.domain.StockMarket;
import unimelb.daniel.finances.domain.TaxRate;
import unimelb.daniel.finances.domain.Year;

public class ApplicationFrame extends JFrame {

	public static final Point INIT_LOCATION = new Point(200, 300);
	public static final Dimension INIT_SIZE = new Dimension(900, 400);

	private static final long serialVersionUID = 1L;
	public static final String TITLE = "Forecast";

	public ApplicationFrame() {

        super(TITLE);

        this.setSize(INIT_SIZE);
        this.setLocation(INIT_LOCATION);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addComponents();
	}

	private void addComponents() {
		Container contentPane = this.getContentPane();
		contentPane.add(BorderLayout.CENTER, forecastTable());
		contentPane.add(BorderLayout.NORTH, new JTextField());
	}

	private JScrollPane forecastTable() {
        return new JScrollPane(new ForecastTable(new StockMarketTableModel(stockMarket())));
	}

	private StockMarket stockMarket() {
		Year startingYear = new Year(2010);
		Year endingYear = new Year(2050);
		Dollars startingBalance = new Dollars(10000);
		Dollars startingPrincipal = new Dollars(7000);
		GrowthRate interestRate = new GrowthRate(10);
		TaxRate capitalGainTaxRate = new TaxRate(25);

		return new StockMarket(startingYear, endingYear, startingBalance, startingPrincipal, interestRate, capitalGainTaxRate, new Dollars(695));
	}

}
