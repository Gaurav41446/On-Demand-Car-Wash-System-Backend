package OnDemandCarWashSystem.paymentservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Payments")
public class PaymentDetails {

    @Id
    private String id;
    private String username;
    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String cvv;
    private String email;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(String expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	public String getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(String expirationYear) {
		this.expirationYear = expirationYear;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public PaymentDetails(String id, String username, String cardNumber, String expirationMonth, String expirationYear,
			String cvv, String email) {
		super();
		this.id = id;
		this.username = username;
		this.cardNumber = cardNumber;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.cvv = cvv;
		this.email = email;
	}
	@Override
	public String toString() {
		return "PaymentDetails [id=" + id + ", username=" + username + ", cardNumber=" + cardNumber
				+ ", expirationMonth=" + expirationMonth + ", expirationYear=" + expirationYear + ", cvv=" + cvv
				+ ", email=" + email + "]";
	}
	public PaymentDetails() {
		super();
	}
    
    
    
    
    
    
	

 

    
}
