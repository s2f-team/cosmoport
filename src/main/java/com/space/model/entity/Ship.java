package com.space.model.entity;

import com.space.model.ShipType;
import jakarta.validation.constraints.*;
import org.apache.commons.math3.util.Precision;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name = "ship")
public class Ship {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull
    @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
    private String name;

    @Column(name = "planet")
    @NotNull
    @Size(min = 1, max = 50, message = "Planet must be between 1 and 50 characters")
    private String planet;

    @Enumerated(EnumType.STRING)
    @NotNull
    private ShipType shipType;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Min(value = 26194914000000L, message = "Min date = 26194914000000 ms")
    @Max(value = 33126786000000L, message = "Max date = 33126786000000 ms")
    private Date prodDate; //ms (started 01.01.1970)

    @Column(name = "isUsed")
    private Boolean isUsed; //default false (if doesn't exists)

    @Column(name = "speed")
    @NotNull
    private Double speed;

    @Column(name = "crewSize")
    @NotNull
    @Min(value = 1, message = "Min crew size = 1")
    @Max(value = 9999, message = "Max crew size = 9999")
    private Integer crewSize;

    @Column(name = "rating")
    private Double rating;

    public Ship() {
    }

    public Ship(Long id, String name, String planet, ShipType shipType, Date prodDate, Boolean isUsed, Double speed, Integer crewSize, Double rating) {
        this.id = id;
        this.name = name;
        this.planet = planet;
        this.shipType = shipType;
        this.prodDate = prodDate;
        this.isUsed = isUsed;
        this.speed = speed;
        this.crewSize = crewSize;
        this.rating = rating;
    }

    public Ship(@NotNull @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters") String name, @NotNull @Size(min = 1, max = 50, message = "Planet must be between 1 and 50 characters") String planet, @NotNull ShipType shipType, @NotNull @Min(value = 26194914000000L, message = "Min date = 26194914000000 ms") @Max(value = 33126786000000L, message = "Max date = 33126786000000 ms") Date prodDate, Boolean isUsed, @NotNull Double speed, @NotNull @Min(value = 1, message = "Min crew size = 1") @Max(value = 9999, message = "Max crew size = 9999") Integer crewSize) {
        this.name = name;
        this.planet = planet;
        this.shipType = shipType;
        this.prodDate = prodDate;
        this.isUsed = isUsed;
        this.speed = speed;
        this.crewSize = crewSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(Integer crewSize) {
        this.crewSize = crewSize;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double countRating() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(this.prodDate);

        int currentYear = 3019;
        int productionYear = calendar.get(Calendar.YEAR);
        double coefficient = isUsed ? 0.5 : 1;

        Double rating = (80 * this.speed * coefficient) / (currentYear - productionYear + 1);

        return roundDoubleTo100(rating);
    }

    public static Double roundDoubleTo100(Double value) {
        BigDecimal result = new BigDecimal(value);
        result = result.setScale(2, RoundingMode.HALF_UP);
        return result.doubleValue();
    }

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(date));
        System.out.println(date);
        System.out.println(date.getTime());

        Calendar calendar = new GregorianCalendar(2800, 1, 1);
        Calendar calendar2 = new GregorianCalendar(3019, 9, 1);

        System.out.println("2800" + calendar.getTimeInMillis());
        System.out.println("3019" + calendar2.getTimeInMillis());

        Double r = 27.512399999999998;
        Double r2 = 2752.153789;
        System.out.println(Precision.round(r, 2));
        System.out.println(Precision.round(r2, 2));

        System.out.println(roundDoubleTo100(r));
        System.out.println(roundDoubleTo100(r2));

        Date date1 = new Date(280026194914000000L);
        Date date2 = new Date(301933126786000000L);

        System.out.println(date1.getTime());
        System.out.println(date2.getTime());

    }


}
