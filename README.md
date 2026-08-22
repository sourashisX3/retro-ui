# DECKRON — Retro UI

A multiplayer arcade card-duel game built with **Kotlin Multiplatform** and
**Compose Multiplatform** — one codebase, four platforms: **Android, iOS,
Web (JS + Wasm) and Desktop (JVM)**.

The entire UI is powered by **Retro UI**, the project's own design system:
a retro-arcade kit with flat colors, hard offset shadows, pixel typography,
tactile press feedback and a strict token layer — organized by Atomic Design
(Tokens → Atoms → Molecules → Organisms → Templates).

## Features

- **Card collection** with rarity tiers, search, sort and **gyro tilt** on
  Android (device-orientation tilt, toggleable in Settings)
- **Battle flow**: matchmaking → battle log → Stat HUDs → dialogs
- **Quests, profile, leaderboard** with badge grid and daily quests
- **Settings**: light/dark/system theme, sound, haptics, music and tilt
  toggles — persisted per platform
- **Type-colored toasts**, speech bubbles, loading/empty states
- Typed Navigation (`Route` + `AppNavHost`) with a retro bottom bar

## Design system

The kit lives in `shared/src/commonMain/kotlin/com/funapp/retroui/core/ui/`:

| What | Where |
| --- | --- |
| Design tokens (colors, type, spacing, shapes, motion) | [`core/ui/token`](./shared/src/commonMain/kotlin/com/funapp/retroui/core/ui/token) |
| Components (buttons, cards, HUD, dialog, toast, …) | [`core/ui/components`](./shared/src/commonMain/kotlin/com/funapp/retroui/core/ui/components) |
| Showcase (full kit on one scrollable screen, unrouted) | [`core/ui/DesignSystemScreen.kt`](./shared/src/commonMain/kotlin/com/funapp/retroui/core/ui/DesignSystemScreen.kt) |
| Studio previews (12 components) | [`core/ui/components/RetroComponentPreviews.kt`](./shared/src/commonMain/kotlin/com/funapp/retroui/core/ui/components/RetroComponentPreviews.kt) |
| Token sanity tests (palette pins, spacing grid, WCAG contrast) | [`shared/src/commonTest/.../TokenSanityTest.kt`](./shared/src/commonTest/kotlin/com/funapp/retroui/core/ui/token/TokenSanityTest.kt) |

- **[Design system docs](./docs/DESIGN_SYSTEM.md)** — tokens, component
  contract, guidelines
- **[Changelog](./docs/CHANGELOG.md)** — versioned history (currently v1.0.0)

Rules the system enforces: semantic colors only (no raw hex in features),
`RetroIcons` only (no Material/third-party icon sets), every screen on
`RetroScreen` (safe-area insets), every pressable tactile (120ms shadow
collapse + sink), keyboard focus rings and WCAG AA/AAA contrast enforced by
tests.

## Project structure

- [`/iosApp`](./iosApp/iosApp) — iOS entry point (SwiftUI shell + Compose UI)
- [`/shared`](./shared/src) — all shared code:
  - [`commonMain`](./shared/src/commonMain/kotlin) — cross-platform code
    (UI kit, features, navigation, DI, mock data)
  - `androidMain` / `iosMain` / `jvmMain` / `jsMain` / `wasmJsMain` —
    platform-specific code (settings storage, gyro sensor, tap feedback, …)
- [`/webApp`](./webApp/src) — web entry point (HTML shell + `main.kt`)
- [`/desktopApp`](./desktopApp/src) — desktop entry point
- [`/androidApp`](./androidApp) — Android application module
- [`/docs`](./docs) — design-system docs and changelog

## Running the apps

- Android app: `./gradlew :androidApp:assembleProdDebug`
- Desktop app:
  - Hot reload: `./gradlew :desktopApp:hotRun --auto`
  - Standard run: `./gradlew :desktopApp:run`
- Web app:
  - Wasm target (faster, modern browsers): `./gradlew :webApp:wasmJsBrowserDevelopmentRun`
  - JS target (slower, supports older browsers): `./gradlew :webApp:jsBrowserDevelopmentRun`
- iOS app: open the [/iosApp](./iosApp) directory in Xcode and run it from there.

Android flavors: `dev` (`com.funapp.retroui.dev`), `staging` (`com.funapp.retroui.staging`), `prod` (`com.funapp.retroui`).

## Running tests

- Android tests: `./gradlew :shared:testAndroidHostTest`
- Desktop tests: `./gradlew :shared:jvmTest`
- Web tests:
  - Wasm target: `./gradlew :shared:wasmJsTest`
  - JS target: `./gradlew :shared:jsTest`
- iOS tests: `./gradlew :shared:iosSimulatorArm64Test`

## CI/CD

GitHub Actions workflows:

- [CI](.github/workflows/ci.yml) — builds Android (prod), JVM/Desktop, Web (JS + WASM) and links iOS frameworks on every push to `main`/`develop`/`feature/**` and on PRs.
- [Release](.github/workflows/release.yml) — on a `v*` tag (or manual dispatch): builds the signed prod AAB, desktop packages (Deb/Msi/Dmg), web production build, uploads the AAB to the Play Console internal track and the iOS app to TestFlight (both via fastlane), then creates a GitHub release with the artifacts.

Fastlane lanes (run from the repo root): `fastlane android build`, `fastlane android internal`, `fastlane android staging`, `fastlane ios beta`.

Required repository secrets for releases:

| Secret | Purpose |
|--------|---------|
| `KEYSTORE_BASE64` | Base64-encoded Android release keystore (`.jks`) |
| `KEYSTORE_PASSWORD`, `KEY_ALIAS`, `KEY_PASSWORD` | Android keystore credentials |
| `PLAY_STORE_SERVICE_ACCOUNT_JSON` | GCP service account JSON for Play Console uploads |
| `MATCH_GIT_URL`, `MATCH_PASSWORD` | Private repo + passphrase for iOS certs/profiles (fastlane match) |
| `APPLE_ID`, `APPLE_TEAM_ID` | Apple Developer account |
| `APP_STORE_CONNECT_API_KEY_KEY_ID` / `_ISSUER_ID` / `_BASE64` | App Store Connect API key for TestFlight |

Releases without these secrets still build and upload artifacts to GitHub; store deploys are skipped.

## Branch workflow

- `main` — production releases
- `develop` — integration branch; feature branches (`feature/*`) merge here
  with `--no-ff` merge commits