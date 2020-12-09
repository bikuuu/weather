package com.biku.weather.forecast;

import com.biku.weather.exceptions.WrongDataException;

public class DirectionCalculator {

    String directCalculate(String windDirection) {

        Double degree = Double.parseDouble(windDirection);

        if (degree >= 0 && degree <= 33.45 || degree >= 337.30 && degree <= 360) {
            return "N";
        } else if (degree > 33.45 && degree < 67.30) {
            return "NE";
        } else if (degree >= 67.30 && degree <= 112.30) {
            return "E";
        } else if (degree > 112.30 && degree < 157.30) {
            return "SE";
        } else if (degree >= 157.30 && degree <= 202.30) {
            return "S";
        } else if (degree > 202.30 && degree < 247.30) {
            return "SW";
        } else if (degree >= 247.30 && degree <= 292.30) {
            return "W";
        } else if (degree > 292.30 && degree < 337.3) {
            return "NW";
        } else {
            throw new WrongDataException("Bad value of wind direction degree");
        }
    }
}
