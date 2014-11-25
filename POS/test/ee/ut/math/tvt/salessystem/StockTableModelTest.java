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
	private StockTableModel model;
	private PurchaseInfoTableModel purchaseModel;
	
	@Before
	public void setUp() {
		item1 = new StockItem((long)100, "Pepsi", "0.5L", 1.5, 10);
		item2 = new StockItem((long)101, "Pepsi", "0.5L", 1.5, 10);
		model = new StockTableModel();
	}
	@Test
	public void testValidateNameUniqueness(){
		model.addItem(item1);
		model.addItem(item2);
		
	}
	@Test(expected=IllegalArgumentException.class)
	public void testHasEnoughInStock(){
		purchaseModel = new PurchaseInfoTableModel();
		purchaseModel.addItem(new SoldItem(item1, 100));
	}
	@Test
	public void testGetItemByIdWhenItemExists(){
		
	}
	@Test
	public void testGetItemByIdWhenThrowsException(){
		
	}
}
