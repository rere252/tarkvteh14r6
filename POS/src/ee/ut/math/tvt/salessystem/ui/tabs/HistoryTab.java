package ee.ut.math.tvt.salessystem.ui.tabs;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.management.modelmbean.ModelMBean;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

/**
 * Encapsulates everything that has to do with the purchase tab (the tab
 * labeled "History" in the menu).
 */
public class HistoryTab {
    
    private static JPanel panel;
    private SalesSystemModel model;

    public HistoryTab(SalesSystemModel model) {
    	this.model = model;
    } 
    
    public Component draw() {
        panel = new JPanel();
        JTable table = new JTable(model.getHistoryTableModel());
        
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        
        JScrollPane scrollPane = new JScrollPane(table);
        
        GridBagConstraints gc = new GridBagConstraints();
        GridBagLayout gb = new GridBagLayout();
    	gc.fill = GridBagConstraints.BOTH;
    	gc.weightx = 1.0;
    	gc.weighty = 1.0;

    	panel.setLayout(gb);
    	panel.add(scrollPane, gc);

    	panel.setBorder(BorderFactory.createTitledBorder("Order history"));
        return panel;
    }
}