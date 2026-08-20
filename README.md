This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop (JVM).

* [/iosApp](./iosApp/iosApp) contains an iOS application. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* [/shared](./shared/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./shared/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./shared/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./shared/src/jvmMain/kotlin)
    folder is the appropriate location.

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these commands and options:

- Android app: `./gradlew :androidApp:assembleProdDebug`
- Desktop app:
  - Hot reload: `./gradlew :desktopApp:hotRun --auto`
  - Standard run: `./gradlew :desktopApp:run`
- Web app:
  - Wasm target (faster, modern browsers): `./gradlew :webApp:wasmJsBrowserDevelopmentRun`
  - JS target (slower, supports older browsers): `./gradlew :webApp:jsBrowserDevelopmentRun`
- iOS app: open the [/iosApp](./iosApp) directory in Xcode and run it from there.

Android flavors: `dev` (`com.funapp.retroui.dev`), `staging` (`com.funapp.retroui.staging`), `prod` (`com.funapp.retroui`).

### CI/CD

GitHub Actions workflows:

- [CI](.github/workflows/ci.yml) - builds Android (prod), JVM/Desktop, Web (JS + WASM) and links iOS frameworks on every PR/push.
- [Release](.github/workflows/release.yml) - on a `v*` tag (or manual dispatch): builds the signed prod AAB, desktop packages (Deb/Msi/Dmg), web production build, uploads the AAB to the Play Console internal track and the iOS app to TestFlight (both via fastlane), then creates a GitHub release with the artifacts.

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

### Running tests

Use the run button in your IDE's editor gutter, or run tests using Gradle tasks:

- Android tests: `./gradlew :shared:testAndroidHostTest`
- Desktop tests: `./gradlew :shared:jvmTest`
- Web tests:
  - Wasm target: `./gradlew :shared:wasmJsTest`
  - JS target: `./gradlew :shared:jsTest`
- iOS tests: `./gradlew :shared:iosSimulatorArm64Test`

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [YouTrack](https://youtrack.jetbrains.com/newIssue?project=CMP).