package com.example.demo.runner;

import com.example.demo.model.Obstacle;
import com.example.demo.model.ObstacleSet;
import com.example.demo.model.ObstacleSetObstacle;
import com.example.demo.repository.ObstacleSetObstacleRepository;
import com.example.demo.repository.ObstacleRepository;
import com.example.demo.repository.ObstacleSetRepository;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MyRunner implements CommandLineRunner {

    @PersistenceUnit
    private EntityManagerFactory emf;

    @Autowired
    ObstacleSetObstacleRepository obstacleSetObstacleRepository;

    @Autowired
    ObstacleSetRepository obstacleSetRepository;

    @Autowired
    ObstacleRepository obstacleRepository;
    @Override
    public void run(String... args) throws Exception {
        withEntityManager();
        //withRepos();
    }

    public void withRepos() {
        var assoc = buildObstacleSetObstacles();
        for (var os: assoc) {
            obstacleSetRepository.save(os.getObstacleSet());
            obstacleRepository.save(os.getObstacle());
            obstacleSetObstacleRepository.save(os);
        }
        obstacleSetObstacleRepository.delete(assoc.get(0));
        var res = obstacleSetObstacleRepository.findAll();
        System.out.println(res.size());
    }
    public void withEntityManager() {
        var assoc = buildObstacleSetObstacles();
        var em = emf.createEntityManager();
        em.getTransaction().begin();
        for (var os: assoc) {
            em.persist(os.getObstacleSet());
            em.persist(os.getObstacle());
            em.persist(os);
        }

//        select os.name, o.name
//        from
//          obstacle_set os
//        inner join
//          obstacle_set_obstacle oso
//              on oso.obstacle_set_uuid = os.uuid
//        inner join obstacle o
//              on oso.obstacle_uuid = o.uuid

        //em.remove(assoc.get(0));
        em.createQuery("delete from ObstacleSetObstacle oso where oso.obstacle.id = (select o.id from Obstacle o where o.name='pole')").executeUpdate();
        var res = em.createQuery("select o from ObstacleSetObstacle o").getResultList();
        System.out.println(res.size());
        em.createQuery("delete from Obstacle o where o.name ='pole'").executeUpdate();
        em.getTransaction().commit();
    }

    private static List<ObstacleSetObstacle> buildObstacleSetObstacles() {
        var defaultSet = ObstacleSet.builder().name("default").build();
        var widebodies = ObstacleSet.builder().name("widebodies").build();
        var twr = Obstacle.builder().name("twr").height(25).build();
        var pole = Obstacle.builder().name("pole").height(50).build();
        var sender = Obstacle.builder().name("sender").height(500).build();
        var assoc = List.of(
                new ObstacleSetObstacle(defaultSet, twr),
                new ObstacleSetObstacle(defaultSet, pole),
                new ObstacleSetObstacle(widebodies, pole),
                new ObstacleSetObstacle(widebodies, sender)
        );
        return assoc;
    }


}
