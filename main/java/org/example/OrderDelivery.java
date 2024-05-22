package org.example;

import java.util.*;

public class OrderDelivery {

    // Radius of the Earth in kilometers
    private static double EARTH_RADIUS = 6371; // in kilometres

    /**
     * Computes the path that takes the least amount of time.
     * @param locations The latitudes and longitudes of restaurants, customers and the rider
     * @param prepTimes The map of times, the restaurants take to prepare the meal.
     * @param riderSpeed The speed with which the rider moves.
     */
    public static void computeOptimalPath( Map<String, Grid> locations, Map<String, Double> prepTimes, double riderSpeed){
        // Calculate distances between all pairs of locations
        Map<String, Double> distances = new HashMap<>();
        for (String loc1 : locations.keySet()) {
            for (String loc2 : locations.keySet()) {
                if (!loc1.equals(loc2)) {
                    distances.put(loc1 + "-" + loc2, Utils.haversineDistance(EARTH_RADIUS, locations.get(loc1), locations.get(loc2)));
                }
            }
        }
        // Convert distances to travel times
        Map<String, Double> travelTimes = new HashMap<>();
        for (Map.Entry<String, Double> entry : distances.entrySet()) {
            travelTimes.put(entry.getKey(), entry.getValue()/riderSpeed);
        }
        List<List<String>> routes = generateAllValidRoutes(prepTimes.size());
        // Find the route with least time taken.
        double minTime = Double.MAX_VALUE;
        List<String> bestRoute = null;
        for (List<String> route : routes) {
            double routeTime = calculateRouteTime(route, travelTimes, prepTimes);
            if (routeTime < minTime) {
                minTime = routeTime;
                bestRoute = route;
            }
        }
        // Print the result
        System.out.println("The shortest time is " + minTime + " hours for the route " + String.join(" -> ", bestRoute));
    }

    /*



     */

    /**
     * Given K restaurants and K customers, it computes all possible valid routes from delivery person to customers.
     * For K=2, the valid routes look like as follows
     *              {
     *                 {"Aman", "R1", "C1", "R2", "C2"},
     *                 {"Aman", "R2", "C2", "R1", "C1"},
     *
     *                 {"Aman", "R1", "R2", "C1", "C2"},
     *                 {"Aman", "R1", "R2", "C2", "C1"},
     *                 {"Aman", "R2", "R1", "C1", "C2"},
     *                 {"Aman", "R2", "R1", "C2", "C1"}
     *             };
     * @param K The number of orders
     * @return All the possible correct routes from rider to the customers.
     */
    private static List<List<String>> generateAllValidRoutes(int K) {
        List<List<String>> routes = generateAllValidPathsFromRestaurantsToCustomers(K);
        // All routes must start with driver i.e. Aman.
        // So prepend Aman to all valid routes
        for(List<String> route : routes){
            route.add(0, "Aman");
        }
        return routes;
    }

    /**
     * Generates all the valid routes form restaurants to the customers.
     * @param K The number of orders
     * @return All the valid routes form restaurants to the customers.
     */
    private static List<List<String>> generateAllValidPathsFromRestaurantsToCustomers (int K){
        List<List<String>> permutations = generateAllArrangementsOfRestaurantsAndCustomers(K);
        List<List<String>> validPermutations = new ArrayList<>();
        for(List<String> permutation : permutations){
            if(isValidRoute(permutation, K)){
                validPermutations.add(permutation);
            }
        }
        return validPermutations;
    }

    /**
     *
     * @param K The number of orders
     * @return All the possible permutations of K restaurants and K customers.
     */
    private static List<List<String>> generateAllArrangementsOfRestaurantsAndCustomers(int K) {
        List<String> elements = new ArrayList<>();
        for (int i = 1; i <= K; i++) {
            elements.add("R" + i);
            elements.add("C" + i);
        }
        return Utils.generatePermutations(elements);
    }

    /**
     * Checks if the given ordering of restaurants and customers is correct.
     * Order from restaurant can be delivered only after it is picked up.
     * So restaurant Ri must come before customer Ci in the route.
     * @param arrangement A route consisting of restaurants and customers.
     * @param K The number of orders
     * @return true/false depending on whether the route is a valid one or not.
     */
    private static boolean isValidRoute(List<String> arrangement, int K) {
        HashMap<Integer, Integer> positionsA = new HashMap<>();
        HashMap<Integer, Integer> positionsB = new HashMap<>();

        for (int i = 0; i < arrangement.size(); i++) {
            String element = arrangement.get(i);
            if (element.startsWith("R")) {
                int index = Integer.parseInt(element.substring(1));
                positionsA.put(index, i);
            } else if (element.startsWith("C")) {
                int index = Integer.parseInt(element.substring(1));
                positionsB.put(index, i);
            }
        }
        for (int i = 1; i <= K; i++) {
            if (positionsA.get(i) > positionsB.get(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method to calculate total time taken for one give route
     * @param route The given ordering of rider, restaurants and customers
     * @param travelTimes Map of times it takes to cover distance between any two points.
     * @param prepTimes Map of times that restaurants take to prepare the meal.
     * @return The total time taken in a given route.
     */
    private static double calculateRouteTime(List<String> route,
                                      Map<String, Double> travelTimes,
                                      Map<String, Double> prepTimes) {
        Set<String> restaurants = prepTimes.keySet();
        double totalTime = 0;
        for(int i=1; i<route.size(); ++i){
            totalTime += travelTimes.get(route.get(i) + "-" + route.get(i-1));
            if(restaurants.contains(route.get(i))){
                totalTime = Math.max(prepTimes.get(route.get(i)), totalTime);
            }
        }
        return totalTime;
    }

}
