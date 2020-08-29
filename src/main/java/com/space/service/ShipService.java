package com.space.service;

import com.space.model.entity.Ship;
import com.space.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ShipService {

    @Autowired
    ShipRepository shipRepository;

    public List<Ship> listAll() {
        return (List<Ship>) shipRepository.findAll();
    }

    public long сount() {
        return shipRepository.count();
    }

    public Optional<Ship> findById(Long id) {
        return shipRepository.findById(id);
    }


}
