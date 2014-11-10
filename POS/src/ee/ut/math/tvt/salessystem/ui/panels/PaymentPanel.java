package ee.ut.math.tvt.salessystem.ui.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class PaymentPanel {	
	//Logger, serial version.
	private static final Logger log = LogManager.getLogger(PaymentPanel.class);
	private static final long serialVersionUID = 1L;
	// Variables.
	private static PurchaseTab purchaseTab;
	private static PurchaseInfoTableModel currentCart;
	private static double sumTotal; 
	//JPanel elements. 
	private static JFrame paymentFrame;
	private static JPanel panel;
	private static JLabel price;
	private static JLabel amountPaid;
	private static JLabel returnMoney;
	private static JLabel totalSum;
	private static JLabel change;
	private static JTextField paidAmount;
	private static JButton acceptButton;
	private static JButton cancelButton;
    private static boolean confirmed = false;

    public static void show(final PurchaseInfoTableModel cart_forwarded, 
							final PurchaseTab tabPurchase) {
		//Initialize class variables. 
        purchaseTab = tabPurchase;
		currentCart = cart_forwarded;
    	if(!confirmed){    		
			//Frame
    		paymentFrame = new JFrame("Payment");
    		paymentFrame.setAlwaysOnTop(true);
            paymentFrame.setResizable(false);
    		paymentFrame.setVisible(true);
    		paymentFrame.setSize(500,200);
    		paymentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    	
			//Panel	
    		panel = new JPanel();
    		panel.setLayout(new GridLayout(5, 2));
    		paymentFrame.add(panel);
			//Panel fields/labels
    		price= new JLabel("Total sum:");
    		panel.add(price);	
    		totalSum = new JLabel(String.valueOf(
										 calculateTotal(
    									 currentCart.getTableRows())));
    		panel.add(totalSum);    		
    		amountPaid = new JLabel("Amount paid:");
    		panel.add(amountPaid);	
    		paidAmount = new JTextField();
    		panel.add(paidAmount);
			paidAmount.addKeyListener(new KeyListener(){
 				public void keyPressed(KeyEvent e){}
				public void keyReleased(KeyEvent e){updateChange(); }
				public void keyTyped(KeyEvent e){}
			});
    		returnMoney = new JLabel("Change:");
    		panel.add(returnMoney);
    		change= new JLabel(String.valueOf(0-(Double.parseDouble(totalSum.getText()))));
    		panel.add(change);
			//Buttons
    		
    		acceptButton = new JButton("Accept");
        	acceptButton.addActionListener(new ActionListener() {
    		    public void actionPerformed(ActionEvent e) {
    				try {
    					double cash = (Double.parseDouble(paidAmount.getText()))-sumTotal;
    					if (cash >= 0) {
    						purchaseTab.acceptPurchaseButtonClicked();
    						paymentFrame.setVisible(false);
    						newQuantity(null, null);
    						
    					} else {
    						JOptionPane.showMessageDialog(null,
                    			   "Insufficient amount paid.");
    					}
    				} catch (NumberFormatException nfe) {
    					JOptionPane.showMessageDialog(null,
                    "Amount paid must be number.");
    				}
    	
    			}
    		});
    		panel.add(acceptButton);
    		
    		cancelButton = new JButton("Cancel");
    		panel.add(cancelButton);
    		cancelButton.addActionListener (new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				purchaseTab.cancelPayment();
    		    	paymentFrame.setVisible(false);
    		    	
    			}
    		});  
    		
    		
			//Set confirmed to true.
    		confirmed = true;	
    	}          
        // Display the window:
        paymentFrame.setVisible(true);   
    }
   
   

	private static double calculateTotal(List<SoldItem> items) {
		double total = 0.00; 
		for (SoldItem item : items) {
			total += (item.getPrice()*item.getQuantity());
		}
		sumTotal = total; 
		return total;
	}
	
	private static void updateChange() {
		try {
            double cash = Double.parseDouble(paidAmount.getText());
			change.setText(String.valueOf(cash-sumTotal));
        } catch (NumberFormatException nfe) {}
	}
    
	private static void newQuantity(final List<SoldItem> items, 
			final List<StockItem> stocks){
		for (SoldItem item : items){
			for(StockItem stock : stocks){
				if (stock.getId() == item.getId()){
					stock.setQuantity(stock.getQuantity()-item.getQuantity());
				}					
			}
		}
	}
}
