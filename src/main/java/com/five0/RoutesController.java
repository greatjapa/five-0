package com.five0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class RoutesController {

    @Autowired
    private RouteRepository repository;

    @RequestMapping(method=GET, name = "/connections")
    public @ResponseBody List<Connection> connections(
            @RequestParam("city") String city,
            @RequestParam(value="depth", required=false) Integer depth) {
        if (depth == null) {
            depth = 3;
        }
        return breadthSearch(city, 0, depth);
    }

    private List<Connection> breadthSearch(String city, int steps, int depth) {
        List<Connection> result = new LinkedList<>();

        List<Route> routes = repository.findByCity(city);
        do {
            List<Route> continuation = new LinkedList<>();
            for (Route route : routes) {
                result.add(new Connection(route.getDestination(), steps));
                continuation.addAll(repository.findByCity(route.getDestination()));
            }
            routes = continuation;
            steps++;
        } while(!routes.isEmpty() && steps < depth);
        return result;
    }

}
