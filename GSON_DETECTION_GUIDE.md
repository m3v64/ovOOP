# GSON Library Detection Verification Guide

This document helps you verify that the GSON library is properly detected in your development environment.

## Quick Verification

### Using Build Scripts

The easiest way to verify GSON detection:

**Linux/Mac:**
```bash
./build.sh
```

**Windows:**
```cmd
build.bat
```

If GSON is detected, you'll see:
```
✓ GSON library detected: lib/gson-2.13.2.jar
```

If GSON is missing, you'll see:
```
ERROR: GSON library not found at lib/gson-2.13.2.jar
```

## IDE-Specific Verification

### IntelliJ IDEA

1. Open the project in IntelliJ IDEA
2. Open any Java file that imports GSON (e.g., `src/ovOOP/DataSystem.java`)
3. Check that the import statements are NOT underlined in red:
   ```java
   import com.google.gson.Gson;
   import com.google.gson.GsonBuilder;
   ```
4. Navigate to **File → Project Structure → Libraries**
5. Verify that `gson-2.13.2` is listed

**If GSON is not detected:**
- Close and reopen the project
- Invalidate caches: **File → Invalidate Caches / Restart**
- Manually add: **File → Project Structure → Libraries → + → Java → Select lib/gson-2.13.2.jar**

### Eclipse

1. Open the project in Eclipse
2. Open **Project Explorer**
3. Expand **Referenced Libraries**
4. Verify that `gson-2.13.2.jar` is listed

**If GSON is not detected:**
- Refresh the project: Right-click project → **Refresh** (F5)
- Clean and rebuild: **Project → Clean**
- Manually add: Right-click project → **Build Path → Add External JARs** → Select `lib/gson-2.13.2.jar`

### VS Code

1. Open the project in VS Code
2. Open any Java file that imports GSON
3. Check that imports are not showing errors
4. Open **Java Projects** view (sidebar)
5. Expand **Referenced Libraries**
6. Verify that `gson-2.13.2.jar` is listed

**If GSON is not detected:**
- Reload window: **Ctrl+Shift+P** → **Developer: Reload Window**
- Check settings: **Ctrl+Shift+P** → **Java: Configure Java Runtime** → Verify `lib/**/*.jar` in **Referenced Libraries**
- Clean workspace: **Ctrl+Shift+P** → **Java: Clean Java Language Server Workspace**

## Manual Compilation Verification

### Test 1: Compile with GSON

```bash
# Linux/Mac
javac -cp "lib/*:src" src/ovOOP/*.java -d bin/

# Windows
javac -cp "lib/*;src" src\ovOOP\*.java -d bin\
```

**Expected result:** Compilation succeeds with no errors

### Test 2: Compile without GSON (Should Fail)

```bash
# Linux/Mac
javac -cp "src" src/ovOOP/*.java -d bin/

# Windows
javac -cp "src" src\ovOOP\*.java -d bin\
```

**Expected result:** Compilation fails with errors like:
```
error: package com.google.gson does not exist
import com.google.gson.Gson;
```

This confirms that the code requires GSON to compile.

## Runtime Verification

### Test 3: Run with GSON

```bash
# Linux/Mac
java -cp "bin:lib/*" ovOOP.Main

# Windows
java -cp "bin;lib/*" ovOOP.Main
```

**Expected result:** Application starts successfully and shows the login screen

### Test 4: Run without GSON (Should Fail)

```bash
# Linux/Mac
java -cp "bin" ovOOP.Main

# Windows
java -cp "bin" ovOOP.Main
```

**Expected result:** Runtime error:
```
Exception in thread "main" java.lang.NoClassDefFoundError: com/google/gson/Gson
```

## File System Verification

Verify that the GSON JAR file exists:

**Linux/Mac:**
```bash
ls -lh lib/gson-2.13.2.jar
```

**Windows:**
```cmd
dir lib\gson-2.13.2.jar
```

**Expected output:** Should show a file of approximately 284KB

Verify the JAR file is valid:

```bash
jar tf lib/gson-2.13.2.jar | head -5
```

**Expected output:** Should show contents like:
```
META-INF/
META-INF/MANIFEST.MF
com/
com/google/
com/google/gson/
```

## Common Issues

### Issue: "package com.google.gson does not exist"

**Cause:** GSON not in classpath during compilation

**Fix:** Use the build scripts or ensure `-cp "lib/*:src"` (or `lib/*;src` on Windows) is included in javac command

### Issue: "NoClassDefFoundError: com/google/gson/Gson"

**Cause:** GSON not in classpath during runtime

**Fix:** Ensure `-cp "bin:lib/*"` (or `bin;lib/*` on Windows) is included in java command

### Issue: IDE shows red underlines on GSON imports

**Cause:** IDE hasn't detected the library configuration files

**Fix:** 
- Reopen/reload the project
- Check that configuration files exist: `.classpath`, `.vscode/settings.json`, or `.idea/`
- Manually add the library in IDE settings

## Success Criteria

Your GSON library detection is working correctly if:

- ✅ Build scripts successfully detect GSON
- ✅ IDE shows no errors on GSON imports
- ✅ Project compiles without errors
- ✅ Application runs successfully
- ✅ GSON appears in IDE's Referenced Libraries section

## Getting Help

If GSON detection still doesn't work after trying these steps:

1. Check that `lib/gson-2.13.2.jar` exists and is not corrupted
2. Verify Java version is 17 or higher: `java -version`
3. Try the build scripts which include automatic verification
4. Refer to the README.md troubleshooting section
5. Open an issue on GitHub with details about your environment
