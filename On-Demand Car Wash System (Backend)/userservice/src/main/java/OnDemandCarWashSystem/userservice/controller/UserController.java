package OnDemandCarWashSystem.userservice.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

//import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import OnDemandCarWashSystem.userservice.exception.ApiRequestException;
import OnDemandCarWashSystem.userservice.model.AdminDetails;
import OnDemandCarWashSystem.userservice.model.AuthenticationRequest;
import OnDemandCarWashSystem.userservice.model.Booking;
import OnDemandCarWashSystem.userservice.model.PaymentDetails;
import OnDemandCarWashSystem.userservice.model.Ratings;
import OnDemandCarWashSystem.userservice.model.Subscription;
import OnDemandCarWashSystem.userservice.model.Userdetails;
import OnDemandCarWashSystem.userservice.repo.AdminRepository;
import OnDemandCarWashSystem.userservice.repo.BookingRepository;
import OnDemandCarWashSystem.userservice.repo.SubscriptionRepository;
import OnDemandCarWashSystem.userservice.repo.UserRepository;
import OnDemandCarWashSystem.userservice.service.UserDetailsServiceImpl;
import OnDemandCarWashSystem.userservice.service.UserService;
import OnDemandCarWashSystem.userservice.util.JwtUtil;
import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/user")
@CrossOrigin("http://localhost:3000")
public class UserController {
	
	@Autowired
	private UserRepository repo;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
    private  BookingRepository bookingRepository;
	
	
	
	@Autowired
	private  SubscriptionRepository subscriptionRepository;
	
	@Autowired
    private AdminRepository adminRepository;
	
	


	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody AuthenticationRequest authRequest) {
		
		
		
	    Userdetails foundUser = repo.findByEmail(authRequest.getEmail());
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    
	    String url = "http://localhost:9090/admin/getUser/"+authRequest.getEmail()+"/"+ authRequest.getPassword();
	    ResponseEntity<AdminDetails> response = restTemplate.exchange(
	            url, HttpMethod.GET, null, AdminDetails.class);

	 
	    AdminDetails adminResponse = response.getBody();
	    
	
	    System.out.println(adminResponse);
	    
	    
	    

	    if (foundUser != null && foundUser.getPassword().equals(authRequest.getPassword())) {
	        // User login successful
	        String token = jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(foundUser.getEmail()));
	        JSONObject response1 = new JSONObject();
	        response1.put("userId", foundUser.getId());
	        response1.put("role", "user");
	        System.out.println(response1);
	        return new ResponseEntity<>(response1.toString(), HttpStatus.OK);
	    } else if (adminResponse != null) {
	        
	            // Admin login successful
//	            String token = jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(adminResponse.getEmail()));
	            JSONObject response2 = new JSONObject();
	            response2.put("adminId", adminResponse.getId());
	            response2.put("role", "admin");
	            response2.put("message", "Admin login successful");
	            System.out.println(response2);
	            return new ResponseEntity<>(response2.toString(), HttpStatus.OK);
	        
	    }
         else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	    }
		
	}

	private boolean isAdminEmail(String email) {
	    
	    return email.equals("kushwahamannu311@gmail.com")
	            || email.equals("gouravkumar123@gmail.com")
	            || email.equals("VeerendraSaini123@gmail.com");
	}

	
	
	

	


	

	 @PostMapping(value ="/adduser")
	 public Userdetails saveUser(@Valid @RequestBody Userdetails user) {
	 	return service.addUser(user);
	 }
 
	 @GetMapping("/allusers")
	 public List<Userdetails> findAllUsers() {
	 	return service.getUsers();
	 }
	
	 @PutMapping("/update/{id}")
	 public ResponseEntity<Object> updateuser(@PathVariable String id,  @RequestBody Userdetails user )
	 {
	 	 boolean isUserExist=repo.existsById(id);
		 if(isUserExist) {
		 	repo.save(user);
		    	return new ResponseEntity<Object>("user Updated Successfully with id "+id,HttpStatus.OK);
		 }
		 else
		 {
			 throw new ApiRequestException("CAN NOT UPDATE AS USER NOT FOUND WITH THIS ID ::");
		 }
	 }

	 
	 
	 @GetMapping("/allusers/{id}")
	 public ResponseEntity<Userdetails> getUser(@PathVariable String id) {
	     java.util.Optional<Userdetails> userOptional = repo.findById(id);
	     
	     if (userOptional.isPresent()) {
	         Userdetails user = userOptional.get();
	         System.out.println(user);
	         return ResponseEntity.ok(user);
	     } else {
	         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with this ID");
	     }
	 }

	 
	 
	
	 @DeleteMapping("/delete/{id}")
	 public ResponseEntity<Object> deleteuser(@PathVariable String id)
	 {
		 boolean isUserExist=repo.existsById(id);
		 if(isUserExist) {
			 service.deleteById(id);
			 return new ResponseEntity<Object>("user deleted with id "+id,HttpStatus.OK);
		 }
		 else
		 {
		 	throw new ApiRequestException("CAN NOT DELETE AS USER NOT FOUND WITH THIS ID ::");
		 }
	 }
	
	 
	 
	 

	 @PostMapping("/payment")
	 public String payment(@RequestBody PaymentDetails payment) {
		  HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<PaymentDetails> entity = new HttpEntity<PaymentDetails>(payment,headers);
	      
	      return restTemplate.exchange(
	          "http://localhost:9003/payment/", HttpMethod.POST, entity, String.class).getBody();
	   }
		
		@PostMapping("/addrating")
		  public String addrating(@RequestBody Ratings rating) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Ratings> entity = new HttpEntity<Ratings>(rating,headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:9090/admin/addrating", HttpMethod.POST, entity, String.class).getBody();
	    }
		
	
		 
		 @GetMapping("AllBooking")
		    public List<Booking> getAllBookings() {
		        return bookingRepository.findAll();
		    }
		 

		 
		 @PostMapping("/AddBooking")
		 public String createBooking(@RequestBody Booking booking) {
		     Userdetails user = repo.findById(booking.getBookingId()).orElse(null);
		     if (user != null) {
		         Subscription subscription = subscriptionRepository.findByEmail(user.getEmail());
		         if (subscription != null) {
		             booking.setPaymentRequired(false); // No payment required
		         } else {
		             booking.setPaymentRequired(true); // Payment required
		         }
		     }
		     bookingRepository.save(booking);
		     return "Booking successful! We will contact you soon.";
		 }
		 
		 @GetMapping("/AllBooking/{id}")
		 public ResponseEntity<String> getBookingById(@PathVariable("id") String id) {
//		     Booking book = null;
			 List<Booking>book=new ArrayList<>();
		     Userdetails user = getUserById(id);
		     if (user != null) {
		         List<Booking> listBooking = bookingRepository.findAll();
		         for (Booking book1 : listBooking) {
		             if (user.getEmail().equals(book1.getEmail())) {
		                book.add(book1);
		             }
		         }
		     }

		     if (book != null) {
		         ObjectMapper objectMapper = new ObjectMapper();
		         String json = null;
		         try {
		             json = objectMapper.writeValueAsString(book);
		         } catch (JsonProcessingException e) {
		             e.printStackTrace();
		         }
		         return new ResponseEntity<>(json, HttpStatus.OK);
		     } else {
		         return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
		     }
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

		 
		 

		 
		    
		    @DeleteMapping("/CancelBooking/{bookingId}")
		    public ResponseEntity<String> cancelBooking(@PathVariable("bookingId") String bookingId) {
		      try {
		        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);
		        if (optionalBooking.isPresent()) {
		          Booking booking = optionalBooking.get();
		          bookingRepository.delete(booking);
		          return new ResponseEntity<>("Booking canceled successfully", HttpStatus.OK);
		        } else {
		          return new ResponseEntity<>("Booking not found", HttpStatus.NOT_FOUND);
		        }
		      } catch (Exception e) {
		        return new ResponseEntity<>("Failed to cancel booking", HttpStatus.INTERNAL_SERVER_ERROR);
		      }
		    }

		    

		    
		    @GetMapping("/allSubscription")
		    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
		        List<Subscription> subscriptions = subscriptionRepository.findAll();
		        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
		    }

		    @GetMapping("/allSubscription/{id}")
		    public ResponseEntity<String> getSubscriptionById(@PathVariable("id") String id) {
//		    	Subscription sub=null;
		    	List<Subscription>sub=new ArrayList<>();
		        Userdetails user=getUserById(id);
		        List<Subscription>listSub=subscriptionRepository.findAll();
		        
		        
		        for(Subscription sub1:listSub) {
		        	if(user.getEmail().equals(sub1.getEmail())) {
		        		sub.add(sub1);
		        	}
		        }
		        ObjectMapper objectMapper = new ObjectMapper();
			    String json = null;
				try {
					json = objectMapper.writeValueAsString(sub);
				} catch (JsonProcessingException e) {
					
					e.printStackTrace();
				}
			    
			    return new ResponseEntity<>(json,HttpStatus.OK);
		    }
		    
		    

		    @PostMapping("/addSubscription")
		    public ResponseEntity<String> createSubscription(@RequestBody Subscription subscription) {
		        try {
		            Subscription savedSubscription = subscriptionRepository.save(subscription);
		            return new ResponseEntity<>("Subscription created successfully", HttpStatus.CREATED);
		        } catch (Exception e) {
		            return new ResponseEntity<>("Failed to create subscription", HttpStatus.INTERNAL_SERVER_ERROR);
		        }
		    }
		    
		    @DeleteMapping("/CancelSubscription/{subscriptionId}")
		    public ResponseEntity<String> cancelSubscription(@PathVariable("subscriptionId") String subscriptionId) {
		      try {
		        Optional<Subscription> optionalSubscription = subscriptionRepository.findById(subscriptionId);
		        if (optionalSubscription.isPresent()) {
		          Subscription subscription = optionalSubscription.get();
		          subscriptionRepository.delete(subscription);
		          return new ResponseEntity<>("Subscription canceled successfully", HttpStatus.OK);
		        } else {
		          return new ResponseEntity<>("Subscription not found", HttpStatus.NOT_FOUND);
		        }
		      } catch (Exception e) {
		        return new ResponseEntity<>("Failed to cancel subscription", HttpStatus.INTERNAL_SERVER_ERROR);
		      }
		    }
		    
		    @GetMapping("/foundSubscription/{email}")
		    public String checkEmail(@PathVariable String email){
		    	List<Subscription>listSubscription=subscriptionRepository.findAll();
		    	for(Subscription sub1:listSubscription) {
		    		if(sub1.getEmail().equals(email)) {
		    			return "found";
		    		}
		    	}
		    	
		    	return "Not found";
		    }
		    
		    




//		    @GetMapping("/allpacks")
//		    public List<WashPacks> getwashpacks() {
//		        String baseurl = "http://localhost:9090/admin/allpacks";
//		        ResponseEntity<WashPacks[]> response = restTemplate.getForEntity(baseurl, WashPacks[].class);
//		        if (response.getStatusCode() == HttpStatus.OK) {
//		            WashPacks[] washPacks = response.getBody();
//		            return Arrays.asList(washPacks);
//		        } else {
//		            throw new ApiRequestException("Failed to retrieve wash packs from admin service");
//		        }
//		    }



//		    @PostMapping("/addadmin")
//			public String saveadmin(@RequestBody AdminDetails admin) {
//			    HttpHeaders headers = new HttpHeaders();
//			    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//			    HttpEntity<AdminDetails> entity = new HttpEntity<>(admin, headers);
//
//			    return restTemplate.exchange(
//			            "http://localhost:9090/admin/addadmin", HttpMethod.POST, entity, String.class).getBody();
//			}

			




//			@GetMapping("/AllSubscription")
//		    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
//		        List<Subscription> subscriptions = service.getAllSubscriptions();
//		        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
//		    }
//
//		    @PostMapping("/AddSubscription")
//		    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
//		        Subscription createdSubscription = service.createSubscription(subscription);
//		        return new ResponseEntity<>(createdSubscription, HttpStatus.CREATED);
//		    }
		    
		    
}


//		    @PostMapping("/authenticate")
//			public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		//
//				try {
//					authenticationManager.authenticate(
//							new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//					);
//				}
//				catch (BadCredentialsException e) {
//					throw new Exception("Incorrect username or password", e);
//				}
		//
		//
//				final UserDetails userDetails = userDetailsService
//						.loadUserByUsername(authenticationRequest.getUsername());
		//
//				final String jwt = jwtTokenUtil.generateToken(userDetails);
		//
//				return ResponseEntity.ok(new AuthenticationResponse(jwt));
//			 }

//      @PostMapping("/addorder")
//      public String addorder(@RequestBody OrderDetails order) {
//      HttpHeaders headers = new HttpHeaders();
//      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//      HttpEntity<OrderDetails> entity = new HttpEntity<OrderDetails>(order,headers); 
//      return restTemplate.exchange(
//      "http://localhost:9002/addorder", HttpMethod.POST, entity, String.class).getBody();
//}
//
//
//    @DeleteMapping("/cancelorder")
//    public String deleteorder(){
//	  String baseurl="http://localhost:9002/delete";
//	  OrderDetails order=restTemplate.getForObject(baseurl, OrderDetails.class);
//	  return "Your Order is successfully Canceled "+order;
//}
		

//ye mene likha hai chal rha hai
//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestBody Userdetails user) {
//	    Userdetails existingUser = repo.findByEmail(user.getEmail());
//	    if (existingUser != null && passwordMatches(user.getPassword(), existingUser.getPassword())) {
//	        return ResponseEntity.ok("Login successful! User ID: " + existingUser.getId());
//	    } else {
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
//	    }
//	}
//
//	private boolean passwordMatches(String inputPassword, String existingPassword) {
//	    
//	    return inputPassword.equals(existingPassword);
//	}
//	
//	
//
//
//
//	
// 
// 
// @PostMapping("/signup")
// public ResponseEntity<String> signUp(@RequestBody User user) {
//    
//     if (userSignUpRepository.findByEmail(user.getEmail()) != null) {
//         return new ResponseEntity<>("Email already registered", HttpStatus.BAD_REQUEST);
//     }
//
//     userSignUpRepository.save(user);
//
//     return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
// }
// 
 
 // ye tune likh baya tha 
 
// @PostMapping("/login")
// public ResponseEntity<String> login1(@RequestBody User user) {
//    User user1=userSignUpRepository.findByEmail(user.getEmail());
//    if(user1==null  ) {
// 	   return new ResponseEntity<>( "Invalid credentials",HttpStatus.NOT_FOUND);
// 	  
//    }
//    
//    else if ( user1.getEmail().equals(user.getEmail())&& user1.getPassword().equals(user.getPassword())){
// 
// 	   return new ResponseEntity<>(  user1.getId(),HttpStatus.OK);
//    }
//    else {
// 	   return new ResponseEntity<>("Email and password is wrong",HttpStatus.BAD_REQUEST);
//    }
//    
// }
     
//     
// @GetMapping("/allSignUpUsers")
// public ResponseEntity<List<User>> getAllSignUpUsers() {
//     List<User> users = userSignUpRepository.findAll();
//     if (users.isEmpty()) {
//         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//     }
//     return new ResponseEntity<>(users, HttpStatus.OK);
// }
	
	
//	@PostMapping("/login")
//		public ResponseEntity<String>login(@Valid @RequestBody Userdetails user){
//		  Userdetails user1=repo.findByEmail(user.getEmail());
//		  if(user1==null) {
//			  return new ResponseEntity<>("Invalid credentials",HttpStatus.NOT_FOUND);
//			  
//		  }
//		  else if(user1.getEmail().equals(user.getEmail()) && user1.getPassword().equals(user.getPassword())) {
//			  return new ResponseEntity<>("Login Successful User Id : "+ user1.getId(),HttpStatus.OK);
//		  }
//		  else {
//			  return new ResponseEntity<>("Email and password is wrong",HttpStatus.BAD_REQUEST);
//		  }
//	}















//@PostMapping("/login")
//public ResponseEntity<Object> login(HttpServletRequest request,@Valid @RequestBody Userdetails user) {
//  Userdetails user1 = repo.findByEmail(user.getEmail());
//  if (user1 == null) {
//    return new ResponseEntity<>("Invalid credentials", HttpStatus.NOT_FOUND);
//  } else if (user1.getEmail().equals(user.getEmail()) && user1.getPassword().equals(user.getPassword())) {
//    
//    JSONObject response = new JSONObject();
//    response.put("userId", user1.getId());
//
//    return new ResponseEntity<>(response.toString(), HttpStatus.OK);
//  } else {
//    return new ResponseEntity<>("Email and password are wrong", HttpStatus.BAD_REQUEST);
//  }