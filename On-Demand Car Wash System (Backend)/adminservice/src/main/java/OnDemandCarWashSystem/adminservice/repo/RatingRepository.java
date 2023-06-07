package OnDemandCarWashSystem.adminservice.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import OnDemandCarWashSystem.adminservice.model.Ratings;

public interface RatingRepository extends MongoRepository<Ratings, Long> {
}
