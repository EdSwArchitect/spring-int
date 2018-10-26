package com.bscllc.elastic;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

/**
 * ["hour_beginning","location","Pedestrians","Towards Manhattan","Towards Brooklyn","weather_summary","temperature","precipitation","lat","lon","events"]
 */
public class ESBrooklyn {
    @JsonProperty ("@timestamp")
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date hourBeginning;
    @JsonProperty("location")
    private String location;
    @JsonProperty("Pedestrians")
    private long pedestrians;
    @JsonProperty("towards_manhattan")
    private long towards_manhattan;
    @JsonProperty("towards_brooklyn")
    private long towards_brooklyn;
    @JsonProperty("weather_summary")
    private String weatherSummary;
    @JsonProperty("temperature")
    private int temperature;
    @JsonProperty("precipitation")
    private double precipitation;
    @JsonProperty("lat")
    private double lat;
    @JsonProperty("lon")
    private double lon;
    @JsonProperty("events")
    private String events;

    /**
     *
     */
    public ESBrooklyn() {

    }

    public Date getHourBeginning() {
        return hourBeginning;
    }

    public void setHourBeginning(Date hourBeginning) {
        this.hourBeginning = hourBeginning;
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

    public long getTowards_manhattan() {
        return towards_manhattan;
    }

    public void setTowards_manhattan(long towards_manhattan) {
        this.towards_manhattan = towards_manhattan;
    }

    public long getTowards_brooklyn() {
        return towards_brooklyn;
    }

    public void setTowards_brooklyn(long towards_brooklyn) {
        this.towards_brooklyn = towards_brooklyn;
    }

    public String getWeatherSummary() {
        return weatherSummary;
    }

    public void setWeatherSummary(String weatherSummary) {
        this.weatherSummary = weatherSummary;
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

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ESBrooklyn brooklyn = (ESBrooklyn) o;
        return pedestrians == brooklyn.pedestrians &&
                towards_manhattan == brooklyn.towards_manhattan &&
                towards_brooklyn == brooklyn.towards_brooklyn &&
                temperature == brooklyn.temperature &&
                Double.compare(brooklyn.precipitation, precipitation) == 0 &&
                Double.compare(brooklyn.lat, lat) == 0 &&
                Double.compare(brooklyn.lon, lon) == 0 &&
                Objects.equals(hourBeginning, brooklyn.hourBeginning) &&
                Objects.equals(location, brooklyn.location) &&
                Objects.equals(weatherSummary, brooklyn.weatherSummary) &&
                Objects.equals(events, brooklyn.events);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hourBeginning, location, pedestrians, towards_manhattan, towards_brooklyn, weatherSummary, temperature, precipitation, lat, lon, events);
    }

//    @Override
//    public String toString() {
//        return "ESBrooklyn{" +
//                "hourBeginning=" + hourBeginning +
//                ", location='" + location + '\'' +
//                ", pedestrians=" + pedestrians +
//                ", towards_manhattan=" + towards_manhattan +
//                ", towards_brooklyn=" + towards_brooklyn +
//                ", weatherSummary='" + weatherSummary + '\'' +
//                ", temperature=" + temperature +
//                ", precipitation=" + precipitation +
//                ", lat=" + lat +
//                ", lon=" + lon +
//                ", events='" + events + '\'' +
//                '}';
//    }
}
