#!/bin/bash
# Build script for ovOOP project

set -e  # Exit on error

echo "===================================="
echo "  ovOOP Build Script"
echo "===================================="
echo ""

# Check if GSON library exists
if [ ! -f "lib/gson-2.13.2.jar" ]; then
    echo "ERROR: GSON library not found at lib/gson-2.13.2.jar"
    echo "Please download GSON from https://github.com/google/gson/releases"
    echo "and place it in the lib/ directory."
    exit 1
fi

echo "✓ GSON library detected: lib/gson-2.13.2.jar"
echo ""

# Create bin directory if it doesn't exist
echo "Creating bin directory..."
mkdir -p bin

# Compile the project
echo "Compiling main Java source files..."
javac -cp "lib/*:src" src/ovOOP/*.java -d bin/

if [ $? -eq 0 ]; then
    echo "✓ Main source files compiled successfully"
    
    # Compile extras directory if it exists
    if [ -d "src/ovOOP/extras" ]; then
        echo "Compiling extras directory..."
        javac -cp "lib/*:src:bin" src/ovOOP/extras/*.java -d bin/
        if [ $? -eq 0 ]; then
            echo "✓ Extras compiled successfully"
        else
            echo "⚠ Warning: Extras compilation failed (non-critical)"
        fi
    fi
    
    echo ""
    echo "===================================="
    echo "  Build successful!"
    echo "===================================="
    echo ""
    echo "To run the application:"
    echo "  java -cp \"bin:lib/*\" ovOOP.Main"
    echo ""
else
    echo ""
    echo "===================================="
    echo "  Build failed!"
    echo "===================================="
    exit 1
fi
