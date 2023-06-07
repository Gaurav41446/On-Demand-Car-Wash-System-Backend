package OnDemandCarWashSystem.washerservice.repo;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import OnDemandCarWashSystem.washerservice.model.WasherDetails;

@Repository
public interface WasherRepository extends MongoRepository<WasherDetails, String> {
    List<WasherDetails> findByLocation(String location);
    WasherDetails findByName(String name);
	Optional<WasherDetails> findById(String id);
	void deleteById(String id);
	boolean existsById(String id);
}