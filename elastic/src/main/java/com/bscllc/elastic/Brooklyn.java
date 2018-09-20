package com.bscllc.elastic;

import com.univocity.parsers.annotations.Format;
import com.univocity.parsers.annotations.Parsed;

import java.util.Date;
import java.util.Objects;

/**
 * ["hour_beginning","location","Pedestrians","Towards Manhattan","Towards Brooklyn","weather_summary","temperature","precipitation","lat","lon","events"]
 */
public class Brooklyn {
    @Parsed(field="hour_beginning")
    @Format(formats = {"M/d/yyyy HH:mm"})
    private Date hourBeginning;
    @Parsed(field="location")
    private String location;
    @Parsed(field="Pedestrians")
    private long pedestrians;
    @Parsed(field="Towards Manhattan")
    private long towardsManhattan;
    @Parsed(field="Towards Brooklyn")
    private long towardsBrooklyn;
    @Parsed(field="weather_summary")
    private String weatherSummary;
    @Parsed(field="temperature")
    private int temperature;
    @Parsed(field="precipitation")
    private double precipitation;
    @Parsed(field="lat")
    private double lat;
    @Parsed(field="long")
    private double lon;
    @Parsed(field="events")
    private String events;

    /**
     *
     */
    public Brooklyn() {

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

    public long getTowardsManhattan() {
        return towardsManhattan;
    }

    public void setTowardsManhattan(long towardsManhattan) {
        this.towardsManhattan = towardsManhattan;
    }

    public long getTowardsBrooklyn() {
        return towardsBrooklyn;
    }

    public void setTowardsBrooklyn(long towardsBrooklyn) {
        this.towardsBrooklyn = towardsBrooklyn;
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
        Brooklyn brooklyn = (Brooklyn) o;
        return pedestrians == brooklyn.pedestrians &&
                towardsManhattan == brooklyn.towardsManhattan &&
                towardsBrooklyn == brooklyn.towardsBrooklyn &&
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
        return Objects.hash(hourBeginning, location, pedestrians, towardsManhattan, towardsBrooklyn, weatherSummary, temperature, precipitation, lat, lon, events);
    }

    @Override
    public String toString() {
        return "Brooklyn{" +
                "hourBeginning=" + hourBeginning +
                ", location='" + location + '\'' +
                ", pedestrians=" + pedestrians +
                ", towardsManhattan=" + towardsManhattan +
                ", towardsBrooklyn=" + towardsBrooklyn +
                ", weatherSummary='" + weatherSummary + '\'' +
                ", temperature=" + temperature +
                ", precipitation=" + precipitation +
                ", lat=" + lat +
                ", lon=" + lon +
                ", events='" + events + '\'' +
                '}';
    }
}
