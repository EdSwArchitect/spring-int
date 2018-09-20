package com.bscllc.springjpa.brooklyn.repositories;

import com.bscllc.springjpa.brooklyn.entities.BrooklynBridge;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BrooklynBridgeRepository extends CrudRepository<BrooklynBridge, Long> {
    List<BrooklynBridge> findByStartDate(LocalDateTime time);

    List<BrooklynBridge> findAllByPedestriansGreaterThanEqualAndPedestriansLessThanEqual(long start, long max);

    List<BrooklynBridge> findByEvents(String event);
}
