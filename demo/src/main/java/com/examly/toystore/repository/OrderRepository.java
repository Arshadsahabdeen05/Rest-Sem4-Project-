package com.examly.toystore.repository;

import com.examly.toystore.model.Category;
import com.examly.toystore.model.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE LOWER(o.customerName) LIKE LOWER(CONCAT('%', :customerName, '%'))")
    List<Order> findByCustomerNameLike(@Param("customerName") String customerName);

     @Query("SELECT c FROM Category c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Category> searchByKeyword(@Param("keyword") String keyword);
}
