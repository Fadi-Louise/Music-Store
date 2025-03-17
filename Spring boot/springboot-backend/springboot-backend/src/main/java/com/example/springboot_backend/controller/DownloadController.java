package com.example.springboot_backend.controller;

import com.example.springboot_backend.model.Download;
import com.example.springboot_backend.model.Song;
import com.example.springboot_backend.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/downloads")
public class DownloadController {

    private final DownloadService downloadService;

    @Autowired
    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    @GetMapping
    public ResponseEntity<List<Download>> getAllDownloads() {
        List<Download> downloads = downloadService.getAllDownloads();
        return new ResponseEntity<>(downloads, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Download> getDownloadById(@PathVariable("id") Integer id) {
        Optional<Download> download = downloadService.getDownloadById(id);
        return download.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Download> createDownload(@RequestBody Download download) {
        Download createdDownload = downloadService.createDownload(download);
        return new ResponseEntity<>(createdDownload, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Download> updateDownload(@PathVariable("id") Integer id, @RequestBody Download download) {
        try {
            Download updatedDownload = downloadService.updateDownload(id, download);
            return new ResponseEntity<>(updatedDownload, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDownload(@PathVariable("id") Integer id) {
        downloadService.deleteDownload(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
