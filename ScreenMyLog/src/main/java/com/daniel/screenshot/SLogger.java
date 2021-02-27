package com.daniel.screenshot;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.daniel.screenshot.DB.AppDatabase;

import java.io.File;
import java.util.ArrayList;

public class SLogger {

    private static SLogger mInstance;
    private static ScreenshotUtil screenshotUtil;
    private static AppDatabase appDatabase;
    private static FileUtil fileUtil;
    private static ArrayList<com.daniel.screenshot.DB.Log> logs;
    private static final String TAG = "SLogger";

    private SLogger(Context context) {
        screenshotUtil = ScreenshotUtil.getInstance();
        appDatabase = AppDatabase.getInstance(context);
        fileUtil = FileUtil.getInstance();
        logs = new ArrayList<>();
    }

    public static SLogger getInstance(Context context) {
        if (mInstance == null) {
            synchronized (SLogger.class) {
                if (mInstance == null) {
                    mInstance = new SLogger(context);
                }
            }
        }
        return mInstance;
    }

    public void log(Activity activity, Severity severity, String msg, boolean screenshot) {
        switch (severity) {
            case INFO:
                infoLog(activity, msg, screenshot);
                break;
            case ERROR:
                errorLog(activity, msg, screenshot);
                break;
            case DEBUG:
                debugLog(activity, msg, screenshot);
                break;
        }
    }

    private void debugLog(Activity activity, String msg, boolean screenshot) {
        String fileName = "";
        if (screenshot) {
            fileName = screenshotUtil.takeScreenshotForScreen(activity);
        }
        Log.d(TAG, msg);
        com.daniel.screenshot.DB.Log log = new com.daniel.screenshot.DB.Log(Severity.DEBUG.toString(), msg, fileName);

        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.logDao().insertAll(log);
            }
        }).start();
    }

    private void errorLog(Activity activity, String msg, boolean screenshot) {
        String fileName = "";
        if (screenshot) {
            fileName = screenshotUtil.takeScreenshotForScreen(activity);
        }
        Log.e(TAG, msg);
        com.daniel.screenshot.DB.Log log = new com.daniel.screenshot.DB.Log(Severity.ERROR.toString(), msg, fileName);

        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.logDao().insertAll(log);
            }
        }).start();

    }

    private void infoLog(Activity activity, String msg, boolean screenshot) {
        String fileName = "";
        if (screenshot) {
            fileName = screenshotUtil.takeScreenshotForScreen(activity);
        }
        Log.i(TAG, msg);
        com.daniel.screenshot.DB.Log log = new com.daniel.screenshot.DB.Log(Severity.INFO.toString(), msg, fileName);

        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.logDao().insertAll(log);
            }
        }).start();
    }

    public void exportLogsToFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                logs = (ArrayList<com.daniel.screenshot.DB.Log>) appDatabase.logDao().getAll();
            }
        }).start();

        fileUtil.storeArrayList(logs);
    }

    public void cleanMyLogs() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.logDao().deleteAllLogs();
            }
        }).start();

        String path = Environment.getExternalStorageDirectory().toString() + "/Logs/logs.txt";
        File logsFile = new File(path);
        if (logsFile.exists()) {
            logsFile.delete();
        }
    }
}
