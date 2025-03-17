package com.example.springboot_backend.repository;

import com.example.springboot_backend.model.Download;
import com.example.springboot_backend.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DownloadRepository extends JpaRepository<Download, Integer> {

    List<Download> findBySong(Song song); // Matches Song naming style

    List<Download> findByName(String name); // Matches naming style for queries
}
