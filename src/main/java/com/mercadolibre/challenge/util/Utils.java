package com.mercadolibre.challenge.util;

public class Utils {

    private static final double latitudBuenosAires = -34.61315;
    private static final double longitudBuenosAires = -58.37723;

    public static double calculateDistance(double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - latitudBuenosAires);
        double lonDistance = Math.toRadians(lon2 - longitudBuenosAires);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(latitudBuenosAires)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
