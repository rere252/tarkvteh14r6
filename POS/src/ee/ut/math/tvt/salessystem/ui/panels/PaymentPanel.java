package ee.ut.math.tvt.salessystem.ui.panels;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;
import ee.ut.math.tvt.salessystem.ui.tabs.PurchaseTab;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PaymentPanel {
	
	private static final Logger log = LogManager.getLogger(PaymentPanel.class);
	
	private static final long serialVersionUID = 1L;
	
	private static PurchaseTab purchaseTab;
	private static JFrame paymentFrame;
	private static JPanel panel;
	private static JLabel price;
	private static JLabel amountPaid;
	private static JLabel returnMoney;
	private static JTextField totalSum;
	private static JTextField paidAmount;
	private static JTextField change;
	private static JButton acceptButton;
	private static JButton cancelButton;
	private static PurchaseInfoTableModel parent;
	private static double finalPrice;
    private static boolean confirmed = false;
    
    private static void confirm(){
    	
    	if(!confirmed){
    		
    		JFrame paymentFrame = new JFrame("Payment");
    		paymentFrame.setAlwaysOnTop(true);
            paymentFrame.setResizable(false);
    		paymentFrame.setVisible(true);
    		paymentFrame.setSize(500,200);
    		paymentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		
    		JPanel panel = new JPanel();
    		panel.setLayout(new GridLayout(5, 2));
    		paymentFrame.add(panel);
    		
    		
    		JLabel price= new JLabel("Total sum:");
    		panel.add(price);
    		
    		//double sum = parent.totalAmount();
    		JTextField totalSum= new JTextField("100");
    		price.add(totalSum);
    		
    		
    		JLabel amountPaid = new JLabel("Amount paid:");
    		panel.add(amountPaid);
    		
    		JTextField paidAmount = new JTextField();
    		panel.add(paidAmount);
    		
    		//double changeSum = sum - Double.parseDouble(paidAmount.getText());
    		JLabel returnMoney = new JLabel("Change:");
    		panel.add(returnMoney);
    		
    		JTextField change= new JTextField();
    		returnMoney.add(change);
    		
    		JButton acceptButton = new JButton("Accept");
    		panel.add(acceptButton);
    		acceptButton.addActionListener(new ActionListener() {
    		      public void actionPerformed(ActionEvent e) {
    		    	  purchaseTab.acceptPurchaseButtonClicked();
    		        }
    		      });
    		
    		 JButton okButton = new JButton("Ok");
    	        okButton.addActionListener(new ActionListener() {
    	            public void actionPerformed(ActionEvent e) {
    	               okButtonClikked();
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
    		
    		confirmed = true;
    		
    	}
    }
    
    private static void cancelPayment() {
    	paymentFrame.setVisible(false);
    }
    
    private static void okButtonClikked(){
    	double cash = 0.0;
        try {
            cash = Double.parseDouble(paidAmount.getText());
        } catch (NumberFormatException nfe) {
        }
        
        double returnMoney = cash - Double.parseDouble(totalSum.getText());

        change.setText(Double.toString(returnMoney));
        if (returnMoney >= 0) {
            acceptButton.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Not enough cash entered.");
        }
    }
    public static void show(final double priceFinal, final PurchaseTab tabPurchase) {
            finalPrice = priceFinal;
            purchaseTab = tabPurchase;
            
            confirm();
            
            // Update the text fields 
            price.setText(String.valueOf(finalPrice));
            paidAmount.setText("");
            change.setText("");
            
            // And make sure the "Commit" button is always unavailable at first
            acceptButton.setEnabled(false);

            // Display the window:
            paymentFrame.pack();
            paymentFrame.setVisible(true);
            
        }
    
}