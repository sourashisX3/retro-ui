# RetroUI — Architecture

Kotlin Multiplatform (Android, iOS, Web, Desktop) Compose app with the **retro pixel / hand-drawn game UI** design system.

## Overview

- **UI**: Compose Multiplatform (shared across all targets)
- **Design system**: custom retro kit (tokens + components) in `core/ui`
- **Architecture**: MVVM + Clean-style layering modeled after the Liquefied user app
- **Targets**: `androidApp`, `iosApp`, `desktopApp`, `webApp`, `shared`

## Module layout

```
RetroUI/
├── androidApp/  iosApp/  desktopApp/  webApp/      # platform entry points
├── shared/                                         # all shared code
│   └── src/commonMain/
│       ├── composeResources/font/                  # Press Start 2P + Pixelify Sans (shared)
│       └── kotlin/com/funapp/retroui/
│           ├── App.kt                              # RetroTheme { DesignSystemScreen() }
│           ├── core/
│           │   ├── config/
│           │   │   └── navigation/               # Route, AppNavHost (typed routes)
│           │   ├── data/
│           │   │   └── mock/                     # shared mock data (champion roster)
│           │   ├── di/                           # AppContainer, LocalAppContainer
│           │   ├── ui/
│           │   │   ├── token/                    # RetroPrimitiveColors, Spacing, Dimensions,
│           │   │   │                             # Shapes, Elevation, Motion, Typography
│           │   │   ├── theme/                    # RetroColors (light+dark), RetroTheme,
│           │   │   │                             # ThemeMode, SettingsRepository, SettingsStorage
│           │   │   ├── animation/                # retroEntrance styles (Rise/Pop/Coin/SlideLeft/Stomp), retroCascade, retroPopPress
│           │   │   ├── icons/                    # RetroIcons (Pixelarticons, generated)
│           │   │   ├── components/
│           │   │   │   ├── foundation/           # RetroText, RetroDivider, retroHardShadow,
│           │   │   │   │                         # retroTactilePress
│           │   │   │   ├── controls/             # RetroButton, IconButton, Chip, TextField,
│           │   │   │   │                         # Switch, Checkbox, Radio
│           │   │   │   ├── surfaces/             # RetroCard, RetroDashedGroup, RetroScreen
│           │   │   │   ├── feedback/             # RetroProgressBar, RetroStatusLabel, SpeechBubble
│           │   │   │   ├── game/                 # RetroGameCard, RetroBattleLog, RetroAvatar
│           │   │   │   ├── hud/                  # StatHud
│           │   │   │   ├── branding/             # RetroDuelLogo, RetroDecor, RetroOutlineText
│           │   │   │   └── navigation/           # RetroBottomBar
│           │   │   ├── placeholder/              # RetroPlaceholderScreen
│           │   │   ├── responsive/               # RetroWindowSize
│           │   │   └── DesignSystemScreen.kt     # showcase of every token + component
│           │   └── utils/                        # UiText, RetroTapFeedback (expect/actual)
│           └── feature/<name>/                   # features (data/domain/presentation)
└── docs/
```

## Design system access

Only valid inside `RetroTheme { }`:

```kotlin
RetroTheme.colors.primary        // semantic color
RetroTheme.typography.title      // Press Start 2P 16sp
RetroTheme.spacing.lg            // 16dp
RetroTheme.shapeTokens.button    // RoundedCornerShape(6dp)
RetroTheme.elevation.medium      // 4dp hard shadow
```

Components consume tokens only — feature code never uses raw colors/values.

## Theme

- `RetroTheme(darkTheme = isSystemInDarkTheme())` picks `LightRetroColors` / `DarkRetroColors`.
- `RetroTheme` bridges tokens into Material3 (`MaterialTheme`) so stock Material widgets adapt.
- Custom components (`Retro*`) render the signature retro look: ink outlines, hard offset shadows, tactile press.
- **Press feedback is two-tier**: hard-shadow surfaces collapse the shadow + sink via `retroTactilePress` (RetroButton behavior); shadowless compact controls pop via `retroPopPress`. Both share the component's `clickable` `InteractionSource` with `indication = null`.
- **Motion is arcade-styled**: `retroEntrance(style = Rise|Pop|Coin|SlideLeft|Stomp)` drives all screen/component entrances on `graphicsLayer` only (alpha/translation/scale — no layout reflow); cascades use `retroCascade(index)`. Screen transitions in `AppNavHost` use `RetroAnimation.arcadeOffset` (⅓-screen push) + `RetroAnimation.fade`, reversed on pop. All specs come from `RetroMotion`/`RetroAnimation` tokens.

## Fonts (shared resources)

Fonts live in `shared/src/commonMain/composeResources/font/` and are loaded via
`org.jetbrains.compose.resources.Font` in `buildRetroTypography()`:

- `press_start_2p.ttf` — pixel/arcade display (display, headline, title, label)
- `pixelify_sans_{regular,medium,semibold,bold}.ttf` — readable pixel-styled body

## Icons (RetroIcons)

Icons come from **Pixelarticons** (MIT) — flat single-path pixel art on a 24×24
grid — generated to Compose by the `dev.tonholo.s2c` Gradle plugin.

- Source SVGs: `icons/pixelarticons/` (repository root)
- Processor: `shared/build.gradle.kts` → `svgToCompose` block (maps kebab-case
  filenames to `RetroIcons.*` names, destination package
  `com.funapp.retroui.core.ui.icons`)
- Generated code: `shared/build/generated/svgToCompose/` (regenerated on build,
  never committed)
- Usage: `RetroIcons.X` (import both `RetroIcons` and the icon property)
- Adding an icon: drop `<name>.svg` into `icons/pixelarticons/`, add a
  `mapIconNameTo` entry if the Kotlin name should differ, rebuild.
- **Rule:** features use only `RetroIcons.*` — Material icons are not a dependency.

## Data flow (future features, per Liquefied pattern)

```
User Action → UI Event → ViewModel → UseCase → Repository → API / DB
                  ↑                                        │
                  └──────── StateFlow ─────────────────────┘
```

Each feature: `presentation → domain → data` (one-way dependency).

## Build & verify

- Android: `./gradlew :androidApp:assembleDebug`
- Desktop preview: `./gradlew :desktopApp:run`
- Shared compile checks: `./gradlew :shared:compileKotlinJvm`