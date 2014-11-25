package ee.ut.math.tvt.salessystem;

import ee.ut.math.tvt.salessystem.domain.data.StockItem;
import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import ee.ut.math.tvt.salessystem.domain.data.SoldItem;
import ee.ut.math.tvt.salessystem.ui.model.HistoryTableModel;

public class HistoryTableModelTest {
	private StockItem stockitem;
	private SoldItem solditem;
	private HistoryTableModel model;
	private HistoryItem history;
	@Before
	public void setUp(){
		model=new HistoryTableModel();
		stockitem=new StockItem(1l,"Pepsi", "0.5L",2.5,10);
	}
	@Test
	public void testAddItem(){
		solditem=new SoldItem(stockitem,2);
		history=new HistoryItem(solditem.getSum());;
		model.addItem(history);
	}

}

