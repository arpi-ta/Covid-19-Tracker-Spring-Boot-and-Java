package com.example.covidtracker.services;

import com.example.covidtracker.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrackerService {

    private static String VIRUS_DATA_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

        private List<LocationStats> finalstats=new ArrayList<>();

    public List<LocationStats> getFinalstats() {
        return finalstats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusData() throws IOException, InterruptedException {
         List<LocationStats> newstats=new ArrayList<>();

        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request= HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_DATA_URL))
                .build();
        HttpResponse<String> response= client.send(request,HttpResponse.BodyHandlers.ofString());
       // System.out.println(response.body());
        StringReader reader=new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
        for (CSVRecord record : records) {
            LocationStats locationStats=new LocationStats();
           locationStats.setCountry(record.get("Country/Region"));
            locationStats.setState(record.get("Province/State"));
            locationStats.setTotalCases(Integer.parseInt(record.get(record.size()-1)));
            System.out.println(locationStats);
            newstats.add(locationStats);
        }
        this.finalstats=newstats;


    }
}
