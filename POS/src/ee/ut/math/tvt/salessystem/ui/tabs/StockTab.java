package ee.ut.math.tvt.salessystem.ui.tabs;


import ee.ut.math.tvt.salessystem.domain.controller.SalesDomainController;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;

public class StockTab {

	private JButton addItem;

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

		GridBagConstraints gc = new GridBagConstraints();
		GridBagLayout gb = new GridBagLayout();

		panel.setLayout(gb);

		gc.anchor = GridBagConstraints.NORTHWEST;
		gc.weightx = 0;

		addItem = new JButton("Add");
		gc.gridwidth = GridBagConstraints.RELATIVE;
		gc.weightx = 1.0;
		

		addItem.addActionListener(new ActionListener() {
		      public void actionPerformed(ActionEvent e) {
		    	  addButtonClicked();
		      }
	    });
		panel.add(addItem, gc);
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
		AddItemScreen();
	}
//Adding new items to the warehouse
public void AddItemScreen(){
	final JFrame new_frame=new JFrame();
	int width = 800;
	final int height = 150;
	JPanel panel = new JPanel();
	final JTextField id_field = new JTextField("", 5);
	final JTextField name_field = new JTextField("", 15);
	final JTextField price_field = new JTextField("", 5);
	final JTextField quantity_field = new JTextField("", 5);
	
	JLabel id_label = new JLabel("Id: ");
	JLabel name_label = new JLabel("Name: ");
	JLabel price_label = new JLabel("Price: ");
	JLabel quantity_label = new JLabel("Quantity: ");
	
	//Creating confirm and cancel buttons
	JButton confirm = new JButton("confirm");
	final JButton cancel = new JButton("cancel");
	
	panel.add(confirm);
	panel.add(cancel);
	
	panel.add(id_label);
	panel.add(id_field);
	panel.add(name_label);
	panel.add(name_field);
	panel.add(price_label);
	panel.add(price_field);
	panel.add(quantity_label);
	panel.add(quantity_field);

    //Adding items to the list
	confirm.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			id = Long.parseLong(id_field.getText());
			name = name_field.getText();
			price = Double.parseDouble(price_field.getText());
			quantity = Integer.parseInt(quantity_field.getText());
			if (id > 0 && name != ""  && price >= 0 && quantity > 0) {
				newStockItem = new StockItem(id, name, price, quantity);
				StockTableModel model_uus = model.getWarehouseTableModel();
				model_uus.addItem(newStockItem);
				new_frame.setVisible(false);
				addItem.setEnabled(true);
			} else {
				new_frame.setVisible(false);
				addItem.setEnabled(true);
			}
		}
	});

	cancel.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new_frame.setVisible(false);
		}
	});
	new_frame.add(panel);
	new_frame.setTitle("Adding products to warehouse");
	new_frame.setSize(width, height);
	new_frame.setVisible(true);
}
}
