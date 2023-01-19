package com.deepak.java.geohash;

import com.deepak.java.coords.Coordinates;

/**
 * todo
 */
public class LongestEnclosingGeoHash {

//todo - combine these methods into one. We iterate to encode and then iterate to find the longest.
// Two iterations -> could make it just one

    /**
     * todo
     */
    public static String longestEnclosingHash(Coordinates... polygonPoints) {
        String[] hashes = new String[polygonPoints.length];
        int i = 0;
        for (Coordinates ds : polygonPoints) {
            hashes[i] = GeoHash.encode(ds);
            i++;
        }
        return longestCommonPrefix(hashes);


    }

    /**
     * todo
     */
    private static String longestCommonPrefix(String[] strings) {
        if (strings.length == 0) {
            return "";   // Or maybe return null?
        }

        for (int prefixLen = 0; prefixLen < strings[0].length(); prefixLen++) {
            char c = strings[0].charAt(prefixLen);
            for (int i = 1; i < strings.length; i++) {
                if (prefixLen >= strings[i].length() || strings[i].charAt(prefixLen) != c) {
                    // Mismatch found
                    return strings[i].substring(0, prefixLen);
                }
            }
        }
        return strings[0];
    }

}
