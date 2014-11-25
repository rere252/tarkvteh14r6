package ee.ut.math.tvt.salessystem;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.StockTableModel;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

import java.util.NoSuchElementException;


public class StockTableModelTest {
	private StockItem item1;
	private StockItem item2;
	private StockItem item3;
	private SoldItem solditem;
	private StockTableModel model;
	private PurchaseInfoTableModel purchaseModel;
	
	@Before
	public void setUp() {
		item1 = new StockItem((long)100, "Pepsi", "0.5L", 1.5, 10);
		item2 = new StockItem((long)101, "Pepsi", "0.5L", 1.5, 10);
		item3 = new StockItem((long)102,"Fanta","Karastusjook",1.5,100);
		solditem = new SoldItem(item1, 15);
		

	}
	@Test
	public void testValidateNameUniqueness(){
		model = new StockTableModel();
		model.addItem(item1);
		model.addItem(item2);
		
	}
	@Test
	public void testHasEnoughInStock(){
		assertTrue(item1.getQuantity()<=solditem.getQuantity());
	}
	@Test
	public void testGetItemByIdWhenItemExists(){
		model = new StockTableModel();
		model.addItem(item1);
    	assertEquals(model.getItemById((long)100).toString(), item1.toString());
		
	}
	@Test(expected = NoSuchElementException.class)
	public void testGetItemByIdWhenThrowsException(){
		model = new StockTableModel();
		model.addItem(item1);
		model.addItem(item3);
		model.getItemById(5000);
		
	}
}
