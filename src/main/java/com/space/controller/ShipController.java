package com.space.controller;

import com.space.model.entity.Ship;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class ShipController {

    @Autowired
    ShipService shipService;

    @GetMapping(path = "/ships", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Ship> showAll() {
        return shipService.listAll();
    }

    @GetMapping(path = "/ships/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long count() {
        System.out.println(shipService.сount());
        return shipService.сount();
    }

    @GetMapping(path = "ships/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ship findById(@PathVariable("id") Long id) {
        if (id == 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        try {
            Optional<Ship> shipResponse = shipService.findById(id);
            return shipResponse.get();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ship not found.", e);
        }
    }
}
