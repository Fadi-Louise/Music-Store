package com.example.springboot_backend.service;

import com.example.springboot_backend.model.UserDownload;
import com.example.springboot_backend.repository.UserDownloadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserDownloadService {

    @Autowired
    private UserDownloadRepository userDownloadRepository;

    // Method to find UserDownload by userID and downloadId
    public Optional<UserDownload> findByUserIDAndDownloadId(Integer userID, Integer downloadId) {
        return userDownloadRepository.findByUser_UserIDAndDownload_DownloadId(userID, downloadId);
    }

    // Method to save a new UserDownload
    public UserDownload save(UserDownload userDownload) {
        return userDownloadRepository.save(userDownload);
    }

    // Method to delete UserDownload by userID and downloadId
    public void deleteByUserIDAndDownloadId(Integer userID, Integer downloadId) {
        Optional<UserDownload> userDownload = findByUserIDAndDownloadId(userID, downloadId);
        userDownload.ifPresent(userDownloadRepository::delete);
    }
    public List<UserDownload> getAllUserDownloads() {
        return userDownloadRepository.findAll();
    }

}
