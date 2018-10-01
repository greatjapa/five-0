package com.five0;

import com.five0.graph.DijkstraAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class RoutesController {

    @Autowired
    private RouteRepository repository;

    @RequestMapping(method=GET, value="/connection")
    public @ResponseBody List<Connection> connection(
            @RequestParam("city") String city,
            @RequestParam(value="depth", required=false) Integer depth) {
        depth = (depth == null) ? 3 : depth;
        return breadthSearch(city, 0, depth);
    }

    @RequestMapping(method=GET, value="/time")
    public @ResponseBody List<Time> time(@RequestParam("city") String city) {
        List<Route> routes = new ArrayList<>();
        repository.findAll().forEach(routes::add);

        Set<String> cities = new HashSet<>();
        for (Route route : routes) {
            if (!route.getCity().equals(city)) {
                cities.add(route.getCity());
            }
            if (!route.getDestination().equals(city)) {
                cities.add(route.getDestination());
            }
        }

        DijkstraAlgorithm algorithm = new DijkstraAlgorithm(routes);
        algorithm.execute(city);

        List<Time> result = new ArrayList<>();
        for (String c : cities) {
            result.add(new Time(c, algorithm.getShortestDistance(c)));
        }
        result.sort(Comparator.comparing(Time::getTime));
        return result;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public void handleMissingParams(MissingServletRequestParameterException ex) {
        ex.printStackTrace();
    }

    private List<Connection> breadthSearch(String city, int steps, int depth) {
        Set<String> visited = new HashSet<>();
        visited.add(city);

        List<Connection> result = new LinkedList<>();
        List<Route> routes = repository.findByCity(city);
        do {
            List<Route> continuation = new LinkedList<>();
            for (Route route : routes) {
                String nextCity = route.getDestination();

                if (!visited.contains(nextCity)) {
                    result.add(new Connection(nextCity, steps));
                    continuation.addAll(repository.findByCity(nextCity));
                    visited.add(nextCity);
                }
            }
            routes = continuation;
            steps++;
        } while(!routes.isEmpty() && steps < depth);
        return result;
    }
}
