package OnDemandCarWashSystem.washerservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import OnDemandCarWashSystem.washerservice.model.WasherDetails;
import OnDemandCarWashSystem.washerservice.repo.WasherRepository;

@Service
public class WasherService {

    @Autowired
    private WasherRepository repository;

    public WasherDetails addWasher(WasherDetails washer) {
        return repository.save(washer);
    }

    public List<WasherDetails> getWashers() {
        List<WasherDetails> washers = repository.findAll();
        System.out.println("Getting data from DB: " + washers);
        return washers;
    }

    public List<WasherDetails> getWasherByLocation(String location) {
        return repository.findByLocation(location);
    }

    public void deleteWasher(WasherDetails washer) {
        repository.delete(washer);
    }
    
   

    public boolean existsWasher(String id) {
        return repository.existsById(id);
    }

    public void deleteWasher(String id) {
        repository.deleteById(id);
    }

	public Object getAllRatings() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
