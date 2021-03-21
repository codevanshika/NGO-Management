package in.co.nog.mgt.bean;

public class DonorFeedbackBean extends BaseBean {

	private long userId;
	private String name;
	private String description;
	private String descriptionOne;
	private String descriptionTwo;
	private String descriptionThree;
	
	


	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescriptionOne() {
		return descriptionOne;
	}

	public void setDescriptionOne(String descriptionOne) {
		this.descriptionOne = descriptionOne;
	}

	public String getDescriptionTwo() {
		return descriptionTwo;
	}

	public void setDescriptionTwo(String descriptionTwo) {
		this.descriptionTwo = descriptionTwo;
	}

	public String getDescriptionThree() {
		return descriptionThree;
	}

	public void setDescriptionThree(String descriptionThree) {
		this.descriptionThree = descriptionThree;
	}

	@Override
	public String getKey() {
		
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		
		return name;
	}

}
