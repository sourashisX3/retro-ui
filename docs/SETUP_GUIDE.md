# Setup Guide

## Requirements

- JDK 17+
- Android SDK (compileSdk 36, minSdk 24)
- Xcode (for iOS target, optional)

## Running

| Target | Command |
|---|---|
| Android APK | `./gradlew :androidApp:assembleDebug` |
| Desktop app | `./gradlew :desktopApp:run` |
| Desktop hot reload | `./gradlew :desktopApp:hotRun --auto` |
| Web (wasm) | `./gradlew :webApp:wasmJsBrowserDevelopmentRun` |
| Web (js) | `./gradlew :webApp:jsBrowserDevelopmentRun` |
| iOS | open `iosApp/` in Xcode and run |

## Build checks

```bash
./gradlew :androidApp:assembleDebug   # Android + shared commonMain
./gradlew :shared:compileKotlinJvm    # Desktop/JVM shared
./gradlew :shared:jvmTest             # shared tests
```

## Fonts

Fonts are shared across all platforms via `shared/src/commonMain/composeResources/font/`:

- `press_start_2p.ttf` (display)
- `pixelify_sans_regular.ttf`, `pixelify_sans_medium.ttf`, `pixelify_sans_semibold.ttf`, `pixelify_sans_bold.ttf` (body)

They are loaded in `RetroTypography.kt` via `org.jetbrains.compose.resources.Font`.

## Icons

`org.jetbrains.compose.material:material-icons-core` (1.7.3) provides the bold icon set used by components. Upgrade with care — newer Compose versions may not publish this artifact at the CMP version number.