package com.mercadolibre.challenge.util;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {

    private static final double LATITUDEBUENOSAIRES = -34.61315;
    private static final double LONGITUDEBUENOSAIRES = -58.37723;

    public static double calculateDistance(double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - LATITUDEBUENOSAIRES);
        double lonDistance = Math.toRadians(lon2 - LONGITUDEBUENOSAIRES);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(LATITUDEBUENOSAIRES)) * Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    public static String formatDouble(double value) {
        DecimalFormat df = new DecimalFormat("#.###");
        return df.format(value);
    }

    public static String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
