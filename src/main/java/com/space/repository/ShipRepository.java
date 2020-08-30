package com.space.repository;

import com.space.model.entity.Ship;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;

public interface ShipRepository extends CrudRepository<Ship, Long>, JpaSpecificationExecutor<Ship> {

}
