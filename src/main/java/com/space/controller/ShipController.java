package com.space.controller;

import com.space.model.entity.Ship;
import com.space.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
