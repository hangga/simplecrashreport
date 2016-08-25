package com.research.hangga.simplecrashreport;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by hangga on 11/03/16.
 */
public class SimpleExceptionHandler implements
        java.lang.Thread.UncaughtExceptionHandler {

    private final Activity context;
    private final String LINE_SEPARATOR = "\n";

    public SimpleExceptionHandler(Activity activity) {
        this.context = activity;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        StringWriter stackTrace = new StringWriter();
        ex.printStackTrace(new PrintWriter(stackTrace));
        StringBuilder builder = new StringBuilder();
        builder.append("************ CAUSE OF ERROR ************\n\n");
        builder.append(stackTrace.toString());
        Log.e("ERROR", ""+stackTrace.toString());
        builder.append("\n************ DEVICE INFORMATION ***********\n");
        builder.append("Brand: ");
        builder.append(Build.BRAND);
        builder.append(LINE_SEPARATOR);
        builder.append("Device: ");
        builder.append(Build.DEVICE);
        builder.append(LINE_SEPARATOR);
        builder.append("Model: ");
        builder.append(Build.MODEL);
        builder.append(LINE_SEPARATOR);
        builder.append("Id: ");
        builder.append(Build.ID);
        builder.append(LINE_SEPARATOR);
        builder.append("Product: ");
        builder.append(Build.PRODUCT);
        builder.append(LINE_SEPARATOR);
        builder.append("\n************ FIRMWARE ************\n");
        builder.append("SDK: ");
        builder.append(Build.VERSION.SDK);
        builder.append(LINE_SEPARATOR);
        builder.append("Release: ");
        builder.append(Build.VERSION.RELEASE);
        builder.append(LINE_SEPARATOR);
        builder.append("Incremental: ");
        builder.append(Build.VERSION.INCREMENTAL);
        builder.append(LINE_SEPARATOR);

        Intent intent = new Intent(context, ActivityShowError.class);
        intent.putExtra("error", builder.toString());
        context.startActivity(intent);

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }
}
