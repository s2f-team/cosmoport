package com.space.repository;

import com.space.model.entity.Ship;
import org.springframework.data.repository.CrudRepository;

public interface ShipRepository extends CrudRepository<Ship, Long> {
}
