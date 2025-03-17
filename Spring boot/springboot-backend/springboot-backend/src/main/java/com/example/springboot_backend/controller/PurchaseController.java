package com.example.springboot_backend.controller;

import com.example.springboot_backend.model.Purchase;
import com.example.springboot_backend.model.Song;
import com.example.springboot_backend.model.User;
import com.example.springboot_backend.service.PurchaseService;
import com.example.springboot_backend.service.SongService;
import com.example.springboot_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;
    private final UserService userService;
    private final SongService songService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService, UserService userService, SongService songService) {
        this.purchaseService = purchaseService;
        this.userService = userService;
        this.songService = songService;
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        List<Purchase> purchases = purchaseService.getAllPurchases();
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Purchase> getPurchaseById(@PathVariable int id) {
        Optional<Purchase> purchase = purchaseService.getPurchaseById(id);
        return purchase.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Purchase>> getPurchasesByUserId(@PathVariable int userId) {
        User user = userService.getUserById(userId).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Purchase> purchases = purchaseService.getPurchasesByUser(user);
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }

    @GetMapping("/song/{songId}")
    public ResponseEntity<List<Purchase>> getPurchasesBySongId(@PathVariable int songId) {
        Song song = songService.getSongById(songId).orElse(null);
        if (song == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Purchase> purchases = purchaseService.getPurchasesBySong(song);
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}/song/{songId}")
    public ResponseEntity<List<Purchase>> getPurchasesByUserIdAndSongId(@PathVariable int userId, @PathVariable int songId) {
        User user = userService.getUserById(userId).orElse(null);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Song song = songService.getSongById(songId).orElse(null);
        if (song == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Purchase> purchases = purchaseService.getPurchasesByUserAndSong(user, song);
        return new ResponseEntity<>(purchases, HttpStatus.OK);
    }

    @GetMapping("/purchase-date/{purchaseDate}")
    public ResponseEntity<List<Purchase>> getPurchasesByPurchaseDate(@PathVariable LocalDateTime purchaseDate) {
        List<Purchase> purchases = purchaseService.getPurchasesByPurchaseDate(purchaseDate);
        return purchases.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(purchases, HttpStatus.OK);
    }

    @GetMapping("/price-paid/{pricePaid}")
    public ResponseEntity<List<Purchase>> getPurchasesByPricePaid(@PathVariable BigDecimal pricePaid) {
        List<Purchase> purchases = purchaseService.getPurchasesByPricePaid(pricePaid);
        return purchases.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(purchases, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Purchase> savePurchase(@Valid @RequestBody Purchase purchase) {
        Purchase savedPurchase = purchaseService.savePurchase(purchase);
        return new ResponseEntity<>(savedPurchase, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePurchase(@PathVariable int id, @Valid @RequestBody Purchase updatedPurchase) {
        Optional<Purchase> existingPurchase = purchaseService.getPurchaseById(id);
        if (existingPurchase.isEmpty()) {
            return new ResponseEntity<>("Purchase not found", HttpStatus.NOT_FOUND);
        }
        purchaseService.updatePurchase(id, updatedPurchase);
        return new ResponseEntity<>("Purchase updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePurchase(@PathVariable int id) {
        Optional<Purchase> purchase = purchaseService.getPurchaseById(id);
        if (purchase.isEmpty()) {
            return new ResponseEntity<>("Purchase not found", HttpStatus.NOT_FOUND);
        }
        purchaseService.deletePurchase(id);
        return new ResponseEntity<>("Purchase deleted successfully", HttpStatus.OK);
    }
}
