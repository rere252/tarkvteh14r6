package ee.ut.math.tvt.salessystem;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.domain.data.StockItem;

public class SoldItemTest {
	
	private StockItem item1;
	
	@Before
	public void setUp() {
		item1 = new StockItem((long)102,"Fanta","Karastusjook",1.5,100);
	}
	@Test
	public void testGetSum(){
		SoldItem item2 = new SoldItem(item1, 10);
		assertEquals(item2.getSum(), 15, 0.0001);
		
		
	}
	@Test
	public void testGetSumWithZeroQuantity(){
		SoldItem item3 = new SoldItem(item1,0);
		assertEquals(item3.getSum(), 0, 0.0001);
		
	}
	
}

