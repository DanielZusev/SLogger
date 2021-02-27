[![](https://jitpack.io/v/DanielZusev/SLogger.svg)](https://jitpack.io/#DanielZusev/SLogger)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# SLogger

Customized logger library with additional screenshot option.
All logs saved internally by Room sql, and screenshot saved inside phone directory.

## Setup
#### Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
#### Add the dependency
```
dependencies {
	        implementation 'com.github.DanielZusev:SLogger:1.00.01'
	}
```

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
## License

    Copyright 2021 Daniel Zusev

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
