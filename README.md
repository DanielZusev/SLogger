# SLogger

Customized logger library with additional screenshot option.
All logs saved internally by Room sql, and screenshot saved inside phone directory.

## Setup

## Usage
### First get an SLogger instance:
```
SLogger sLogger = SLogger.getInstance(this);
```

### Log examples: 
```
 sLogger.log(MainActivity.this, Severity.INFO, "info log", false);
 sLogger.log(MainActivity.this, Severity.ERROR, "error log", true);
 sLogger.log(MainActivity.this, Severity.DEBUG, "debug log", true);
```

### Export logs to file:
```
 sLogger.exportLogsToFile();
```

### Delete all logs from db & delete logs file:
```
sLogger.cleanMyLogs();
```
