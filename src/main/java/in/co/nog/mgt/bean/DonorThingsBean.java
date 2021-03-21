package in.co.nog.mgt.bean;

public class DonorThingsBean extends BaseBean {

	private long userId;
	private String name;
	private String email;
	private String description;
	
	

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
