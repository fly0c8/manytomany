package com.example.demo.repository;

import com.example.demo.model.ObstacleSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ObstacleSetRepository extends JpaRepository<ObstacleSet, UUID> {
}
