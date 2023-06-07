package OnDemandCarWashSystem.adminservice.model;

import org.springframework.data.mongodb.core.mapping.Document;



import org.springframework.data.annotation.Id;

@Document(collection="Ratings")
public class Ratings {
	
	@Id
    private String id;
    private String washerName;
    private String email;
    private int rating;
    private String feedback;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWasherName() {
		return washerName;
	}
	public void setWasherName(String washerName) {
		this.washerName = washerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public Ratings(String id, String washerName, String email, int rating, String feedback) {
		super();
		this.id = id;
		this.washerName = washerName;
		this.email = email;
		this.rating = rating;
		this.feedback = feedback;
	}
	@Override
	public String toString() {
		return "Ratings [id=" + id + ", washerName=" + washerName + ", email=" + email + ", rating=" + rating
				+ ", feedback=" + feedback + "]";
	}
	public Ratings() {
		super();
	}
	
	
    

}