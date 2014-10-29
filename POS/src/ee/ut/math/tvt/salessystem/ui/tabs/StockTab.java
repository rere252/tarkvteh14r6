package ee.ut.math.tvt.salessystem.ui.tabs;


import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

public class StockTab {

	private JButton addItem;
	private JTextField id_field;
	private JTextField name_field;
	private JTextField price_field;
	private JTextField quantity_field;
	
	private JLabel id_label;
	private JLabel name_label;
	private JLabel price_label;
	private JLabel quantity_label;
		
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

		/*GridBagConstraints gc = new GridBagConstraints();*/
		GridBagLayout gb = new GridBagLayout();

		panel.setLayout(gb);
/*		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridheight = GridBagConstraints.RELATIVE;*/

/*		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.weightx = 0;*/

		addItem = new JButton("Add");
		
		id_label = new JLabel("Id: ");
		name_label = new JLabel("Name: ");
		price_label = new JLabel("Price: ");
		quantity_label = new JLabel("Quantity: ");
		
		id_field = new JTextField("", 5);
		name_field = new JTextField("", 15);
		price_field = new JTextField("", 5);
		quantity_field = new JTextField("", 5);		
		
		//gc.gridwidth = GridBagConstraints.RELATIVE;
		//gc.weightx = 1.0;
		
		addItem.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  addButtonClicked();
		      }
	    });
		
		panel.add(id_label);
		panel.add(id_field);
		panel.add(name_label);
		panel.add(name_field);
		panel.add(price_label);
		panel.add(price_field);
		panel.add(quantity_label);
		panel.add(quantity_field);
		panel.add(addItem);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		return panel;
	}

	// table of the wareshouse stock
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
			System.out.println("ID must be a number!");
			return;
		}
		ArrayList<Long> stockIDs = new ArrayList<Long>();
		for(StockItem item : model.getWarehouseTableModel().getTableRows()){
    		stockIDs.add(item.getId());
    	}
		for(Long stockItemID : stockIDs){
			if(stockItemID == id){
				System.out.println("ID must be unique!");
				return;
			}
		}

		name = name_field.getText();
		if(name.length() <= 0){
			System.out.println("Name field is empty!");
			return;
		}
	
		try {
			price = Double.parseDouble(price_field.getText());
			price = (double)Math.round(price * 100) / 100;
			System.out.println(price);
		} catch (NumberFormatException e2){
			System.out.println("Price must be a number (or maybe you used a comma instead of a dot?)");
			return;
		}
	
		try {
			quantity = Integer.parseInt(quantity_field.getText());
	
		} catch (NumberFormatException e3){
			System.out.println("Quantity must be a number");
			return;
		}
		
		newStockItem = new StockItem(id, name, price, quantity);
		model.getWarehouseTableModel().addItem(newStockItem);
		
		cleanUpAdd();
	}

	private void cleanUpAdd() {		
		id_field.setText(null);
		name_field.setText(null);
		price_field.setText(null);
		quantity_field.setText(null);
	}
	
}
