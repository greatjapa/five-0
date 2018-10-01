package com.five0;

public class Time {

    private String city;
    private float time;

    public Time(String city, float time) {
        this.city = city;
        this.time = time;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Time{" +
                "city='" + city + '\'' +
                ", time=" + time +
                '}';
    }
}
