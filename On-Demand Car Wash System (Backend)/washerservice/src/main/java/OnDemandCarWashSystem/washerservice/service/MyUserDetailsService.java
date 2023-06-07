package OnDemandCarWashSystem.washerservice.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import OnDemandCarWashSystem.washerservice.model.WasherDetails;
import OnDemandCarWashSystem.washerservice.repo.WasherRepository;


@Service
public class MyUserDetailsService implements UserDetailsService {
    
    @Autowired
    private WasherRepository repository;

//    @Override
//    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
//       WasherDetails washer=repository.findByName(name);
//       if(washer==null){
//           throw new UsernameNotFoundException("User not found with username: "+name);
//       }
//       return new User(washer.getName(),washer.getPassword(),new ArrayList<>());
//    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        WasherDetails washer = repository.findByName(username);
        if (washer == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(
            washer.getName(), washer.getPassword(), new ArrayList<>()
        );
    }
}