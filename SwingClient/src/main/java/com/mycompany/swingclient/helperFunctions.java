/*
 * helperFunctions.java
 * 
 * The helperFunctions class provides utility methods for our Swing GUI client.
 * 
 * @author Michael Putnik (putn0005@algonquinlive.com)
 */
package com.mycompany.swingclient;

import java.net.HttpURLConnection;
import java.util.Map;

/**
 *
 * @author mputn
 */
public class helperFunctions {
    
    /**
     * Converts a Map object (key:value pairs) to a string representation.
     * 
     * @param map the Map to convert
     * @return the string representation of the map
     */
    public static String mapToString(Map<String, String> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(", ");
        }
        if (!map.isEmpty()) {
            sb.setLength(sb.length() - 2); // Remove the trailing comma and space
        }
        sb.append("}");
        return sb.toString();
    }  
    
    /**
     * Gets the error message corresponding to the given HTTP status code.
     * 
     * @param statusCode the HTTP status code
     * @return the error message
     */
    public static String getErrorMessage(int statusCode) {
        switch (statusCode) {
            case HttpURLConnection.HTTP_BAD_REQUEST:
                return "400 Bad Request";
            case HttpURLConnection.HTTP_NOT_FOUND:
                return "404 Not Found";
            case HttpURLConnection.HTTP_INTERNAL_ERROR:
                return "500 Internal Server Error";
            default:
                return "Error: " + statusCode;
        }
    }
}