# Android Kotlin + Jetpack Compose Starter Pack

Template + AI workflow untuk develop Android app: **dari ide → running app**.

Dua sistem dalam satu repo:

| Sistem | Fungsi |
|--------|--------|
| **`Aisetup/`** | Workflow AI — define fitur, elaborasi, planning, memory |
| **`app/`** | Kode template — langsung jalan, tinggal isi logic |

Keduanya terhubung via **`project-config.json`** — satu file isi konfigurasi, semua build file nyesuai.

---

## 📦 Cara Install

### Prasyarat (Wajib — dipake di kedua metode)
- **JDK 17** — [Download Temurin JDK 17](https://adoptium.net/temurin/releases/?version=17)
- **Git** — `winget install Git.Git`
- **Koneksi internet** — first sync download ~200 MB

---

### Opsi A: Pakai Android Studio ✅ (Termudah)

| Komponen | Status |
|----------|--------|
| Android Studio Ladybug+ | Wajib install |
| Android SDK | Built-in di AS |
| JDK | Built-in di AS |

**Langkah:**

1. **Clone repo:**
   ```bash
   git clone https://github.com/danu-septi-adi/Android-starter-template.git
   cd Android-starter-template
   ```

2. **Edit konfigurasi** — buka `project-config.json`:
   ```json
   {
     "project": {
       "name": "NamaProject",
       "appName": "Nama Aplikasi"
     },
     "android": {
       "namespace": "com.perusahaan.app",
       "applicationId": "com.perusahaan.app",
       "compileSdk": 35,
       "minSdk": 26,
       "targetSdk": 35,
       "versionCode": 1,
       "versionName": "1.0.0"
     },
     "api": {
       "baseUrl": "https://api.example.com/",
       "timeoutSeconds": 30
     }
   }
   ```

3. **Rename package folder:**
   ```bash
   # Contoh: namespace com.mycompany.myapp
   # Rename: app/src/main/java/com/template/app/ → app/src/main/java/com/mycompany/myapp/
   ```

4. **Buka di Android Studio — File → Open → pilih folder → Run ▶️**

---

### Opsi B: Tanpa Android Studio (CLI-only) 🚀

| Komponen | Ukuran | Install | Wajib? |
|----------|--------|---------|--------|
| JDK 17 | ~200 MB | `winget install EclipseAdoptium.Temurin.17.JDK` | ✅ |
| Android SDK cmdline-tools | ~100 MB | [Download](https://developer.android.com/studio#command-line-tools-only) | ✅ |
| SDK platform 35 | ~50 MB | via `sdkmanager` | ✅ |
| SDK build-tools | ~50 MB | via `sdkmanager` | ✅ |
| platform-tools (ADB) | ~10 MB | via `sdkmanager` | ✅ kalo deploy |
| Gradle | include di repo | pake `./gradlew` | ✅ |
| Android Studio | ~1.5 GB | ❌ **Gak perlu** | ❌ |

**Langkah 1 — Install JDK 17:**

```bash
# Cek versi Java
java -version  # kalo >= 17, skip

# Install pake winget (Windows)
winget install EclipseAdoptium.Temurin.17.JDK

# Manual: download dari https://adoptium.net/temurin/releases/?version=17
```

**Langkah 2 — Install Android SDK command-line tools:**

Download dari: https://developer.android.com/studio#command-line-tools-only

```bash
# Ekstrak ke C:\Android\cmdline-tools\
# Struktur final harus:
#   C:\Android\cmdline-tools\latest\bin\sdkmanager.bat

# Set environment variable
setx ANDROID_HOME "C:\Android"

# Install SDK components
sdkmanager "platforms;android-35" "build-tools;35.0.0" "platform-tools"

# Verifikasi ADB
adb devices
```

**Langkah 3 — Clone + konfigurasi:**

```bash
git clone https://github.com/danu-septi-adi/Android-starter-template.git
cd Android-starter-template

# Set SDK path
echo sdk.dir=C\:\\Android > local.properties

# Edit project-config.json (nama, package, API)
notepad project-config.json
```

**Langkah 4 — Build APK:**

```bash
ANDROID_HOME=C:/Android ./gradlew assembleDebug
```

**Langkah 5 — Install ke device:**

```bash
# Connect device via USB (enable USB debugging)
adb install -r app/build/outputs/apk/debug/app-debug.apk

# Kalo udah terinstall sebelumnya, cukup launch:
adb shell am start -n com.yourpackage.debug/com.yourpackage.MainActivity
```

**Kalo build error `mipmap/ic_launcher not found`** — jalankan dari direktori yang bener (root project, bukan `app/`). Error ini udah di-fix di template.

---

### Konfigurasi `project-config.json` (Common buat kedua opsi)

| Field | Fungsi | Contoh |
|-------|--------|--------|
| `project.name` | Nama project (settings.gradle) | `MyApp` |
| `project.appName` | Nama di launcher Android | `My App` |
| `android.namespace` | Package name / source folder | `com.perusahaan.app` |
| `android.applicationId` | ID unik di Play Store | `com.perusahaan.app` |
| `android.compileSdk` | SDK versi buat compile | `35` |
| `android.minSdk` | Minimal Android version | `26` (Android 8) |
| `android.targetSdk` | Target Android version | `35` |
| `android.versionCode` | Build number (integer) | `1` |
| `android.versionName` | Versi yang keliatan user | `1.0.0` |
| `api.baseUrl` | Base URL REST API | `https://api.example.com/` |
| `api.timeoutSeconds` | Timeout HTTP request | `30` |

> **Default API:** `https://jsonplaceholder.typicode.com/` — demo API gratis. Ganti kalo pake backend sendiri.

---

## ✅ Hasil

Jika berhasil, di device/emulator akan muncul:

- **Top bar:** "Template App"
- **List:** Menampilkan data dari JSONPlaceholder API
- **Tap item:** Navigasi ke halaman detail
- **Refresh:** Tombol refresh di top bar

Template sudah teruji: **build sukses, install via ADB, API call berjalan, 0 crash.**

---

## 🚀 Cara Kerja — Gambaran Besar

```
Ide kamu
    ↓ (isi userplan.md)
┌─ Phase 1: Feature Mapping ──────────────────┐
│  AI tanya → kamu jawab → fix → implement    │
│  Output: Aisetup/docs/implementfeature.md    │
└──────────────────────────────────────────────┘
    ↓
┌─ Phase 2: Feature Elaboration ──────────────┐
│  AI bedah tiap fitur → flow, UI, data model  │
│  Output: Aisetup/docs/detailfeature.md       │
└──────────────────────────────────────────────┘
    ↓
┌─ Phase 3: Planning ─────────────────────────┐
│  AI breakdown task → mapping ke file template│
│  Output: Aisetup/docs/planning_todolist.md   │
└──────────────────────────────────────────────┘
    ↓
┌─ Phase 4: Setup Memory ─────────────────────┐
│  tech_stack, file_map, command_log, state    │
│  Output: Aisetup/config/*.md                │
└──────────────────────────────────────────────┘
    ↓
┌─ Phase 5: Coding ───────────────────────────┐
│  AI generate kode ke app/ + isi config.json  │
│  Tinggal sync gradle + run                   │
└──────────────────────────────────────────────┘
    ↓
RUNNING APP 🚀
```

---

## 🧠 Pakai AI Workflow (Recommended)

1. Buka file **`Aisetup/docs/userplan.md`**
2. Isi deskripsi aplikasi kamu
3. Kirim ke AI (Claude, ChatGPT, dll)
4. AI akan pandu dari **Phase 1** sampai kode jadi

---

## 📁 Struktur Lengkap

```
├── project-config.json          ← SATU FILE KONFIGURASI
├── settings.gradle.kts          ← baca project name dari config
├── build.gradle.kts             ← plugins
├── gradle.properties            ← JVM, AndroidX flags
├── gradle/
│   ├── libs.versions.toml       ← version catalog
│   └── wrapper/
│
├── Aisetup/                     ← AI WORKFLOW
│   ├── docs/
│   │   ├── userplan.md          ← [isi] deskripsi ide kamu
│   │   ├── feature.md           ← [generate AI] tanya jawab fitur
│   │   ├── implementfeature.md  ← [final] fitur final disepakati
│   │   ├── elaborationfitur.md  ← [generate AI] detail teknis
│   │   ├── detailfeature.md     ← [final] blueprint final
│   │   └── planning_todolist.md ← [generate AI] task tracker
│   └── config/
│       ├── tech_stack_config.md ← pre-filled stack template
│       ├── feature_to_file_map.md ← mapping fitur → file
│       ├── ai_command.md        ← [log] semua CLI command
│       └── ai_memory.md         ← [state] otak AI, update tiap sesi
│
└── app/                         ← KODE TEMPLATE
    ├── build.gradle.kts          ← baca SDK, package, version dari config
    ├── proguard-rules.pro
    └── src/main/
        ├── AndroidManifest.xml
        ├── res/values/
        └── java/{namespace}/
            ├── MainActivity.kt          / edge-to-edge + setContent
            ├── TemplateApplication.kt
            ├── navigation/NavGraph.kt
            ├── di/AppContainer.kt
            ├── data/
            │   ├── model/{UiState,SampleItem}.kt
            │   ├── remote/{ApiService,RetrofitInstance}.kt
            │   └── repository/SampleRepository.kt
            └── ui/
                ├── theme/{Color,Type,Theme}.kt
                ├── components/            ← reusable
                └── screens/{home,detail}/ ← demo
```

---

## ⚙️ Config Mapping

| `project-config.json` | Dipakai di |
|---|---|
| `project.name` | `settings.gradle.kts` → rootProject.name |
| `project.appName` | `app/build.gradle.kts` → resValue → @string/app_name |
| `android.namespace` | Build namespace |
| `android.applicationId` | Play Store ID |
| `android.compileSdk / minSdk / targetSdk` | SDK versions |
| `android.versionCode / versionName` | Versioning |
| `api.baseUrl` | `BuildConfig.BASE_URL` → Retrofit |
| `api.timeoutSeconds` | `BuildConfig.TIMEOUT_SECONDS` → OkHttp |

---

## 🛠 Tech Stack

| Library | Versi | Fungsi |
|---------|-------|--------|
| Kotlin | 2.1.0 | Bahasa pemrograman |
| Compose BOM | 2024.12 | UI toolkit |
| Material3 | built-in | Design system (Material You) |
| Navigation Compose | 2.8.5 | Screen routing |
| Retrofit + OkHttp | 2.11.0 / 4.12.0 | HTTP client + logging |
| Kotlin Serialization | 1.7.3 | JSON parsing (nullable-safe) |
| Coil | 2.7.0 | Image loading |
| Coroutines | 1.9.0 | Async operations |
| Lifecycle ViewModel | 2.8.7 | State management |

---

## 📸 Screenshot

App sudah teruji di device fisik (Realme, Android 14):

- ✅ Build sukses (0 warning)
- ✅ Install via ADB berhasil
- ✅ API call ke JSONPlaceholder sukses (100 items)
- ✅ Navigasi list → detail jalan
- ✅ Dark mode support (ikut system)
- ✅ Dynamic color (Android 12+)
- ✅ 0 crash / FATAL

---

## 🔧 Troubleshooting

| Masalah | Solusi |
|---------|--------|
| `SDK location not found` | Android Studio akan generate `local.properties` otomatis. Atau buat manual: `sdk.dir=C\:\\Users\\[user]\\AppData\\Local\\Android\\Sdk` |
| `Gradle sync failed` | Cek koneksi internet. First sync download Gradle 8.11.1 (~100MB) |
| `Namespace wrong` | Ganti `android.namespace` di `project-config.json` + rename folder package |
| `API not loading` | Cek `baseUrl` di config, pastikan API nyala. Default pake JSONPlaceholder |
| `App crash` | Jalankan via Android Studio, cek logcat. Laporkan issue ke GitHub |

---

## 📄 License

MIT — bebas dipake untuk project apapun.
