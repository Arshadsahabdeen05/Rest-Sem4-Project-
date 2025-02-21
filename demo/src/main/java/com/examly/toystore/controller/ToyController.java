package com.examly.toystore.controller;
import com.examly.toystore.model.Toy;
import com.examly.toystore.service.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toys")
public class ToyController {

    @Autowired
    private ToyService toyService;

    @GetMapping
    public List<Toy> getAllToys(@RequestParam(defaultValue = "0") int page, 
                                @RequestParam(defaultValue = "100") int size) {
        return toyService.getAllToys(page, size).getContent();
    }


    @GetMapping("/{id}")
    public Toy getToyById(@PathVariable Long id) {
        return toyService.getToyById(id);
    }

    @GetMapping("/search")
    public List<Toy> searchToys(@RequestParam String keyword) {
        return toyService.searchToys(keyword);
    }
    @PostMapping
public ResponseEntity<Toy> createToy(@RequestBody Toy toy) {
    return ResponseEntity.ok(toyService.saveToy(toy));
}


@PostMapping("/bulk")
public ResponseEntity<List<Toy>> createToys(@RequestBody List<Toy> toys) {
    List<Toy> savedToys = toyService.saveAllToys(toys);
    return ResponseEntity.ok(savedToys);
}


    @PutMapping("/{id}")
    public Toy updateToy(@PathVariable Long id, @RequestBody Toy updatedToy) {
        return toyService.updateToy(id, updatedToy);
    }

    @DeleteMapping("/{id}")
    public void deleteToy(@PathVariable Long id) {
        toyService.deleteToy(id);
    }
    @DeleteMapping("/all")
    public void deleteAllToys() {
        toyService.deleteAllToys();
    }
}
