# RetroUI — Architecture

Kotlin Multiplatform (Android, iOS, Web, Desktop) Compose app with the **retro pixel / hand-drawn game UI** design system.

## Overview

- **UI**: Compose Multiplatform (shared across all targets)
- **Design system**: custom retro kit (tokens + components) in `core/design`
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
│           │   ├── design/
│           │   │   ├── token/                      # RetroPrimitiveColors, Spacing, Dimensions,
│           │   │   │                               # Shapes, Elevation, Motion, Typography
│           │   │   ├── theme/                      # RetroColors (light+dark), RetroTheme
│           │   │   ├── components/
│           │   │   │   ├── foundation/             # RetroText, RetroDivider, retroHardShadow
│           │   │   │   ├── controls/               # RetroButton, IconButton, Chip, TextField,
│           │   │   │   │                           # Switch, Checkbox, Radio
│           │   │   │   ├── surfaces/               # RetroCard, RetroDashedGroup
│           │   │   │   ├── feedback/               # RetroProgressBar, RetroStatusLabel, SpeechBubble
│           │   │   │   └── hud/                    # StatHud
│           │   │   └── DesignSystemScreen.kt       # showcase of every token + component
│           │   └── (di, network, utils → added with features)
│           └── feature/<name>/                     # future features (data/domain/presentation)
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

## Fonts (shared resources)

Fonts live in `shared/src/commonMain/composeResources/font/` and are loaded via
`org.jetbrains.compose.resources.Font` in `buildRetroTypography()`:

- `press_start_2p.ttf` — pixel/arcade display (display, headline, title, label)
- `pixelify_sans_{regular,medium,semibold,bold}.ttf` — readable pixel-styled body

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