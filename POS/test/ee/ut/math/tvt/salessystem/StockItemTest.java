package ee.ut.math.tvt.salessystem;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class StockItemTest {
	@Test
	public void testClone(){
		StockItem item = new StockItem((long)100, "Pepsi", "0.5L", 2.5, 1000);				
		StockItem item2 = (StockItem) item.clone();					
		assertEquals(item2.getId(), item.getId(), 0.0001);
		assertEquals(item2.getName(), (String)item.getName());
		assertEquals(item2.getPrice(), item.getPrice(), 0.0001);
		assertEquals(item2.getDescription(), item.getDescription());
		assertEquals(item2.getQuantity(), item.getQuantity(), 0.0001);
		 }
	@Test
	public void testGetColumn(){
		StockItem item = new StockItem(1l, "Fanta", "0.33L", 1.5, 1000);
		assertEquals(item.getColumn(0), item.getId());
		assertEquals(item.getColumn(1), item.getName());
		assertEquals(item.getColumn(2), item.getPrice());
		assertEquals(item.getColumn(3), item.getQuantity());		
	}
	@Test (expected=RuntimeException.class)
	public void testGetNonExistingRow(){
		StockItem item = new StockItem(1l, "Sprite", "0.33L", 1.5, 1000);
		item.getColumn(100);
	}
}
