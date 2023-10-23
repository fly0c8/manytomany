package com.example.demo.runner;

import com.example.demo.model.Obstacle;
import com.example.demo.model.ObstacleSet;
import com.example.demo.model.ObstacleSetObstacle;
import com.example.demo.repository.ObstacleSetObstacleRepository;
import com.example.demo.repository.ObstacleRepository;
import com.example.demo.repository.ObstacleSetRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class MyRunner implements CommandLineRunner {


    @Autowired
    ObstacleSetObstacleRepository obstacleSetObstacleRepository;

    @Autowired
    ObstacleSetRepository obstacleSetRepository;

    @Autowired
    ObstacleRepository obstacleRepository;

    @Override



    public void run(String... args) throws Exception {

        createAll();
        var obstacle = obstacleRepository.findObstacleByName("twr");
        if (obstacle.isPresent()) {
            System.out.println("BEFORE DELETE");
            obstacleSetObstacleRepository.findAll().forEach(oso -> {
                System.out.println(oso.getUuid() +" " + oso.getObstacle().getUuid() +" "+ oso.getObstacleSet().getUuid() +" "+ oso.getDistance());
            });
            System.out.println("DELETING Obstacle "+obstacle.get().getUuid());
            deleteAll(obstacle.get().getUuid());
            System.out.println("AFTER DELETE");
            obstacleSetObstacleRepository.findAll().forEach(oso -> {
                System.out.println(oso.getUuid() +" " + oso.getObstacle().getUuid() +" "+ oso.getObstacleSet().getUuid() +" "+ oso.getDistance());
            });
            System.out.println("***");
        }

    }

    @Transactional
    public void deleteAll(UUID obstacleUUID) {
        obstacleSetObstacleRepository.deleteAll();
    }

    @Transactional
    public void createAll() {
        System.out.println("*** my runner runs! ***");
        var defaultSet = ObstacleSet.builder()
                .name("default")
                .build();

        var widebodies = ObstacleSet.builder().name("widebodies").build();

        var twr = Obstacle.builder()
                .name("twr")
                .height(25)
                .build();

        var pole = Obstacle.builder()
                .name("pole")
                .height(50)
                .build();

        var sender = Obstacle.builder().name("sender").height(500).build();


        obstacleSetRepository.saveAll(List.of(defaultSet, widebodies));
        obstacleRepository.saveAll(List.of(twr, pole, sender));

        var assoc = List.of(
                new ObstacleSetObstacle(defaultSet, twr),
                new ObstacleSetObstacle(defaultSet, pole),
                new ObstacleSetObstacle(widebodies, pole),
                new ObstacleSetObstacle(widebodies, sender)
        );
        obstacleSetObstacleRepository.saveAll(assoc);
    }

}
