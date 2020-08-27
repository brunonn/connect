package com.geoloc.connect;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class LocationForm {

    public Double latitude;
    public Double longitude;

    public LocationForm() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }


    public LocationForm(Double lat, Double lng) {
        this.latitude = lat;
        this.longitude = lng;
    }
}
