package com.mercadolibre.challenge.util;

public class Utils {

    private static final double LATITUDEBUENOSAIRES = -34.61315;
    private static final double LONGITUDEBUENOSAIRES = -58.37723;

    public static double calculateDistance(double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - LATITUDEBUENOSAIRES);
        double lonDistance = Math.toRadians(lon2 - LONGITUDEBUENOSAIRES);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(LATITUDEBUENOSAIRES)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public static double getDistanceBetweenPointsNew(double latitude2, double longitude2) {
        double theta = Math.toRadians(LONGITUDEBUENOSAIRES - longitude2);
        double distance = 60 * 1.1515 * (180/Math.PI) * Math.acos(
                Math.sin(LATITUDEBUENOSAIRES * (Math.PI/180)) * Math.sin(latitude2 * (Math.PI/180)) +
                        Math.cos(LATITUDEBUENOSAIRES * (Math.PI/180)) * Math.cos(latitude2 * (Math.PI/180)) * Math.cos(theta * (Math.PI/180))
        );
        return Math.round(distance * 1.609344);
    }
}
