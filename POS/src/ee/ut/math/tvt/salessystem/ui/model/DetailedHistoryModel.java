package ee.ut.math.tvt.salessystem.ui.model;

import java.util.List;

import ee.ut.math.tvt.salessystem.domain.data.SoldItem;

public abstract class DetailedHistoryModel extends
		SalesSystemTableModel<SoldItem> {

	public DetailedHistoryModel() {
		super(new String[] { "Id", "Name", "Price", "Quantity", "Sum" });

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Object getColumnValue1(SoldItem item, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return item.getId();
		case 1:
			return item.getName();
		case 2:
			return item.getPrice();
		case 3:
			return item.getQuantity();
		case 4:
			return item.getSum();
		}
		throw new IllegalArgumentException("Column index out of range");
	}

	public void addItems(List<SoldItem> soldItem) {
		rows.clear();
		rows.addAll(soldItem);
	}

	public String toString() {
		final StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < headers.length; i++) {
			buffer.append(headers[i] + "\t");
		}

		buffer.append("\n");

		for (final SoldItem item : rows) {
			buffer.append(item.getId() + "\t");
			buffer.append(item.getName() + "\t");
			buffer.append(item.getPrice() + "\t");
			buffer.append(item.getQuantity() + "\t");
			buffer.append("\n");
		}

		return buffer.toString();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}