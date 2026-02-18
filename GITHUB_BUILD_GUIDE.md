# Build APK Using GitHub Actions (FREE & AUTOMATIC)
# بناء APK باستخدام GitHub Actions (مجاني وتلقائي)

## Why Use This Method? / لماذا هذه الطريقة؟

✅ **FREE** - No cost / مجاني
✅ **NO SOFTWARE NEEDED** - Build online / لا حاجة لبرامج
✅ **AUTOMATIC** - Builds on every update / تلقائي
✅ **NO GRADLE ERRORS** - Uses cloud servers / بدون أخطاء Gradle

---

## Step-by-Step Guide / دليل خطوة بخطوة

### Step 1: Create GitHub Account / إنشاء حساب GitHub

1. Go to: https://github.com
2. Click "Sign up"
3. Create free account

### Step 2: Create New Repository / إنشاء مستودع جديد

1. Click the **+** icon (top right)
2. Select "New repository"
3. Name it: `arabic-crossword`
4. Choose: **Public**
5. Click "Create repository"

### Step 3: Upload Your Project / رفع المشروع

**Option A: Using Web Interface (Easiest)**

1. Extract the `ArabicCrossword.zip` file
2. On GitHub repository page, click "uploading an existing file"
3. Drag ALL folders and files from ArabicCrossword folder
4. Click "Commit changes"

**Option B: Using Git Command Line**

```bash
cd ArabicCrossword
git init
git add .
git commit -m "Initial commit"
git remote add origin https://github.com/YOUR_USERNAME/arabic-crossword.git
git push -u origin main
```

### Step 4: Automatic Build Starts / يبدأ البناء التلقائي

1. Go to **Actions** tab in your repository
2. You'll see "Build Android APK" workflow running
3. Wait 3-5 minutes for build to complete
4. ✅ Green checkmark = Success!

### Step 5: Download Your APK / تحميل ملف APK

1. Click on the completed workflow run
2. Scroll down to **Artifacts**
3. Click **arabic-crossword-apk** to download
4. Extract the ZIP file
5. Inside is your `app-debug.apk` 🎉

---

## Files I've Added for GitHub Build

Your updated project now includes:

```
ArabicCrossword/
├── .github/
│   └── workflows/
│       └── build.yml          ← Automatic build configuration
├── gradlew                     ← Gradle wrapper script
├── gradle/
│   └── wrapper/
│       └── gradle-wrapper.properties
└── ... (rest of your files)
```

These files tell GitHub how to build your APK automatically!

---

## How to Get Updates / كيفية الحصول على التحديثات

Every time you make changes:

1. Upload new files to GitHub (or push with Git)
2. GitHub Actions builds new APK automatically
3. Download from Actions → Artifacts

---

## Alternative: Download Pre-Built APK

If you just want the APK NOW without setup:

### Use AppGyver Build Service:
1. Go to: https://www.appgyver.com
2. Sign up (free)
3. Upload project
4. Click "Build"
5. Download APK

### Use Expo Build Service:
1. Install: `npm install -g eas-cli`
2. Run: `eas build --platform android`
3. Download APK from link provided

---

## Troubleshooting GitHub Actions

**Build fails?**
- Check the "Actions" tab for error details
- Common fix: Make sure all files were uploaded
- Verify `gradlew` has execution permissions

**No Artifacts?**
- Build must complete successfully (green checkmark)
- Scroll down on the workflow run page
- Artifacts appear at bottom

**Can't find APK?**
- Click on artifact name to download ZIP
- Extract ZIP file
- APK is inside the extracted folder

---

## Summary / الملخص

**English:**
1. Create GitHub account
2. Create repository
3. Upload ArabicCrossword files
4. Wait for automatic build (3-5 min)
5. Download APK from Actions → Artifacts

**Arabic:**
1. أنشئ حساب GitHub
2. أنشئ مستودع
3. ارفع ملفات ArabicCrossword
4. انتظر البناء التلقائي (3-5 دقائق)
5. حمل APK من Actions → Artifacts

---

## What Next?

Once you have the APK:

1. **Transfer to Android phone:**
   - Email it to yourself
   - Use Google Drive
   - USB transfer

2. **Install APK:**
   - Enable "Unknown sources" in Settings
   - Tap the APK file
   - Click "Install"

3. **Play the game!** 🎮

---

## Need Help?

Tell me if you want me to:
- Walk you through any step
- Create video instructions
- Set up a different build method
- Fix specific errors you're seeing

I'm here to help! 😊
