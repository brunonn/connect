package com.geoloc.connect;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class UploadWorker extends Worker {
    public UploadWorker(
            @NonNull Context context,
            @NonNull WorkerParameters params) {
        super(context, params);
    }

    @Override
    public Result doWork() {

        // Do the work here--in this case, upload the images.
        sendLocationToFirebase();

        // Indicate whether the work finished successfully with the Result
        return Result.success();
    }

    private void sendLocationToFirebase() {
        Log.e("LOCATION", "SENDINTG LOCATION");
    }
}
