package com.examly.toystore.repository;

import com.examly.toystore.model.Toy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ToyRepository extends JpaRepository<Toy, Long> {
    @Query("SELECT t FROM Toy t WHERE LOWER(t.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Toy> searchByName(String keyword);
}
