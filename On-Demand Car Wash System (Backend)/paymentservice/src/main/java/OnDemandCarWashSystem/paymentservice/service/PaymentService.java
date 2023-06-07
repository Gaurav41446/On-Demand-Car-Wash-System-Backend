package OnDemandCarWashSystem.paymentservice.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import OnDemandCarWashSystem.paymentservice.model.PaymentDetails;
import OnDemandCarWashSystem.paymentservice.repo.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public PaymentDetails createPayment(PaymentDetails payment) {
        return paymentRepository.save(payment);
    }

//    public PaymentDetails getPayment(Long paymentId) {
//        Optional<PaymentDetails> optionalPayment = paymentRepository.findById(paymentId);
//        return optionalPayment.orElse(null);
//    }
    
    public PaymentDetails getPaymentById(String payment) {
    	Optional<PaymentDetails>optionalPayment=paymentRepository.findById(payment);
    	return optionalPayment.orElse(null);
    	
    }

    public List<PaymentDetails> getAllPayments() {
        return paymentRepository.findAll();
    }
}

