package ee.ut.math.tvt.salessystem.domain.data;

import java.util.List;

public class HistoryItem {
	private static long ID = 1;
	private static Long id;
	private final String date;
	private final String time;
	private final double sumTotal;

	public HistoryItem(String time, String date, double sumTotal) {
		this.sumTotal = sumTotal;
		this.date = date;
		this.time = time;
		this.id = ID;
		ID += 1;
	}

	public double getSumTotal() {
		return sumTotal;
	}



	public static Long getId() {
		return id;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

}
