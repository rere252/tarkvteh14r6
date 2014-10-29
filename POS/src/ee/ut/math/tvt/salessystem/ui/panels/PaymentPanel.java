package ee.ut.math.tvt.salessystem.ui.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
	private static JTextField paidAmount;
	private static JTextField change;
	private static JButton acceptButton;
	private static JButton cancelButton;
	private static PurchaseInfoTableModel parent;
    private static boolean confirmed = false;

    public static void show(final PurchaseInfoTableModel cart_forwarded, 
							final PurchaseTab tabPurchase) {
            purchaseTab = tabPurchase;
			currentCart = cart_forwarded;
            confirm();           
            // And make sure the "Commit" button is always unavailable at first
            acceptButton.setEnabled(false);
            // Display the window:
            paymentFrame.pack();
            paymentFrame.setVisible(true);   
        }
    
    private static void confirm(){    	
    	if(!confirmed){    		
			//Frame
    		JFrame paymentFrame = new JFrame("Payment");
    		paymentFrame.setAlwaysOnTop(true);
            paymentFrame.setResizable(false);
    		paymentFrame.setVisible(true);
    		paymentFrame.setSize(500,200);
    		paymentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    	
			//Panel	
    		JPanel panel = new JPanel();
    		panel.setLayout(new GridLayout(5, 2));
    		paymentFrame.add(panel);
			//Panel fields/labels
    		JLabel price= new JLabel("Total sum:");
    		panel.add(price);	
    		JLabel totalSum = new JLabel(String.valueOf(
										 calculateTotal(
    									 currentCart.getTableRows())));
    		panel.add(totalSum);    		
    		JLabel amountPaid = new JLabel("Amount paid:");
    		panel.add(amountPaid);	
    		JTextField paidAmount = new JTextField();
    		panel.add(paidAmount);
    		JLabel returnMoney = new JLabel("Change:");
    		panel.add(returnMoney);
    		JTextField change= new JTextField();
    		panel.add(change);
			//Buttons
    		JButton acceptButton = new JButton("Accept");
    		panel.add(acceptButton);
    		acceptButton.addActionListener(new ActionListener() {
    		      public void actionPerformed(ActionEvent e) {
    		    	  purchaseTab.acceptPurchaseButtonClicked();
    		        }
    		      });
    		JButton cancelButton = new JButton("Cancel");
    		panel.add(cancelButton);
    		cancelButton.addActionListener (new ActionListener() {
    			 public void actionPerformed(ActionEvent e) {
   		    	  purchaseTab.cancelPayment();
   		    	  cancelPayment();	  
   		        }
    		});  		
			//Set confirmed to true.
    		confirmed = true;	
    	}
    }
    
    private static void cancelPayment() {
    	paymentFrame.setVisible(false);
    }
    
 /*   private static void okButtonClikked(){
    	double cash = 0.0;
        try {
            cash = Double.parseDouble(paidAmount.getText());
        } catch (NumberFormatException nfe) {}
        double returnMoney = cash - Double.parseDouble(totalSum.getText());
        change.setText(Double.toString(returnMoney));
        if (returnMoney >= 0) {
            acceptButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Not enough cash entered.");
        }
    } */

	private static double calculateTotal(List<SoldItem> items) {
		double total = 0.00; 
		for (SoldItem item : items) {
			total += (item.getPrice()*item.getQuantity());
		}
		sumTotal = total; 
		return total;
	}
    
}
