package OnDemandCarWashSystem.paymentservice.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import OnDemandCarWashSystem.paymentservice.model.PaymentDetails;

@Repository
public interface PaymentRepository extends MongoRepository<PaymentDetails, String> {

//	Optional<PaymentDetails> findById(Long paymentId);
	Optional<PaymentDetails>findById(String payment);


}

