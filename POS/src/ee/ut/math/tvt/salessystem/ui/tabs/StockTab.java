package ee.ut.math.tvt.salessystem.ui.tabs;


import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.panels.PurchaseItemPanel;
import ee.ut.math.tvt.salessystem.util.HibernateUtil;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

import org.hibernate.Session;

public class StockTab {

	private JButton addItem;
	private JTextField id_field;
	private JTextField name_field;
	private JTextField price_field;
	private JTextField quantity_field;
	private JTextField description_field;
	
	private JLabel id_label;
	private JLabel name_label;
	private JLabel price_label;
	private JLabel quantity_label;
	private JLabel description_label;
	
	private Session session = HibernateUtil.currentSession();
		
	SalesSystemModel model;
	Long id;
	String name;
	double price;
	int quantity;
	StockItem newStockItem;
	
	public StockTab(SalesDomainController controller, SalesSystemModel model) {
		this.model = model;
	}

	// warehouse stock tab - consists of a menu and a table
	public Component draw() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		panel.setLayout(gb);

		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.NORTH;
		gc.gridwidth = GridBagConstraints.REMAINDER;
		gc.weightx = 1.0d;
		gc.weighty = 0d;
		panel.add(drawStockMenuPane(), gc);

		gc.weighty = 1.0;
		gc.fill = GridBagConstraints.BOTH;
		panel.add(drawStockMainPane(), gc);
		return panel;
	}

	// warehouse menu
	private Component drawStockMenuPane() {
		JPanel panel = new JPanel();

		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.gridheight = GridBagConstraints.RELATIVE;
		panel.setLayout(gb);
				
		id_label = new JLabel(" Id: ");
		gc.gridx = 0;
		panel.add(id_label, gc);
		
		name_label = new JLabel(" Name: ");
		gc.gridx = 2;
		panel.add(name_label, gc);
	
		price_label = new JLabel(" Price: ");
		gc.gridx = 4;
		panel.add(price_label, gc);
		
		quantity_label = new JLabel(" Quantity: ");
		gc.gridx = 6;
		panel.add(quantity_label, gc);
		
		description_label = new JLabel(" Description: ");
		gc.gridx = 8;
		panel.add(description_label, gc);
		
		gc.weightx = 0.1;
		
		id_field = new JTextField();
		gc.gridx = 1;
		gc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(id_field, gc);
		
		gc.weightx = 0.3;
		name_field = new JTextField();
		gc.gridx = 3;
		gc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(name_field, gc);
			
		gc.weightx = 0.1;
		price_field = new JTextField();
		gc.gridx = 5;
		gc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(price_field, gc);
		
		gc.weightx = 0.1;
		quantity_field = new JTextField();	
		gc.gridx = 7;
		gc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(quantity_field, gc);
		
		gc.weightx = 0.3;
		description_field = new JTextField();
		gc.gridx = 9;
		gc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(description_field, gc);
		
		gc.weightx = 0.1;
		addItem = new JButton("Add");
		gc.gridx = 10;
		panel.add(addItem, gc);
			
		addItem.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  addButtonClicked();
		      }
	    });

		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return panel;
	}

/*	protected void historyAddTest() {
		HistoryItem testHistoryItem = new HistoryItem(20);
		session.beginTransaction();
		session.saveOrUpdate(testHistoryItem);
		session.getTransaction().commit();
	}*/

	// table of the warehouse stock
	private Component drawStockMainPane() {
		JPanel panel = new JPanel();

		JTable table = new JTable(model.getWarehouseTableModel());

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

		panel.setBorder(BorderFactory.createTitledBorder("Warehouse status"));
		return panel;
	}
	
	private void addButtonClicked(){
		try {
			id = Long.parseLong(id_field.getText());
		} catch (NumberFormatException e){
			JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, "ID must be an integer!", "Incorrect input", 
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		ArrayList<Long> stockIDs = new ArrayList<Long>();
		for(StockItem item : model.getWarehouseTableModel().getTableRows()){
    		stockIDs.add(item.getId());
    	}

/*		for(Long stockItemID : stockIDs){
			if(stockItemID == id){
				JPanel panel = new JPanel();
				JOptionPane.showMessageDialog(panel, "ID number already in use!", "Incorrect input", 
						JOptionPane.WARNING_MESSAGE);
				return;
			}
		}*/

		name = name_field.getText();
		if(name.length() <= 0){
			JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, "Name field mustn't be empty!", "Incorrect input", 
					JOptionPane.WARNING_MESSAGE);
			return;
		}
	
		try {
			price = Double.parseDouble(price_field.getText());
			price = (double)Math.round(price * 100) / 100;
			System.out.println(price);
		} catch (NumberFormatException e2){
			JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, "Price must be a number (or maybe you used a "
					+ "comma instead of a dot?)!", 
					"Incorrect input", 
					JOptionPane.WARNING_MESSAGE);
			return;
		}
	
		try {
			quantity = Integer.parseInt(quantity_field.getText());
	
		} catch (NumberFormatException e3){
			JPanel panel = new JPanel();
			JOptionPane.showMessageDialog(panel, "Quantity must be an integer!", "Incorrect input", 
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		newStockItem = new StockItem(id, name, price, quantity);
		model.getWarehouseTableModel().addItem(newStockItem);
		//Lisa toode ka andmebaasi
		session.beginTransaction();
		session.saveOrUpdate(newStockItem);
		session.getTransaction().commit();
		PurchaseItemPanel.nameBox.addItem(newStockItem.getName());
		
		cleanUpAdd();
	}

	private void cleanUpAdd() {		
		id_field.setText(null);
		name_field.setText(null);
		price_field.setText(null);
		quantity_field.setText(null);
	}
	
}
