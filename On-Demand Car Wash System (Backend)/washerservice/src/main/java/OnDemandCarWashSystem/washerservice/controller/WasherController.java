package OnDemandCarWashSystem.washerservice.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import OnDemandCarWashSystem.washerservice.exception.ApiRequestException;
import OnDemandCarWashSystem.washerservice.model.AuthenticationRequest;
import OnDemandCarWashSystem.washerservice.model.AuthenticationResponse;
import OnDemandCarWashSystem.washerservice.model.Ratings;
import OnDemandCarWashSystem.washerservice.model.WasherDetails;
import OnDemandCarWashSystem.washerservice.repo.WasherRepository;
import OnDemandCarWashSystem.washerservice.service.MyUserDetailsService;
import OnDemandCarWashSystem.washerservice.service.WasherService;
import OnDemandCarWashSystem.washerservice.util.JwtUtil;

@RestController
@RequestMapping("/washer")
@CrossOrigin("http://localhost:3000")
public class WasherController {

    private final WasherService service;
    private final WasherRepository repo;
    private final RestTemplate restTemplate;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;
    

    public WasherController(WasherService service, WasherRepository repo, RestTemplate restTemplate) {
        this.service = service;
        this.repo = repo;
        this.restTemplate = restTemplate;
    }
    
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/addwasher")
    public WasherDetails saveWasher(@Valid @RequestBody WasherDetails washer) {
    	
    	return service.addWasher(washer);
    }

      

    @GetMapping("/allwashers")
    public List<WasherDetails> getAllWashers() {
        return service.getWashers();
    }
    
    
    @GetMapping("/allwashers/{location}")
    public List<WasherDetails> getWashersByLocation(@PathVariable String location) {
        return service.getWasherByLocation(location);
    }


    @GetMapping("/getwasher/{id}")
    public WasherDetails getWasherById(@PathVariable String id) {
        return repo.findById(id)
                .orElseThrow(() -> new ApiRequestException("Washer not found with ID: " + id));
    }
  
    @GetMapping("/allratings")
    public List<Ratings> getAllRatings() {
        String baseUrl = "http://localhost:9090/admin/allratings";
        Ratings[] allRatings = restTemplate.getForObject(baseUrl, Ratings[].class);
        return Arrays.asList(allRatings);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteWasherById(@PathVariable String id) {
      try {
        boolean isWasherExist = service.existsWasher(id);
        if (isWasherExist) {
          service.deleteWasher(id);
          return new ResponseEntity<>("Washer deleted with ID: " + id, HttpStatus.OK);
        } else {
          throw new ApiRequestException("Washer not found with ID: " + id);
        }
      } catch (Exception e) {
        throw new ApiRequestException("Error deleting washer with ID: " + id);
      }
    }




}
    
//    @GetMapping("/getwasher/{id}")
//  public WasherDetails getWasherById(@PathVariable String id) {
//      ObjectId objectId;
//      try {
//          objectId = new ObjectId(id);
//      } catch (IllegalArgumentException e) {
//          throw new ApiRequestException("Invalid washer ID format: " + id);
//      }
//      
//      return repo.findById(objectId)
//              .orElseThrow(() -> new ApiRequestException("Washer not found with ID: " + id));
//  }

//  @GetMapping("/allorders")
//  public List<OrderDetails> getAllOrders() {
//      String baseUrl = "http://localhost:9002/allorders";
//      OrderDetails[] allOrders = restTemplate.getForObject(baseUrl, OrderDetails[].class);
//      return Arrays.asList(allOrders);
//  }

