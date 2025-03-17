package com.example.springboot_backend.controller;

import com.example.springboot_backend.model.UserDownload;
import com.example.springboot_backend.service.UserDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user-downloads")
public class UserDownloadController {

    private final UserDownloadService userDownloadService;

    @Autowired
    public UserDownloadController(UserDownloadService userDownloadService) {
        this.userDownloadService = userDownloadService;
    }

    // Endpoint to get all user downloads
    @GetMapping
    public ResponseEntity<List<UserDownload>> getAllUserDownloads() {
        List<UserDownload> userDownloads = userDownloadService.getAllUserDownloads();
        return new ResponseEntity<>(userDownloads, HttpStatus.OK);
    }

    // Endpoint to get a UserDownload by userID and downloadId
    @GetMapping("/{userID}/{downloadId}")
    public ResponseEntity<UserDownload> getUserDownload(@PathVariable Integer userID, @PathVariable Integer downloadId) {
        Optional<UserDownload> userDownload = userDownloadService.findByUserIDAndDownloadId(userID, downloadId);
        return userDownload.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint to create a new UserDownload
    @PostMapping
    public ResponseEntity<UserDownload> createUserDownload(@Valid @RequestBody UserDownload userDownload) {
        try {
            UserDownload savedUserDownload = userDownloadService.save(userDownload);
            return new ResponseEntity<>(savedUserDownload, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Endpoint to delete UserDownload by userID and downloadId
    @DeleteMapping("/{userID}/{downloadId}")
    public ResponseEntity<Void> deleteUserDownload(@PathVariable Integer userID, @PathVariable Integer downloadId) {
        userDownloadService.deleteByUserIDAndDownloadId(userID, downloadId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
