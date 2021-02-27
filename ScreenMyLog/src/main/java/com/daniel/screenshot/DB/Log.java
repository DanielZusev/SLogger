package com.daniel.screenshot.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Logs")
public class Log {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "image_id")
    private int id;

    @ColumnInfo(name = "severity")
    private String severity;

    @ColumnInfo(name = "msg")
    private String log;

    @ColumnInfo(name = "screenshotId")
    private String screenshotId;

    public Log() {
    }

    public Log(String severity, String log, String screenshot) {
        this.severity = severity;
        this.log = log;
        this.screenshotId = screenshot;
    }

    public int getId() {
        return id;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getScreenshotId() {
        return screenshotId;
    }

    public void setScreenshotId(String screenshotId) {
        this.screenshotId = screenshotId;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", severity='" + severity + '\'' +
                ", log='" + log + '\'' +
                ", screenshotId='" + screenshotId + '\'' +
                '}';
    }
}
