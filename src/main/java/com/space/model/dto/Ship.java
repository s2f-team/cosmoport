package com.space.model.dto;

import com.space.model.ShipType;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Ship {
    private Long id;
    private String name;
    private String planet;
    private ShipType shipType;
    private Date prodDate; //ms (started 01.01.1970)
    private Boolean isUsed; //default false (if doesn't exists)
    private Double speed;
    private Integer crewSize;
    private Double rating;

    public Ship(String name, String planet, ShipType shipType, Date prodDate, Boolean isUsed, Double speed, Integer crewSize) {
        this.name = name;
        this.planet = planet;
        this.shipType = shipType;
        this.prodDate = prodDate;
        this.isUsed = isUsed;
        this.speed = speed;
        this.crewSize = crewSize;
        this.rating = getRating();
    }

    public Double getRating() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.prodDate);

        int currentYear = 3019;
        int productionYear = calendar.get(Calendar.YEAR);
        double coefficient = isUsed ? 0.5 : 1;

        return (80 * this.speed * coefficient) / (currentYear - productionYear + 1);
    }

    public static void main(String[] args) {
    }


}
