package com.deepak.java;


import ch.hsr.geohash.GeoHash;
import com.deepak.java.coords.Coordinates;
import com.deepak.java.geohash.LongestEnclosingGeoHash;
import com.deepak.java.polygon.GeoHashesInPolygon;
import org.fusesource.leveldbjni.All;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Polygon2Geohash {
    public static void main(String[] args) throws IOException {

        Coordinates[] allCords = null;
        Set<String> geoHash9 = new HashSet<>();
        Set<String> geohash7 = new HashSet<>();
        Set<String> geohash6 = new HashSet<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/home/deepaksingh/Downloads/export/usa_polygon.json")));
//        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/home/deepaksingh/Downloads/near_placematrix_usa.json")));
        String line = null;
        while( (line = br.readLine())!= null ){
            JSONObject jsonObject = new JSONObject(line);
            JSONObject geometry = jsonObject.getJSONObject("_source").getJSONObject("geometry");
            if (geometry.getString("type").equals("Polygon")) {
                JSONArray coordinates = geometry.getJSONArray("coordinates");
                JSONArray cords = coordinates.getJSONArray(0);
                if(cords != null) {
                    allCords = new Coordinates[cords.length()];
                    for (int i = 0; i < cords.length(); i++) {
                        JSONArray array = cords.getJSONArray(i);
                        Coordinates coord = new Coordinates(Float.parseFloat(String.valueOf(array.get(1))),
                                Float.parseFloat(String.valueOf(array.get(0))));
                        allCords[i] = coord;
                    }
                    Set<String> hashes = GeoHashesInPolygon.geohashesInPolygon(allCords, 9);
                    System.out.println(" Generated Hashes are  :- " + hashes);
                    geoHash9.addAll(hashes);
                    System.out.println(" Total size increasing is :- " + geoHash9.size());
                }
            }
        }
//        System.out.println(allCords.length);
//        int longestEnclosingHashLength = LongestEnclosingGeoHash.longestEnclosingHash(allCords).length();
//        System.out.println("longestEnclosingHashLength : " + longestEnclosingHashLength);
//        int precision = longestEnclosingHashLength + 2;
//        System.out.println(" Precision length is  : " + precision);
//        Set<String> hashes = GeoHashesInPolygon.geohashesInPolygon(allCords, precision);
//        System.out.println(geoHash);
//        System.out.println(geoHash9.size());
    }

}
