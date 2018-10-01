package com.five0.graph;

import com.five0.Route;

import java.util.*;

public class DijkstraAlgorithm {

    private final List<Route> routes;
    private Set<String> settledNodes;
    private Set<String> unSettledNodes;
    private Map<String, String> predecessors;
    private Map<String, Float> distance;

    public DijkstraAlgorithm(List<Route> routes) {
        this.routes = new ArrayList<>(routes);
    }

    public void execute(String source) {
        settledNodes = new HashSet<>();
        unSettledNodes = new HashSet<>();
        distance = new HashMap<>();
        predecessors = new HashMap<>();
        distance.put(source, 0f);
        unSettledNodes.add(source);
        while (unSettledNodes.size() > 0) {
            String node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }

    private void findMinimalDistances(String node) {
        List<String> adjacentNodes = getNeighbors(node);
        for (String target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node)
                    + getDistance(node, target)) {
                distance.put(target, getShortestDistance(node)
                        + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }

    }

    private float getDistance(String node, String target) {
        for (Route route : routes) {
            if (route.getCity().equals(node)
                    && route.getDestination().equals(target)) {
                return route.getWeight();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    private List<String> getNeighbors(String node) {
        List<String> neighbors = new ArrayList<>();
        for (Route route : routes) {
            if (route.getCity().equals(node)
                    && !isSettled(route.getDestination())) {
                neighbors.add(route.getDestination());
            }
        }
        return neighbors;
    }

    private String getMinimum(Set<String> vertexes) {
        String minimum = null;
        for (String vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private boolean isSettled(String vertex) {
        return settledNodes.contains(vertex);
    }

    public float getShortestDistance(String destination) {
        Float d = distance.get(destination);
        if (d == null) {
            return Float.MAX_VALUE;
        } else {
            return d;
        }
    }

    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public List<String> getPath(String target) {
        List<String> path = new LinkedList<>();
        String step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }
}
