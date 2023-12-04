package com.is1di.remotecoffeemachine.repository;

import com.is1di.remotecoffeemachine.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    List<OrderEntity> findByCloseTimestampNull();

    boolean existsByCloseTimestampNull();

    Optional<OrderEntity> findFirstByStartTimestampNullOrderByCreationTimestampAsc();

    long countByCloseTimestampNull();
}
