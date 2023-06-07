package OnDemandCarWashSystem.userservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Booking")
public class Booking {

    @Id
    private String bookingId;
    private String name;
    private String phone;
    private String email;
    private String date;
    private String time;
    private String washPacks;
    private String carName;
    private String location; 
    private boolean paymentRequired;
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getWashPacks() {
		return washPacks;
	}
	public void setWashPacks(String washPacks) {
		this.washPacks = washPacks;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public boolean isPaymentRequired() {
		return paymentRequired;
	}
	public void setPaymentRequired(boolean paymentRequired) {
		this.paymentRequired = paymentRequired;
	}
	public Booking(String bookingId, String name, String phone, String email, String date, String time,
			String washPacks, String carName, String location, boolean paymentRequired) {
		super();
		this.bookingId = bookingId;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.date = date;
		this.time = time;
		this.washPacks = washPacks;
		this.carName = carName;
		this.location = location;
		this.paymentRequired = paymentRequired;
	}
	public Booking() {
		super();
	}
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", name=" + name + ", phone=" + phone + ", email=" + email
				+ ", date=" + date + ", time=" + time + ", washPacks=" + washPacks + ", carName=" + carName
				+ ", location=" + location + ", paymentRequired=" + paymentRequired + "]";
	}
    
    
    

    
}
