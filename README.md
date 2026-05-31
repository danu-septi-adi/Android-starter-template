# Android Kotlin + Jetpack Compose Starter Pack

Template + AI workflow untuk develop Android app: **dari ide → running app**.

Dua sistem dalam satu repo:

| Sistem | Fungsi |
|--------|--------|
| **`Aisetup/`** | Workflow AI — define fitur, elaborasi, planning, memory |
| **`app/`** | Kode template — langsung jalan, tinggal isi logic |

Keduanya terhubung via **`project-config.json`** — satu file isi konfigurasi, semua build file nyesuai.

---

## Cara kerja — gambaran besar

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

## Quick start (tanpa AI workflow)

1. Buka `project-config.json` — edit nama, package, API base URL
2. Rename folder `com/template/app/` → sesuai namespace baru
3. Buka di **Android Studio**, sync Gradle, run

Template demo langsung jalan pake JSONPlaceholder API.

---

## Quick start (pakai AI workflow — recomended)

Buka file `Aisetup/docs/userplan.md`, isi deskripsi aplikasi kamu, lalu kirim ke AI. AI akan pandu dari Phase 1 sampai kode jadi.

---

## Struktur lengkap

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

## Config mapping

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

## Tech stack

| Library | Versi |
|---------|-------|
| Kotlin | 2.1.0 |
| Compose BOM | 2024.12 |
| Material3 | built-in |
| Navigation Compose | 2.8.5 |
| Retrofit + OkHttp | 2.11.0 / 4.12.0 |
| Kotlin Serialization | 1.7.3 |
| Coil | 2.7.0 |
| Coroutines | 1.9.0 |
| Lifecycle ViewModel | 2.8.7 |

---

## License

MIT
