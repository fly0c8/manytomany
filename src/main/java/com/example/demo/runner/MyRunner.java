package com.example.demo.runner;

import com.example.demo.model.Obstacle;
import com.example.demo.model.ObstacleGroup;
import com.example.demo.model.ObstacleGroupObstacle;
import com.example.demo.repository.ObstacleGroupObstacleRepository;
import com.example.demo.repository.ObstacleGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    ObstacleGroupObstacleRepository obstacleGroupObstacleRepository;

    @Autowired
    ObstacleGroupRepository obstacleGroupRepository;


    @Override
    public void run(String... args) throws Exception {

        System.out.println("*** my runner runs! ***");
        var defaultGroup = ObstacleGroup.builder()
                .name("default")
                .build();

        var widebodies = ObstacleGroup.builder().name("widebodies").build();

        var twr = Obstacle.builder()
                .name("twr")
                .height(25)
                .build();

        var pole = Obstacle.builder()
                .name("pole")
                .height(50)
                .build();

        var sender = Obstacle.builder().name("sender").height(500).build();

        var assoc = List.of(
                new ObstacleGroupObstacle(defaultGroup, twr),
                new ObstacleGroupObstacle(defaultGroup, pole),
                new ObstacleGroupObstacle(widebodies, pole),
                new ObstacleGroupObstacle(widebodies, sender)
        );
        obstacleGroupObstacleRepository.saveAll(assoc);

        var res = obstacleGroupObstacleRepository.findObstacleGroupObstacleByObstacleGroup_Name("widebodies");
        for (var o: res) {
            System.out.println(o.getObstacle().getName() + o.getObstacle().getHeight());
        }
        obstacleGroupObstacleRepository.findObstacleGroupObstacleByObstacleGroup_Name("default")
                .forEach(og -> System.out.println(og.getObstacle().getName()));

    }
}
