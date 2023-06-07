package OnDemandCarWashSystem.adminservice.controller;

import java.util.List;
import java.util.Optional;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import OnDemandCarWashSystem.adminservice.model.AdminDetails;
import OnDemandCarWashSystem.adminservice.model.Ratings;
import OnDemandCarWashSystem.adminservice.repo.AdminRepository;
import OnDemandCarWashSystem.adminservice.repo.RatingRepository;



@RestController
@RequestMapping("/admin/")
@CrossOrigin("http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    

    @Autowired
    private RatingRepository ratingRepository;

  
    
    @PostMapping("/login")
     public ResponseEntity<String>login(@RequestBody AdminDetails admin){
    	AdminDetails admindetails =adminRepository.findByEmail(admin.getEmail());
    	if(admindetails!=null && admin.getPassword().equals(admin.getPassword())) {
    		   return ResponseEntity.ok("Login Successful");
    	}
    	else {
    		return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body("Invalid username or password");
    	}
    }
    
    
    

    
    

    @PostMapping("/addadmin")
    public String saveadmin(@RequestBody AdminDetails admin) {
        adminRepository.save(admin);
        return "Admin saved successfully with id: " + admin.getId();
    }

    @GetMapping("/alladmins")
    public List<AdminDetails> getadmin() {
        return adminRepository.findAll();
    }

    @GetMapping("/alladmins/{id}")
    public Optional<AdminDetails> getadmin(@PathVariable String id) {
        return adminRepository.findById(id);
    }

    @DeleteMapping("/deleteadmin/{id}")
    public String deleteAdmin(@PathVariable String id) {
        try {
            adminRepository.deleteById(id);
            return "Admin with ID: " + id + " deleted successfully.";
        } catch (Exception e) {
            return "Error deleting admin with ID: " + id;
        }
    }

    

    
    @GetMapping("/allratings")
    public List<Ratings> getAllRatings() {
        return ratingRepository.findAll();
    }

    @PostMapping("/addrating")
    public Ratings submitRating(@RequestBody Ratings rating) {
        return ratingRepository.save(rating);
    }

    
    
    
    @PostMapping("/addwasher")
    public String addWasher() {
        String baseUrl = "http://localhost:9004/addwasher";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = null;
        try {
            response = restTemplate.exchange(baseUrl, HttpMethod.POST, getHeaders(), String.class);
            if (response != null && response.getBody() != null) {
                System.out.println(response.getBody());
                return response.getBody();
            } else {
               
                return "Error: Null response body";
            }
        } catch (Exception ex) {
            System.out.println(ex);
            return "Error: " + ex.getMessage();
        }
    }

    private HttpEntity<?> getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(headers);
    }
    
    @GetMapping("/getUser/{email}/{password}")
    public AdminDetails getAdminByEmail(@PathVariable String email,@PathVariable String password) {
    	return adminRepository.findByEmailAndPassword(email,password);
    }

}




