package com.example.springboot_backend.service;

import com.example.springboot_backend.model.Purchase;
import com.example.springboot_backend.model.User;
import com.example.springboot_backend.model.Song;
import com.example.springboot_backend.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    public List<Purchase> getAllPurchases() {
        return purchaseRepository.findAll();
    }

    public Optional<Purchase> getPurchaseById(int id) {
        return purchaseRepository.findById(id);
    }

    public List<Purchase> getPurchasesByUser(User user) {
        return purchaseRepository.findByUser(user);
    }

    public List<Purchase> getPurchasesBySong(Song song) {
        return purchaseRepository.findBySong(song);
    }

    public List<Purchase> getPurchasesByUserAndSong(User user, Song song) {
        return purchaseRepository.findByUserAndSong(user, song);
    }

    public List<Purchase> getPurchasesByPurchaseDate(LocalDateTime purchaseDate) {
        return purchaseRepository.findByPurchaseDate(purchaseDate);
    }

    public List<Purchase> getPurchasesByPricePaid(BigDecimal pricePaid) {
        return purchaseRepository.findByPricePaid(pricePaid);
    }

    public Purchase savePurchase(Purchase purchase) {
        return purchaseRepository.save(purchase);
    }

    public void updatePurchase(int id, Purchase updatedPurchase) {
        Optional<Purchase> existingPurchase = purchaseRepository.findById(id);
        if (existingPurchase.isPresent()) {
            Purchase purchase = existingPurchase.get();
            purchase.setUser(updatedPurchase.getUser());
            purchase.setSong(updatedPurchase.getSong());
            purchase.setPurchaseDate(updatedPurchase.getPurchaseDate());
            purchase.setPricePaid(updatedPurchase.getPricePaid());
            purchaseRepository.save(purchase);
        }
    }

    public void deletePurchase(int id) {
        purchaseRepository.deleteById(id);
    }
}
