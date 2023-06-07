package OnDemandCarWashSystem.washerservice.model;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;



@Document(collection = "Washerdata")
public class WasherDetails {

	@Id
    private String id;

    @NotEmpty(message = "Name must not be empty")
    private String name;

    @NotEmpty(message = "Location must not be empty")
    private String location;

    @NotEmpty(message = "Password must not be empty")
    private String password;

    

    public WasherDetails(String id, String name, String location, String password) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.password = password;
    }
    
    

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
    public String toString() {
        return "WasherDetails [id=" + id + ", name=" + name + ", location=" + location + ", password=" + password + "]";
    }

	public WasherDetails() {
		super();
	}

	
}