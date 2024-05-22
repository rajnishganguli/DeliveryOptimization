package org.example;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // -----------------------------------------
        // ------------ Input starts ---------------
        // -----------------------------------------

        // Coordinates of Aman, R1, R2, C1, C2
        Map<String, Grid> locations = new HashMap<>();
        locations.put("Aman", new Grid(12.935, 77.619));
        locations.put("R1", new Grid(12.935, 77.613));
        locations.put("R2", new Grid(12.932, 77.610));
        locations.put("C1", new Grid(12.937, 77.620));
        locations.put("C2", new Grid(12.934, 77.617));

        // Preparation times for R1 and R2 in hours
        double pt1 = 0.15;
        double pt2 = 0.20;

        // Aman's speed throughout in km.hr
        double deliverySpeed = 20;

        // -----------------------------------------
        // -------------- Input ends ---------------
        // -----------------------------------------

        Map<String, Double> prepTimes = new HashMap<>();
        prepTimes.put("R1", pt1);
        prepTimes.put("R2", pt2);

        OrderDelivery.computeOptimalPath(locations, prepTimes, deliverySpeed);
    }
}