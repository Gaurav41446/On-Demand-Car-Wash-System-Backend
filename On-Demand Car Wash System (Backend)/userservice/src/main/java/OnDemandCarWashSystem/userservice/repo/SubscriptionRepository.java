package OnDemandCarWashSystem.userservice.repo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import OnDemandCarWashSystem.userservice.model.Subscription;

@Repository
public interface SubscriptionRepository extends MongoRepository<Subscription, String> {

	Subscription findByEmail(String email);
}
