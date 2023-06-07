package io.carwashsystem.washerservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import OnDemandCarWashSystem.washerservice.WasherserviceApplication;
import OnDemandCarWashSystem.washerservice.controller.WasherController;
import OnDemandCarWashSystem.washerservice.model.AuthenticationRequest;
import OnDemandCarWashSystem.washerservice.model.Ratings;
import OnDemandCarWashSystem.washerservice.model.WasherDetails;
import OnDemandCarWashSystem.washerservice.repo.WasherRepository;
import OnDemandCarWashSystem.washerservice.service.MyUserDetailsService;
import OnDemandCarWashSystem.washerservice.service.WasherService;
import OnDemandCarWashSystem.washerservice.util.JwtUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(WasherController.class)
@SpringJUnitConfig
@SpringBootTest(classes = WasherserviceApplication.class)
public class WasherserviceApplicationTests {

//    private MockMvc mockMvc;
//
//    @MockBean
//    private WasherRepository washerRepository;
//
//    @MockBean
//    private WasherService washerService;
//
//    @MockBean
//    private MyUserDetailsService userDetailsService;
//
//    @MockBean
//    private JwtUtil jwtUtil;
//
//    @InjectMocks
//    private WasherController washerController;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(washerController).build();
//    }
//
//    @Test
//    public void testCreateAuthenticationToken() throws Exception {
//        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
//        authenticationRequest.setUsername("admin");
//        authenticationRequest.setPassword("password");
//
//        when(userDetailsService.loadUserByUsername("admin")).thenReturn(any());
//        when(jwtUtil.generateToken(any())).thenReturn("token");
//
//        mockMvc.perform(post("/washer/authenticate")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"username\":\"admin\", \"password\":\"password\"}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.jwt").exists());
//    }
//
//    @Test
//    public void testSaveWasher() throws Exception {
//        WasherDetails washer = new WasherDetails();
//        washer.setName("Washer 1");
//        washer.setLocation("Location 1");
//        washer.setPassword("password");
//
//        when(washerService.addWasher(any())).thenReturn(washer);
//
//        mockMvc.perform(post("/washer/addwasher")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\"name\":\"Washer 1\", \"location\":\"Location 1\", \"password\":\"password\"}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name").value("Washer 1"))
//                .andExpect(jsonPath("$.location").value("Location 1"));
//    }
//
//    @Test
//    public void testGetAllWashers() throws Exception {
//        List<WasherDetails> washers = new ArrayList<>();
//        washers.add(new WasherDetails());
//        washers.add(new WasherDetails());
//
//        when(washerService.getWashers()).thenReturn(washers);
//
//        mockMvc.perform(get("/washer/allwashers"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2));
//    }
//
//    @Test
//    public void testGetWashersByLocation() throws Exception {
//        List<WasherDetails> washers = new ArrayList<>();
//        washers.add(new WasherDetails());
//        washers.add(new WasherDetails());
//
//        when(washerRepository.findByLocation("Location 1")).thenReturn(washers);
//
//        mockMvc.perform(get("/washer/allwashers/Location%201"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2));
//    }
//
//    @Test
//    public void testGetWasherById() throws Exception {
//        WasherDetails washer = new WasherDetails();
//        washer.setId("1");
//        washer.setName("Washer 1");
//        washer.setLocation("Location 1");
//
//        when(washerRepository.findById(new ObjectId("1"))).thenReturn(Optional.of(washer));
//
//        mockMvc.perform(get("/washer/getwasher/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value("1"))
//                .andExpect(jsonPath("$.name").value("Washer 1"))
//                .andExpect(jsonPath("$.location").value("Location 1"));
//    }
//
//    @Test
//    public void testGetAllRatings() throws Exception {
//        List<Ratings> ratings = new ArrayList<>();
//        ratings.add(new Ratings());
//        ratings.add(new Ratings());
//
//        when(washerService.getAllRatings()).thenReturn(ratings);
//
//        mockMvc.perform(get("/washer/allratings"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.length()").value(2));
//    }
//
//    @Test
//    public void testDeleteWasherById() throws Exception {
//        String id = "1";
//
//        when(washerService.existsWasher(id)).thenReturn(true);
//
//        mockMvc.perform(delete("/washer/delete/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().string("Washer deleted with ID: " + id));
//    }

   
}
