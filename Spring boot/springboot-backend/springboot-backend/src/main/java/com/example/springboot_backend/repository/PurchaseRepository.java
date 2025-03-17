package com.example.springboot_backend.repository;

import com.example.springboot_backend.model.Purchase;
import com.example.springboot_backend.model.User;
import com.example.springboot_backend.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findByUser(User user);
    List<Purchase> findBySong(Song song);
    List<Purchase> findByUserAndSong(User user, Song song);
    List<Purchase> findByPurchaseDate(LocalDateTime purchaseDate);
    List<Purchase> findByPricePaid(BigDecimal pricePaid);
}
