# Feature → File Mapping

> **Cache system:** AI cukup baca file ini untuk tahu file mana berkaitan dengan fitur apa.
> Update setiap kali ada file baru.

---

## Template Base (built-in — semua project punya ini)

```
project-root/
├── project-config.json                        → Gateway config semua value
├── settings.gradle.kts                        → rootProject.name (dari config)
├── app/build.gradle.kts                       → BuildConfig + dependency (dari config)
├── app/src/main/AndroidManifest.xml            → app_name via resValue
└── app/src/main/java/{namespace}/
    ├── MainActivity.kt                        → Entry point, edge-to-edge, setContent
    ├── TemplateApplication.kt                  → Application class
    ├── navigation/NavGraph.kt                  → Router semua screen
    ├── di/AppContainer.kt                      → Manual DI
    ├── data/
    │   ├── model/UiState.kt                    → Loading/Success/Error sealed interface
    │   ├── model/SampleItem.kt                 → @Serializable model (ganti sesuai project)
    │   ├── remote/ApiService.kt                → Retrofit interface
    │   ├── remote/RetrofitInstance.kt           → OkHttp + Retrofit singleton (dari config)
    │   └── repository/SampleRepository.kt       → Repository (ganti sesuai project)
    └── ui/
        ├── theme/{Color,Type,Theme}.kt         → Material3 + dynamic color
        ├── components/AppTopBar.kt             → Reusable top bar
        ├── components/LoadingIndicator.kt      → Loading spinner
        ├── components/ErrorDialog.kt           → Error state + dialog
        └── screens/
            ├── home/{HomeScreen,HomeViewModel}.kt    → Demo list screen
            └── detail/{DetailScreen,DetailViewModel}.kt  → Demo detail screen
```

---

## Feature Mapping (isi setelah blueprint fix)

### Feature: Autentikasi
| File | Peran |
|------|-------|
| `data/model/LoginRequest.kt` | Request body |
| `data/model/User.kt` | Response model |
| `data/remote/AuthApi.kt` | Retrofit endpoints |
| `data/repository/AuthRepository.kt` | Auth logic |
| `ui/screens/auth/LoginScreen.kt` | Login UI |
| `ui/screens/auth/RegisterScreen.kt` | Register UI |
| `ui/screens/auth/AuthViewModel.kt` | Auth state |
| `navigation/NavGraph.kt` | + route auth |

### Feature: [Nama Fitur 1]
| File | Peran |
|------|-------|
| `data/model/Fitur1Item.kt` | |
| `data/remote/Fitur1Api.kt` | |
| `data/repository/Fitur1Repository.kt` | |
| `ui/screens/fitur1/...` | |

### Feature: [Nama Fitur 2]
| File | Peran |
|------|-------|
| ... | |
