package com.five0;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface RouteRepository extends ElasticsearchRepository<Route, String> {

    Route findByCity(String city);

    List<Route> findByDestination(String destination);
}