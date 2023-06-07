package OnDemandCarWashSystem.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import OnDemandCarWashSystem.userservice.model.Booking;
import OnDemandCarWashSystem.userservice.model.Subscription;
import OnDemandCarWashSystem.userservice.model.Userdetails;
import OnDemandCarWashSystem.userservice.repo.BookingRepository;
import OnDemandCarWashSystem.userservice.repo.SubscriptionRepository;
import OnDemandCarWashSystem.userservice.repo.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private BookingRepository bookingRepository;

	public Userdetails addUser(Userdetails user) {
		return repository.save(user);
	}

	public List<Userdetails> getUsers() {
		List<Userdetails> users = repository.findAll();
		System.out.println("Getting data from DB: " + users);
		return users;
	}

	public void deleteUser(Userdetails user) {
		repository.delete(user);
	}

	public void deleteById(String id) {
		repository.deleteById(id);
	}
	
	public List<Subscription> getAllSubscriptions() {
		return subscriptionRepository.findAll();
	}

	public Subscription createSubscription(Subscription subscription) {
		return subscriptionRepository.save(subscription);
	}
	
	public List<Booking> getAllBookings() {
		return bookingRepository.findAll();
	}

	public Booking createBooking(Booking booking) {
		return bookingRepository.save(booking);
	}

	

	
}
