#!/bin/bash
# Run script for ovOOP project

# Check if project is compiled
if [ ! -d "bin" ] || [ ! -f "bin/ovOOP/Main.class" ]; then
    echo "Project not compiled. Running build script..."
    echo ""
    ./build.sh
    echo ""
fi

# Check if GSON library exists
if [ ! -f "lib/gson-2.13.2.jar" ]; then
    echo "ERROR: GSON library not found at lib/gson-2.13.2.jar"
    exit 1
fi

# Run the application
java -cp "bin:lib/*" ovOOP.Main
