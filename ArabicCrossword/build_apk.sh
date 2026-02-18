#!/bin/bash
# Arabic Crossword APK Builder
# This script helps you build the APK easily

echo "=========================================="
echo "Arabic Crossword APK Builder"
echo "=========================================="
echo ""

# Check if we're in the right directory
if [ ! -f "settings.gradle" ]; then
    echo "ERROR: Please run this script from the ArabicCrossword project root directory"
    exit 1
fi

echo "Step 1: Cleaning previous builds..."
rm -rf app/build
rm -rf build

echo "Step 2: Making gradlew executable..."
chmod +x gradlew 2>/dev/null || echo "gradlew not found, will be created by Gradle wrapper"

echo "Step 3: Building APK..."
echo "This may take a few minutes on first run..."
echo ""

# Try to build with gradle wrapper
if [ -f "gradlew" ]; then
    ./gradlew assembleDebug
else
    # If gradlew doesn't exist, try with system gradle
    gradle assembleDebug 2>/dev/null || gradle wrapper && ./gradlew assembleDebug
fi

BUILD_RESULT=$?

echo ""
if [ $BUILD_RESULT -eq 0 ]; then
    echo "=========================================="
    echo "✓ BUILD SUCCESSFUL!"
    echo "=========================================="
    echo ""
    echo "Your APK is ready at:"
    echo "app/build/outputs/apk/debug/app-debug.apk"
    echo ""
    echo "File size:"
    ls -lh app/build/outputs/apk/debug/app-debug.apk 2>/dev/null | awk '{print $5}'
    echo ""
    echo "To install on your device:"
    echo "  adb install app/build/outputs/apk/debug/app-debug.apk"
    echo ""
else
    echo "=========================================="
    echo "✗ BUILD FAILED"
    echo "=========================================="
    echo ""
    echo "Common solutions:"
    echo "1. Make sure you have JDK 8 or higher installed"
    echo "2. Make sure JAVA_HOME is set correctly"
    echo "3. Check that you have internet connection (for first build)"
    echo "4. Try: ./gradlew clean build"
    echo ""
    echo "Or use Android Studio:"
    echo "  Build → Build Bundle(s) / APK(s) → Build APK(s)"
    echo ""
fi
