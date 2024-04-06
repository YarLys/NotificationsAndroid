package com.example.notifications;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.notifications.utils.NotificationUtil;

public class MyWorker extends Worker {

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }
    @NonNull
    @Override
    public Result doWork() {
        NotificationUtil.getInstance().showNotification(getApplicationContext(), "Teatime", "Сегодня ерл грей");

        return Result.success();
    }
}
