package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labelled "History" in the menu).
 */
public class HistoryTab {
    
    private static JPanel panel;
    private JTable table;
    private SalesSystemModel model;

    public HistoryTab(SalesSystemModel model) {
    	this.model=model;
    } 
    
    public static Component draw(HistoryItem history) {
        panel = new JPanel();
        String[] columnNames = {"Time","Date","Total sum"};
        Object [][] data={{"13:00:22","23.12.2014","23"},
        		{"15:00:22","13.02.2014","3"},
        		{"23:40:52","23.12.2014","8"}};
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);
        return panel;
    }
}