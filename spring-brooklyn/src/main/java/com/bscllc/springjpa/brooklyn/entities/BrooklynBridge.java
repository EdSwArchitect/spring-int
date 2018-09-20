package com.bscllc.springjpa.brooklyn.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "brooklyn_bridge")
public class BrooklynBridge {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name = "start_date")
    private Date startDate;
    private String location;
    private long pedestrians;
    @Column(name = "towards_m")
    private long towardsM;
    @Column(name = "towards_b")
    private long towardsB;
    private String weather;
    private int temperature;
    private double precipitation;
    private double lat;
    private double lon;
    private String events;

    public BrooklynBridge() {

    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getPedestrians() {
        return pedestrians;
    }

    public void setPedestrians(long pedestrians) {
        this.pedestrians = pedestrians;
    }

    public long getTowardsM() {
        return towardsM;
    }

    public void setTowardsM(long towardsM) {
        this.towardsM = towardsM;
    }

    public long getTowardsB() {
        return towardsB;
    }

    public void setTowardsB(long towardsB) {
        this.towardsB = towardsB;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getEvents() {
        return events;
    }

    public void setEvents(String events) {
        this.events = events;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrooklynBridge that = (BrooklynBridge) o;
        return id == that.id &&
                pedestrians == that.pedestrians &&
                towardsM == that.towardsM &&
                towardsB == that.towardsB &&
                temperature == that.temperature &&
                Double.compare(that.precipitation, precipitation) == 0 &&
                Double.compare(that.lat, lat) == 0 &&
                Double.compare(that.lon, lon) == 0 &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(location, that.location) &&
                Objects.equals(weather, that.weather) &&
                Objects.equals(events, that.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, id, location, pedestrians, towardsM, towardsB, weather, temperature, precipitation, lat, lon, events);
    }

    @Override
    public String toString() {
        return "BrooklynBridge{" +
                "startDate=" + startDate +
                ", id=" + id +
                ", location='" + location + '\'' +
                ", pedestrians=" + pedestrians +
                ", towardsM=" + towardsM +
                ", towardsB=" + towardsB +
                ", weather='" + weather + '\'' +
                ", temperature=" + temperature +
                ", precipitation=" + precipitation +
                ", lat=" + lat +
                ", lon=" + lon +
                ", events='" + events + '\'' +
                '}';
    }
}
