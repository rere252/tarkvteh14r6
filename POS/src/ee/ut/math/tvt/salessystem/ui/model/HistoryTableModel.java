package ee.ut.math.tvt.salessystem.ui.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ee.ut.math.tvt.salessystem.domain.data.HistoryItem;

public class HistoryTableModel extends SalesSystemTableModel<HistoryItem>{
	private static final long serialVersionUID = 1L;

	private static final Logger log = LogManager.getLogger(HistoryTableModel.class);

	public HistoryTableModel() {
		super(new String[] {"Id", "Date", "Time", "Total sum"});
	}

	@Override
	protected Object getColumnValue(HistoryItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getDate();
		case 2:
			return item.getTime();
		case 3:
			return item.getSumTotal();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	public void addItem(final HistoryItem historyItem) {
		rows.add(historyItem);
		fireTableDataChanged();
	}
	

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++)
			buffer.append(headers[i] + "\t");
		buffer.append("\n");

		for (final HistoryItem stockItem : rows) {
			buffer.append(stockItem.getId() + "\t");
			buffer.append(stockItem.getDate() + "\t");
			buffer.append(stockItem.getTime() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}
}
