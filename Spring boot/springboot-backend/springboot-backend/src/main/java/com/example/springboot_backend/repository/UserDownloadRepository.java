package com.example.springboot_backend.repository;

import com.example.springboot_backend.model.UserDownload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDownloadRepository extends JpaRepository<UserDownload, Integer> {

    // Method to find a UserDownload by userID and downloadId
    Optional<UserDownload> findByUser_UserIDAndDownload_DownloadId(Integer userID, Integer downloadId);

}
