package OnDemandCarWashSystem.userservice.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import OnDemandCarWashSystem.userservice.model.User;
import OnDemandCarWashSystem.userservice.model.Userdetails;

public interface UserRepository extends MongoRepository<Userdetails, Integer> {


	Userdetails findByEmail(String email);

	Optional<Userdetails> findByEmail(boolean b);

	Optional<Userdetails> findById(String id);

	boolean existsById(String id);

	void deleteById(String id);



}

