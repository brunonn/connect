package com.geoloc.connect;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LocationWorker extends Worker {
    public LocationWorker(
            @NonNull Context appContext,
            @NonNull WorkerParameters workerParams) {
        super(appContext, workerParams);
    }

    private static final String TAG = LocationWorker.class.getSimpleName();

    private FusedLocationProviderClient fusedLocationClient;

    @NonNull
    @Override
    public Result doWork() {
        Context context = getApplicationContext();
        try {
            Log.e(TAG, "sending data to firebase");
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location) {
                                if (location != null) {
                                    Double lat = location.getLatitude();
                                    Double lng = location.getLongitude();
                                    LocationForm locationForm = new LocationForm(lat, lng);
                                    DatabaseReference  database = FirebaseDatabase.getInstance().getReference();
                                    String key = database.child("users").push().getKey();
                                    database.child("users").child(key).setValue(locationForm);

                                }
                            }
                        });
            }
            return Result.success();
        } catch (Throwable throwable) {
            Log.e(TAG, "Error applying blur", throwable);
            return Result.failure();
        }
    }
}