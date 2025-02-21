package com.examly.toystore.service;
import org.springframework.data.domain.Page;
import com.examly.toystore.model.Toy;
import com.examly.toystore.repository.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ToyService {

    @Autowired
    private ToyRepository toyRepository;

    public Page<Toy> getAllToys(int page, int size) {
        return toyRepository.findAll(PageRequest.of(page, size));
    }
    

    public Toy getToyById(Long id) {
        return toyRepository.findById(id).orElse(null);
    }
    
    public List<Toy> saveAllToys(List<Toy> toys) {
        return toyRepository.saveAll(toys);
    }
    

    public void deleteToy(Long id) {
        toyRepository.deleteById(id);
    }
    public void deleteAllToys() {
        toyRepository.deleteAll();
    }

    public Toy updateToy(Long id, Toy updatedToy) {
        return toyRepository.findById(id).map(toy -> {
            toy.setName(updatedToy.getName());
            toy.setPrice(updatedToy.getPrice());
            toy.setCategory(updatedToy.getCategory());
            return toyRepository.save(toy);
        }).orElse(null);
    }
    public Toy saveToy(Toy toy) {
        return toyRepository.save(toy);
    }
    
    public List<Toy> searchToys(String keyword) {
        return toyRepository.searchByName(keyword);
    }
}
