package com.five0;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Document(indexName = "routes", type = "active")
public class Route {

    @Id
    private String id;
    private String city;
    private String destination;
    private String departure;
    private String arrive;

    public Route() {
    }

    public Route(String city, String destination, String departure, String arrive) {
        this.city = city;
        this.destination = destination;
        this.departure = departure;
        this.arrive = arrive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrive() {
        return arrive;
    }

    public void setArrive(String arrive) {
        this.arrive = arrive;
    }

    public float getWeight() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime departureTime = LocalTime.parse(departure, f);
        LocalTime arriveTime = LocalTime.parse(arrive, f);
        return (arriveTime.getHour() + arriveTime.getMinute() * 0.01f) - (departureTime.getHour() + departureTime.getMinute() * 0.01f);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Objects.equals(city, route.city) &&
                Objects.equals(destination, route.destination);
    }

    @Override
    public int hashCode() {

        return Objects.hash(city, destination);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", city='" + city + '\'' +
                ", destination='" + destination + '\'' +
                ", departure='" + departure + '\'' +
                ", arrive='" + arrive + '\'' +
                '}';
    }
}
