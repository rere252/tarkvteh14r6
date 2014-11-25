package ee.ut.math.tvt.salessystem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.ui.model.PurchaseInfoTableModel;

public class PurchaseInfoTableModelTest {
	  private StockItem stockitem1;
	  private StockItem stockitem2;
	  private StockItem stockitem3;
	  
	  private SoldItem solditem1;
	  private SoldItem solditem2;
	  private SoldItem solditem3;
	  private SoldItem solditem4;
	  
	  @Before
	  public void setUp() {
		  stockitem1 = new StockItem(1l,"Pepsi", "0.5L",2.5,10);
		  stockitem2 = new StockItem(1l,"Pepsi", "0.5L",2.5,10);
		  stockitem3 = new StockItem(1l,"Fanta", "0.5L",2.5,3);
		  
		  solditem1 = new SoldItem(stockitem1, 2);
		  solditem2 = new SoldItem(stockitem2, 4);
		  solditem3 = new SoldItem(stockitem3, 4);
		  solditem4 = new SoldItem(stockitem3, 0);
	  }
	  
	  @Test
	  public void testAddItemWithSameId(){
		  PurchaseInfoTableModel model = new PurchaseInfoTableModel();
		  model.addItem(solditem1);
		  model.addItem(solditem2);
	  }
	  
	  @Test(expected = IllegalArgumentException.class)
	  public void testItemHasEnoghStock(){
		  PurchaseInfoTableModel model1 = new PurchaseInfoTableModel();
		  model1.addItem(solditem3);
	  }
	  
	  @Test
	  public void testItemWithZeroQuantity(){
		  PurchaseInfoTableModel model2 = new PurchaseInfoTableModel();
		  model2.addItem(solditem4);
	  
	  }
}
