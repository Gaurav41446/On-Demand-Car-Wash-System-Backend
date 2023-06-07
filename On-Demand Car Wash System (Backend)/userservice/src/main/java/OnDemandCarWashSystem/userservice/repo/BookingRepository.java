package OnDemandCarWashSystem.userservice.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import OnDemandCarWashSystem.userservice.model.Booking;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

	Optional<Booking> findById(String id);

	
}
