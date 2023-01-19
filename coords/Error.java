package com.deepak.java.coords;

/**
 * todo
 */
public class Error {

    private final coords.LatLonPair latLonPair;

    /**
     * todo
     */
    Error(double lat, double lon) {
        this.latLonPair = new coords.LatLonPair(lat, lon);
    }a


    /**
     * todo
     */
    public double getLat() {
        return latLonPair.getLat();
    }

    /**
     * todo
     */
    public double getLon() {
        return latLonPair.getLon();
    }


}
