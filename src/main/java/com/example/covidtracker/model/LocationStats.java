package com.example.covidtracker.model;

public class LocationStats {
    private String Country;
    private String state;
    private int TotalCases;

    public String getCountry() {
        return Country;
    }

    public  void setCountry(String country) {
        Country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getTotalCases() {
        return TotalCases;
    }

    public void setTotalCases(int totalCases) {
        TotalCases = totalCases;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "Country='" + Country + '\'' +
                ", state='" + state + '\'' +
                ", TotalCases=" + TotalCases +
                '}';
    }
}
