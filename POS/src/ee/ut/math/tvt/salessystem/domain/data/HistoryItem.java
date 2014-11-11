package ee.ut.math.tvt.salessystem.domain.data;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="HISTORYITEM")
public class HistoryItem implements Cloneable, DisplayableItem{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "date")
	private Date date;
	@Column(name = "time")
	private String time;
	@Column(name = "total_sum")
	private double sumTotal;
	
	public HistoryItem(){}

	public HistoryItem(double sumTotal) {
		this.sumTotal = sumTotal;
		this.date = new Date(System.currentTimeMillis());
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		this.time = df.format(date.getTime()); 
	}

	public double getSumTotal() {
		return sumTotal;
	}

	public Long getId() {
		return id;
	}

	public String getDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String sDate = df.format(this.date);
		return sDate;
	}

	public String getTime() {
		return time;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
