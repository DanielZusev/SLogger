package com.daniel.screenshot;

import android.os.Environment;

import com.daniel.screenshot.DB.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class FileUtil {

    private static FileUtil mInstance;

    private FileUtil() {

    }

    public static FileUtil getInstance() {
        if (mInstance == null) {
            synchronized (FileUtil.class) {
                if (mInstance == null) {
                    mInstance = new FileUtil();
                }
            }
        }
        return mInstance;
    }

    public void storeArrayList(ArrayList<Log> arrayList) {
        try {
            String path = Environment.getExternalStorageDirectory().toString() + "/Logs/logs.txt";
            File logsFile = new File(path);
            if (logsFile.exists()) {
                logsFile.delete();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(logsFile);

            for (int i = 0; i < arrayList.size(); i++) {
                fileOutputStream.write(String.format(arrayList.get(i).toString() + "\n").getBytes());
            }
            fileOutputStream.flush();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
