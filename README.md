# Android Kotlin + Jetpack Compose Starter Pack

Template + AI workflow untuk develop Android app: **dari ide → running app**.

Dua sistem dalam satu repo:

| Sistem | Fungsi |
|--------|--------|
| **`Aisetup/`** | Workflow AI — define fitur, elaborasi, planning, memory |
| **`app/`** | Kode template — langsung jalan, tinggal isi logic |

Keduanya terhubung via **`project-config.json`** — satu file isi konfigurasi, semua build file nyesuai.

---

## 📦 Cara Install (4 Langkah)

### Prasyarat
- **Android Studio** Ladybug+ (2024.1+) atau versi terbaru
- **JDK 17** (built-in di Android Studio)
- **Android SDK** (API 35 disarankan, minimal API 26)
- **Git**

### Langkah 1 — Clone repository

```bash
git clone https://github.com/danu-septi-adi/Android-starter-template.git
cd Android-starter-template
```

### Langkah 2 — Setup konfigurasi

Buka file **`project-config.json`** di root project, edit semua yang perlu:

```json
{
  "project": {
    "name": "NamaProject",       // → settings.gradle rootProject.name
    "appName": "Nama Aplikasi"   // → title di Android launcher
  },
  "android": {
    "namespace": "com.perusahaan.app",     // → source package
    "applicationId": "com.perusahaan.app", // → Play Store ID
    "compileSdk": 35,      // versi SDK kompilasi
    "minSdk": 26,          // minimal Android versi berapa
    "targetSdk": 35,       // target SDK
    "versionCode": 1,      // naikin tiap upload Play Store
    "versionName": "1.0.0" // versi yang keliatan ke user
  },
  "api": {
    "baseUrl": "https://api.example.com/", // ganti dengan API lo
    "timeoutSeconds": 30
  }
}
```

Template langsung pake **JSONPlaceholder** sebagai demo API — ganti `baseUrl` ke endpoint lo sendiri.

### Langkah 3 — Rename package folder

Rename folder `app/src/main/java/com/template/app/` → sesuai `android.namespace` di config.

> **Contoh:** namespace `com.mycompany.myapp` → folder jadi `app/src/main/java/com/mycompany/myapp/`

### Langkah 4 — Buka di Android Studio

1. Buka **Android Studio**
2. **File → Open** → pilih folder `Android-starter-template`
3. Android Studio akan otomatis:
   - Generate file `local.properties` (path SDK)
   - Download **Gradle 8.11.1** (first sync butuh internet)
   - Download dependencies (first sync butuh internet ~2-5 menit)
4. Klik **Run ▶️** atau tekan **Shift+F10**

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
