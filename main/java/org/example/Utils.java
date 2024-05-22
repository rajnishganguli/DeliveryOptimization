package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Utils {

    /**
     * Method to calculate the Haversine distance between two geo-coordinates
     * @param radius The radius of the sphere on whose surface distance has to be calculated
     * @param coordinate1 Latitudes and Longitudes of the first point.
     * @param coordinate2 Latitudes and Longitudes of the second point.
     * @return The haversine distance between those two points.
     */
    public static double haversineDistance(double radius, Grid coordinate1, Grid coordinate2) {
        double phi1 = Math.toRadians(coordinate1.getLatitude());
        double phi2 = Math.toRadians(coordinate2.getLatitude());

        double dLat = Math.toRadians(coordinate2.getLatitude() - coordinate1.getLatitude());
        double dLon = Math.toRadians(coordinate2.getLongitude() - coordinate1.getLongitude());

        double a =  Math.pow(Math.sin(dLat/2), 2) +
                (Math.cos(phi1) * Math.cos(phi2) * Math.pow(Math.sin(dLon/2), 2));

        double theta = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return radius * theta;
    }

    // Generates list of

    /**
     * Generates all the permutations of elements in the list.
     * @param elements The list to be permutated
     * @return The list of all the possible permutations.
     */
    public static List<List<String>> generatePermutations(List<String> elements){
        List<List<String>> permutations = new ArrayList<>();
        generatePermutations(elements, 0, permutations);
        return permutations;
    }

    private static void generatePermutations(List<String> elements, int start, List<List<String>> permutations) {
        if (start == elements.size() - 1) {
            permutations.add(new ArrayList<>(elements));
            return;
        }
        for (int i = start; i < elements.size(); i++) {
            Collections.swap(elements, start, i);
            generatePermutations(elements, start + 1, permutations);
            Collections.swap(elements, start, i); // backtrack
        }
    }
}
