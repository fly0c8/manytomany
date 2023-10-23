package com.example.demo.repository;

import com.example.demo.model.ObstacleSet;
import com.example.demo.model.ObstacleSetObstacle;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ObstacleSetObstacleRepository extends JpaRepository<ObstacleSetObstacle, UUID> {

}
