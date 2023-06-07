package OnDemandCarWashSystem.adminservice.repo;


import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import OnDemandCarWashSystem.adminservice.model.AdminDetails;


@Repository
public interface AdminRepository extends MongoRepository<AdminDetails, String> {

//	AdminDetails findByUsername(String username);
	AdminDetails findByEmail(String email);

	void deleteById(String id);

	Optional<AdminDetails> findById(String id);
	AdminDetails findByEmailAndPassword(String email,String password);

	

}
