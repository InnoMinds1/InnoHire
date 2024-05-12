package edu.esprit.controllers;

import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class MapController {
    @FXML
    private WebView map;

    @FXML
    public void initialize() {
        WebEngine webEngine = map.getEngine();

        // Enable JavaScript
        webEngine.setJavaScriptEnabled(true);

        // Add event filter to WebView to capture mouse clicks
        map.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_CLICKED, event -> {
            // Get x and y coordinates of the click event
            double mouseX = event.getX();
            double mouseY = event.getY();

            // Get map latitude and longitude from mouse coordinates
            JSObject result = (JSObject) webEngine.executeScript(
                    "document.elementFromPoint(" + mouseX + "," + mouseY + ").getAttribute('data-latlng')"
            );

            if (result != null) {
                String[] latLng = result.toString().split(",");
                double lat = Double.parseDouble(latLng[0]);
                double lng = Double.parseDouble(latLng[1]);

                // Handle latitude and longitude
                handleCoordinates(lat, lng);
            }
        });

        // Load Google Maps
        webEngine.load("https://www.google.com/maps");
    }

    // Method to handle latitude and longitude
    private void handleCoordinates(double lat, double lng) {
        System.out.println("Latitude: " + lat + ", Longitude: " + lng);
        // You can perform further actions with the obtained coordinates here
    }
}
