package OnDemandCarWashSystem.paymentservice.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.security.core.userdetails.UserDetails;

import OnDemandCarWashSystem.paymentservice.model.Userdetails;



public interface UserRepository extends MongoRepository<Userdetails, String> {


	Userdetails findByEmail(String email);

	Optional<Userdetails> findByEmail(boolean b);

	Optional<Userdetails> findById(String id);

	boolean existsById(String id);

	void deleteById(String id);



}

