# Quick Setup Guide - دليل الإعداد السريع

## English Version

### How to Open This Project in Android Studio

1. **Download and Extract:**
   - Download the ArabicCrossword folder
   - Extract it to your desired location

2. **Open in Android Studio:**
   - Launch Android Studio
   - Click "Open" or "Open an Existing Project"
   - Navigate to the ArabicCrossword folder
   - Click "OK"

3. **Wait for Gradle Sync:**
   - Android Studio will automatically sync the project
   - This may take a few minutes on first run
   - Wait for "Gradle sync finished" message

4. **Run the App:**
   - Click the green play button (▶️) at the top
   - Choose an emulator or connect your Android phone
   - The app will build and install automatically

### First Time Android Studio Setup

If you don't have Android Studio:

1. Download from: https://developer.android.com/studio
2. Install Android Studio
3. During setup, install Android SDK (API 24 or higher)
4. Create an Android Virtual Device (AVD) emulator
5. Then follow the steps above

### Minimum Requirements
- Android Studio Arctic Fox or later
- JDK 8+
- Android SDK 24 (Android 7.0) or higher
- 4GB RAM minimum, 8GB recommended

---

## النسخة العربية

### كيفية فتح المشروع في Android Studio

1. **التحميل والاستخراج:**
   - قم بتحميل مجلد ArabicCrossword
   - استخرجه إلى الموقع المطلوب

2. **افتح في Android Studio:**
   - شغل Android Studio
   - اضغط "Open" أو "Open an Existing Project"
   - انتقل إلى مجلد ArabicCrossword
   - اضغط "OK"

3. **انتظر Gradle Sync:**
   - سيقوم Android Studio بالمزامنة تلقائياً
   - قد يستغرق هذا بضع دقائق في المرة الأولى
   - انتظر رسالة "Gradle sync finished"

4. **شغل التطبيق:**
   - اضغط على زر التشغيل الأخضر (▶️) في الأعلى
   - اختر محاكي أو وصل هاتف Android الخاص بك
   - سيتم بناء التطبيق وتثبيته تلقائياً

### إعداد Android Studio للمرة الأولى

إذا لم يكن لديك Android Studio:

1. حمل من: https://developer.android.com/studio
2. ثبت Android Studio
3. خلال الإعداد، ثبت Android SDK (API 24 أو أعلى)
4. أنشئ محاكي Android Virtual Device (AVD)
5. ثم اتبع الخطوات أعلاه

### الحد الأدنى من المتطلبات
- Android Studio Arctic Fox أو أحدث
- JDK 8+
- Android SDK 24 (Android 7.0) أو أعلى
- 4GB RAM كحد أدنى، 8GB موصى به

---

## Common Issues / المشاكل الشائعة

**Problem:** Gradle sync failed
**Solution:** File → Invalidate Caches → Restart

**المشكلة:** فشل Gradle sync
**الحل:** File → Invalidate Caches → Restart

---

**Problem:** Unable to locate Android SDK
**Solution:** File → Project Structure → SDK Location → Set Android SDK path

**المشكلة:** تعذر تحديد موقع Android SDK
**الحل:** File → Project Structure → SDK Location → حدد مسار Android SDK

---

## Project Files Overview / نظرة عامة على ملفات المشروع

```
ArabicCrossword/
├── app/
│   ├── src/main/
│   │   ├── java/              # Java source code
│   │   ├── res/               # Resources (layouts, strings)
│   │   └── AndroidManifest.xml
│   └── build.gradle           # App-level build config
├── build.gradle               # Project-level build config
├── settings.gradle            # Gradle settings
└── README.md                  # Full documentation
```

## Contact & Support

For issues or questions, please check the README.md file for detailed documentation.

Good luck! / حظاً موفقاً! 🎉
