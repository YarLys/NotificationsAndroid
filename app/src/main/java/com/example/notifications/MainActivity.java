package com.example.notifications;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.Operation;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.notifications.utils.NotificationUtil;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationUtil.getInstance().createNotificationChannel(this);

        Log.d("AAAAAA", ""+NotificationUtil.getInstance().increaseA());

        Button button = findViewById(R.id.PushButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationUtil.getInstance().showNotification(getApplicationContext(),
                        "Оценка по географии", "КР за 06.04 - оценка 3/5");

                PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(MyWorker.class, 30, TimeUnit.MINUTES)
                        .build();
                WorkManager workManager = WorkManager.getInstance((getApplicationContext()));
                Operation enqueue = workManager.enqueueUniquePeriodicWork("Notification",
                        ExistingPeriodicWorkPolicy.REPLACE, workRequest);
            }
        });

        askNotificationPermission();

        // Не по программе: просто разбирались с паттерном Builder на примере
        Base base = new Base.Builder()
                .setA(1)
                .setB(2)
                .setC("3")
                .build();
    }

    // Запрашиватель разрешений
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted->{
                if (isGranted) {
                    Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "BAD", Toast.LENGTH_SHORT).show();
                }
            });

    private void askNotificationPermission() {
        requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
    }
}