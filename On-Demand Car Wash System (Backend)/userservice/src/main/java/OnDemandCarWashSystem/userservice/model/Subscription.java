package OnDemandCarWashSystem.userservice.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Subscription")
public class Subscription {
    @Id
    private String id;
    private String subscriptionType;
    private String name;
    private String email;
    private String phone;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubscriptionType() {
		return subscriptionType;
	}
	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Subscription(String id, String subscriptionType, String name, String email, String phone) {
		super();
		this.id = id;
		this.subscriptionType = subscriptionType;
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	public Subscription() {
		super();
	}
	@Override
	public String toString() {
		return "Subscription [id=" + id + ", subscriptionType=" + subscriptionType + ", name=" + name + ", email="
				+ email + ", phone=" + phone + "]";
	}
    
    
	
    
    
	
	
    
}
