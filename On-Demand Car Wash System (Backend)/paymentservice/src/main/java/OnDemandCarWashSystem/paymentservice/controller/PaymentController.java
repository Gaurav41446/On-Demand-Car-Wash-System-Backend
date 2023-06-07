package OnDemandCarWashSystem.paymentservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import OnDemandCarWashSystem.paymentservice.model.PaymentDetails;
import OnDemandCarWashSystem.paymentservice.model.Userdetails;
import OnDemandCarWashSystem.paymentservice.repo.PaymentRepository;
import OnDemandCarWashSystem.paymentservice.repo.UserRepository;
import OnDemandCarWashSystem.paymentservice.service.PaymentService;




@RestController
@CrossOrigin("http://localhost:3000")
public class PaymentController {
    
    private final PaymentService paymentService;
    
    @Autowired
    private UserRepository repo;
    
    @Autowired
    private PaymentRepository paymentRepository;
    


    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
       
    }
    
    @GetMapping("/allpayments")
    public ResponseEntity<List<PaymentDetails>> getAllPayments() {
        List<PaymentDetails> payments = paymentService.getAllPayments();
        if (!payments.isEmpty()) {
            return ResponseEntity.ok(payments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/payment")
    public PaymentDetails createPayment(@RequestBody PaymentDetails payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/payment/{id}")
    public ResponseEntity<PaymentDetails> getPayment(@PathVariable("id") String paymentId) {
        PaymentDetails payment = paymentService.getPaymentById(paymentId);
        if (payment != null) {
            return ResponseEntity.ok(payment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
        @GetMapping("/allpayment/{id}")
    	public ResponseEntity<String>getPaymentById(@PathVariable("id") String id){
//    		PaymentDetails payment=null;
    		Userdetails user=getUserById(id);
    		List<PaymentDetails>payment=new ArrayList<>();
    		List<PaymentDetails> listpay=paymentRepository.findAll();
    		 System.out.println("user"+user);
           System.out.println("list of payment "+listpay);
    		
    		for(PaymentDetails pay1:listpay) {
    			if(user.getEmail().equals(pay1.getEmail())){
    				payment.add(pay1);
    				
    			}
    		}
    		ObjectMapper objectMapper = new ObjectMapper();
    		String json=null;
    		try {
    			json=objectMapper.writeValueAsString(payment);
    		}
    		catch(JsonProcessingException e) {
    			e.printStackTrace();
    		}
    		return new ResponseEntity<>(json ,HttpStatus.OK);
    	}
        
        
        public Userdetails getUserById(String id) {
        	RestTemplate restTemplate =new RestTemplate();
        	
        	 HttpHeaders headers = new HttpHeaders();
     	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
     	    
     	    String url = "http://localhost:8181/user/allusers/"+id;
     	    
     	    ResponseEntity<Userdetails> response = restTemplate.exchange(
     	            url, HttpMethod.GET, null, Userdetails.class);

     	 
     	    Userdetails userResponse = response.getBody();
     	    
     	    return userResponse;
        	
        }
        
        
        
        
}
   


   
        
       
    
    

 
    

 