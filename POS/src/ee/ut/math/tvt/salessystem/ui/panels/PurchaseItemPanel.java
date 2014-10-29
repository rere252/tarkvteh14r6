package ee.ut.math.tvt.salessystem.ui.panels;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.SalesSystemModel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Purchase pane + shopping cart tabel UI.
 */
public class PurchaseItemPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    // Text field on the dialogPane
    private JTextField quantityField;
    private JTextField priceField;
    private JTextField barCodeField;

    public static JComboBox<String> nameBox;
    
    private JButton addItemButton;

    // Warehouse model
    private SalesSystemModel model;

    /**
     * Constructs new purchase item panel.
     * 
     * @param model
     *            composite model of the warehouse and the shopping cart.
     */
    public PurchaseItemPanel(SalesSystemModel model) {
        this.model = model;

        setLayout(new GridBagLayout());

        add(drawDialogPane(), getDialogPaneConstraints());
        add(drawBasketPane(), getBasketPaneConstraints());

        setEnabled(false);
    }

    // shopping cart pane
    private JComponent drawBasketPane() {

        // Create the basketPane
        JPanel basketPane = new JPanel();
        basketPane.setLayout(new GridBagLayout());
        basketPane.setBorder(BorderFactory.createTitledBorder("Shopping cart"));

        // Create the table, put it inside a scollPane,
        // and add the scrollPane to the basketPanel.
        JTable table = new JTable(model.getCurrentPurchaseTableModel());
        JScrollPane scrollPane = new JScrollPane(table);

        basketPane.add(scrollPane, getBacketScrollPaneConstraints());

        return basketPane;
    }

    // purchase dialog
    private JComponent drawDialogPane() {

        // Create the panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.setBorder(BorderFactory.createTitledBorder("Product"));

        // Initialize the textfields
        //barCodeField = new JTextField();
        quantityField = new JTextField("1");
        barCodeField = new JTextField();
        priceField = new JTextField();
        
        // Initialize the items name dropdown menu
        nameBox = new JComboBox<String>();
        fillNameBox();

        nameBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fillDialogFields();
			}
        	
        });
        
        barCodeField.setEditable(false);
        priceField.setEditable(false);
   

        // == Add components to the panel
        
        // - name
        panel.add(new JLabel("Name:"));
        panel.add(nameBox);

        // - amount
        panel.add(new JLabel("Amount:"));
        panel.add(quantityField);
        
        // - bar code
        panel.add(new JLabel("Bar code:"));
        panel.add(barCodeField);

        // - price
        panel.add(new JLabel("Price:"));
        panel.add(priceField);

        // Create and add the button
        addItemButton = new JButton("Add to cart");
        addItemButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addItemEventHandler();
            }
        });

        panel.add(addItemButton);

        return panel;
    }
    
    //Fill name box
    public void fillNameBox(){
    	for(StockItem item : model.getWarehouseTableModel().getTableRows()){
    		nameBox.addItem(item.getName());
    	}
    }

    // Fill dialog with data from the "database".
    public void fillDialogFields() {
        StockItem stockItem = getStockItemByName();

        if (stockItem != null) {
            barCodeField.setText(stockItem.getId().toString());
            String priceString = String.valueOf(stockItem.getPrice());
            priceField.setText(priceString);
        } else {
            reset();
        }
    }
    
    private StockItem getStockItemByName() {
    	try {
    		String name = nameBox.getSelectedItem().toString();
    		return model.getWarehouseTableModel().getItemByName(name);
    	} catch (NoSuchElementException nsee){
    		return null;
    	}
    }
    
    

    /**
     * Add new item to the cart.
     */
    public void addItemEventHandler() {
        // add chosen item to the shopping cart.
        StockItem stockItem = getStockItemByName();
        if (stockItem != null) {
            int quantity;
            try {
                quantity = Integer.parseInt(quantityField.getText());
            } catch (NumberFormatException ex) {
                quantity = 0;
            }
			//Check whether there are items of this kind in the table already.
			boolean row_exists = false; 
			try {
				SoldItem inCart = (model.getCurrentPurchaseTableModel())
										.getItemById(stockItem.getId());
				quantity += inCart.getQuantity();
				row_exists = true; 
			} catch (NoSuchElementException nsee) {}
            //checking if warehouse has enough items in stock
            //should also check how many items are in the cart
            if (quantity > stockItem.getQuantity()){
            	JOptionPane.showMessageDialog(this, "Not enough items in stock!");
            	return;
            }
			else if (quantity <= 0) {
            	JOptionPane.showMessageDialog(this, "Invalid item quantity input!");
            	return;
			}
            if (!row_exists) {
				model.getCurrentPurchaseTableModel()
                .addItem(new SoldItem(stockItem, quantity));
				}
			else {
				model.getCurrentPurchaseTableModel()
					 .getItemById(stockItem.getId())
					 .setQuantity(quantity);
				model.getCurrentPurchaseTableModel()
					 .fireTableDataChanged();
			}
        }
    }

    /**
     * Sets whether or not this component is enabled.
     */
    @Override
    public void setEnabled(boolean enabled) {
        this.addItemButton.setEnabled(enabled);
        this.barCodeField.setEnabled(enabled);
        this.quantityField.setEnabled(enabled);
        nameBox.setEnabled(enabled);
    }

    /**
     * Reset dialog fields.
     */
    public void reset() {
        barCodeField.setText("");
        quantityField.setText("1");
        priceField.setText("");
    }

    /*
     * === Ideally, UI's layout and behavior should be kept as separated as
     * possible. If you work on the behavior of the application, you don't want
     * the layout details to get on your way all the time, and vice versa. This
     * separation leads to cleaner, more readable and better maintainable code.
     * 
     * In a Swing application, the layout is also defined as Java code and this
     * separation is more difficult to make. One thing that can still be done is
     * moving the layout-defining code out into separate methods, leaving the
     * more important methods unburdened of the messy layout code. This is done
     * in the following methods.
     */

    // Formatting constraints for the dialogPane
    private GridBagConstraints getDialogPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.WEST;
        gc.weightx = 0.2;
        gc.weighty = 0d;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.fill = GridBagConstraints.NONE;

        return gc;
    }

    // Formatting constraints for the basketPane
    private GridBagConstraints getBasketPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.anchor = GridBagConstraints.WEST;
        gc.weightx = 0.2;
        gc.weighty = 1.0;
        gc.gridwidth = GridBagConstraints.REMAINDER;
        gc.fill = GridBagConstraints.BOTH;

        return gc;
    }

    private GridBagConstraints getBacketScrollPaneConstraints() {
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.BOTH;
        gc.weightx = 1.0;
        gc.weighty = 1.0;

        return gc;
    }

}
