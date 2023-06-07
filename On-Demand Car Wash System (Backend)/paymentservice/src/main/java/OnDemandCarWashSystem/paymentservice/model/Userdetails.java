package OnDemandCarWashSystem.paymentservice.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "Userdata")
public class Userdetails {

    @Id
    
    String id;

    @NotEmpty(message = "Name must not be empty")
    String email;

    @NotEmpty(message = "Password must not be empty")
    String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Userdetails(String id, @NotEmpty(message = "Name must not be empty") String email,
			@NotEmpty(message = "Password must not be empty") String password) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
	}

	public Userdetails() {
		super();
	}

    

	
}
