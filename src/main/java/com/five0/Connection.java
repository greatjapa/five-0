package com.five0;

import java.util.Objects;

public class Connection {

    private String city;
    private int steps;

    public Connection(String city, int steps) {
        this.city = city;
        this.steps = steps;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return steps == that.steps &&
                Objects.equals(city, that.city);
    }

    @Override
    public int hashCode() {

        return Objects.hash(city, steps);
    }

    @Override
    public String toString() {
        return "Connection{" +
                "city='" + city + '\'' +
                ", steps=" + steps +
                '}';
    }
}
