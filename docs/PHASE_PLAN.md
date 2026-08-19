# Implementation Plan

## ✅ Phase 1: Foundation (Complete)

- Gradle/KMP project targets (Android, iOS, Web, Desktop)
- Shared fonts downloaded to `composeResources/font/` (Press Start 2P + Pixelify Sans)
- `material-icons-core` dependency wired
- Docs folder

## ✅ Phase 2: Design Tokens (Complete)

- `RetroPrimitiveColors` (paper + ink + arcade accents, light & dark)
- `RetroSpacing`, `RetroDimensions`, `RetroShapes`/`RetroShapeTokens`, `RetroElevation`, `RetroMotion`
- `RetroTypography` (pixel display + readable pixel-styled body)

## ✅ Phase 3: Theme (Complete)

- `RetroColors` semantic light + dark schemes
- `RetroTheme` token provider + Material3 bridge
- `retroHardShadow` modifier (signature hard offset shadow)

## ✅ Phase 4: Core Components (Complete)

- Controls: `RetroButton` (5 variants, loading, disabled, small, tactile press), `RetroIconButton`, `RetroChip`, `RetroTextField`, `RetroSwitch`, `RetroCheckbox`, `RetroRadio`
- Surfaces: `RetroCard` (+ header strip), `RetroDashedGroup`
- Feedback: `RetroProgressBar` (Health/Energy/Xp/Danger/Info, segments, value), `RetroStatusLabel`, `SpeechBubble`
- HUD: `StatHud`
- Foundations: `RetroText`, `RetroDivider`

## ✅ Phase 5: Showcase Screen (Complete)

- `DesignSystemScreen` — one scrollable screen rendering every token + component
- `App.kt` → `RetroTheme { DesignSystemScreen() }`

## 🔜 Phase 6: Icons & Doodles

- Retro icon set (bold, illustrative, dark outlines)
- Decorative doodles (hearts, lightning, stars, sparks) as Canvas composables

## 🔜 Phase 7: Navigation & App Shell

- App-level screens (home, etc.), top/bottom arcade bars
- Route definitions per target

## 🔜 Phase 8+: Features

Per Liquefied MVVM + Clean pattern (`feature/<name>` with `data/domain/presentation`): di, network, repository, use cases, ViewModels, StateFlow state.