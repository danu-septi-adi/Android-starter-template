# Tech Stack Config

> **Dibuat:** {{tanggal}}
> **Project:** {{project_name}}
> **Stack:** Android Native (Kotlin + Jetpack Compose)

---

## Bahasa & Build
| Item | Value |
|------|-------|
| Bahasa | **Kotlin** 2.1.0 |
| Build System | **Gradle** 8.11.1 + Kotlin DSL |
| Version Catalog | `gradle/libs.versions.toml` |
| Config Gateway | `project-config.json` (satu file untuk semua konfigurasi) |

## Minimum Versions
| SDK | Versi |
|-----|-------|
| compileSdk | 35 (Android 15) |
| minSdk | 26 (Android 8) |
| targetSdk | 35 (Android 15) |

## UI Framework
| Library | Fungsi | Catatan |
|---------|--------|---------|
| Jetpack Compose BOM 2024.12 | UI toolkit | Wajib |
| Material3 | Design system | Wajib, dynamic color support |
| Navigation Compose 2.8.5 | Screen routing | Wajib |
| Material Icons Extended | Icon library | Opsional — hemat size jika tidak dipakai |
| Coil 2.7.0 | Image loading | Wajib untuk gambar dari network |

## Architecture
| Layer | Pattern | Lokasi |
|-------|---------|--------|
| UI | Composable + ViewModel + StateFlow | `ui/` |
| Data | Repository + Retrofit Service | `data/` |
| DI | Manual DI (AppContainer) | `di/` |
| Navigation | NavHost + sealed route | `navigation/` |

## Networking
| Library | Fungsi |
|---------|--------|
| Retrofit 2.11.0 | HTTP client (interface-based) |
| OkHttp 4.12.0 | HTTP engine (dengan logging interceptor) |
| Kotlin Serialization 1.7.3 | JSON parsing (nullable-safe) |

## Async & State
| Library | Fungsi |
|---------|--------|
| Kotlin Coroutines 1.9.0 | Async operations |
| StateFlow | Reactive state (di ViewModel) |
| UiState sealed interface | Loading / Success / Error wrapper |

## Storage (opsional — tambah sesuai kebutuhan)
| Butuh? | Library | Lokasi Config |
|--------|---------|---------------|
| [ ] | Room DB | `libs.versions.toml` → add `room-runtime`, `room-ktx` |
| [ ] | DataStore | `libs.versions.toml` → add `datastore-preferences` |

## Firebase (opsional)
| Butuh? | Service |
|--------|---------|
| [ ] | Authentication |
| [ ] | Firestore / Realtime DB |
| [ ] | Cloud Messaging (push notif) |
| [ ] | Crashlytics |
| [ ] | Analytics |

---

## Cara nambah dependency

1. Buka `gradle/libs.versions.toml`
2. Tambah version + library
3. Buka `app/build.gradle.kts` → tambah `implementation(libs.nama.library)`
4. Sync Gradle
