package com.five0;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface RouteRepository extends ElasticsearchRepository<Route, String> {
    List<Route> findByCity(String city);
}