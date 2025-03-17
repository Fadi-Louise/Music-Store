package com.example.springboot_backend.service;

import com.example.springboot_backend.model.Download;
import com.example.springboot_backend.model.Song;
import com.example.springboot_backend.repository.DownloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DownloadService {

    private final DownloadRepository downloadRepository;

    @Autowired
    public DownloadService(DownloadRepository downloadRepository) {
        this.downloadRepository = downloadRepository;
    }

    public List<Download> getAllDownloads() {
        return downloadRepository.findAll();
    }

    public Optional<Download> getDownloadById(Integer id) {
        return downloadRepository.findById(id);
    }

    public List<Download> getDownloadsBySong(Song song) {
        return downloadRepository.findBySong(song);
    }

    public Download createDownload(Download download) {
        return downloadRepository.save(download);
    }

    public Download updateDownload(Integer id, Download updatedDownload) {
        Optional<Download> existingDownload = downloadRepository.findById(id);
        if (existingDownload.isPresent()) {
            updatedDownload.setDownloadId(id);
            return downloadRepository.save(updatedDownload);
        } else {
            throw new IllegalArgumentException("Download with ID " + id + " not found.");
        }
    }

    public void deleteDownload(Integer id) {
        downloadRepository.deleteById(id);
    }
}
