package in.co.nog.mgt.bean;

import java.util.Date;

public class FundraiserBean extends BaseBean {

	private String name;
	private Date   date;
	private String  description;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getKey() {
		return id+"";
	}

	@Override
	public String getValue() {
		return name;
	}

}
