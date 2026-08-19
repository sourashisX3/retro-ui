# Implementation Plan

## Phase 1: Foundation (Complete)

- Gradle/KMP project targets (Android, iOS, Web, Desktop)
- Shared fonts downloaded to `composeResources/font/` (Press Start 2P + Pixelify Sans)
- Docs folder

## Phase 2: Design Tokens (Complete)

- `RetroPrimitiveColors` (paper + ink + arcade accents, light & dark)
- `RetroSpacing`, `RetroDimensions`, `RetroShapes`/`RetroShapeTokens`, `RetroElevation`, `RetroMotion`
- `RetroTypography` (pixel display + readable pixel-styled body)

## Phase 3: Theme (Complete)

- `RetroColors` semantic light + dark schemes
- `RetroTheme` token provider + Material3 bridge
- `retroHardShadow` modifier (signature hard offset shadow)

## Phase 4: Core Components (Complete)

- Controls: `RetroButton` (5 variants, loading, disabled, small, tactile press), `RetroIconButton`, `RetroChip`, `RetroTextField`, `RetroSwitch`, `RetroCheckbox`, `RetroRadio`
- Surfaces: `RetroCard` (+ header strip), `RetroDashedGroup`, `RetroScreen`/`RetroScreenStatic`, `RetroBottomSheet`, `RetroDialog`, `RetroPanel`, `RetroSection`, `RetroStatCard`, `RetroCharacterCard`
- Feedback: `RetroProgressBar` (Health/Energy/Xp/Danger/Info, segments, value), `RetroStatusLabel`, `SpeechBubble`, `RetroEmptyState`, `RetroLoadingIndicator`
- Game: `RetroGameCard`, `RetroCardSlot`, `RetroBattleLog`, `RetroAvatar`
- HUD: `StatHud` · Navigation: `RetroBottomBar`
- Foundations: `RetroText`, `RetroDivider`

## Phase 5: Showcase Screen (Complete)

- `DesignSystemScreen` — one scrollable screen rendering every token + component
- `App.kt` + `RetroTheme { DesignSystemScreen() }`

## Phase 6: Icons & Decorations (Complete)

- Branding components: `RetroDuelLogo`, `RetroOutlineText`, `RetroAppIcon`, `RetroDecor`
- Decorative pixel elements (stars, sparks, bolts) as Canvas composables

## Phase 7: Navigation & App Shell (Complete)

- Type-safe `@Serializable sealed interface Route` (12 destinations), single typed NavHost
- Bottom bar (HOME, CARDS, QUESTS, PROFILE) with state preservation
- Fade + slide screen transitions (phase 16)

## Phase 8–17: Features (Complete)

- Splash (animated logo, auto-advance) → Onboarding (3-page pager) → Auth (login/register/forgot password)
- Home (banner, battle CTA, deck snapshot, daily quests) · Collection (rarity filters + card detail bottom sheet)
- Deck builder (5-slot assembly, save) · Battle (arena, log, hand, attack/defend, retreat dialog)
- Quests (daily + weekly) · Profile (stats, badges, records) · Settings (theme mode persisted, audio, gameplay, account)
- Central mock data: `core/data/mock/MockChampions.kt` (shared champion roster, phase 17)

## Phase A/B/D: Theme management & polish (Complete)

- Phase A: system/light/dark theme toggle persisted on all platforms (`ThemeMode` + `SettingsRepository`/`SettingsStorage` expect/actual)
- Phase B: `surfaceRaised` token + bottom bar contrast
- Phase D: retro bottom sheet + collection card detail
- Animations branch: motion primitives (liquid spring, entrance, press feedback), staggered screen entrances, dialog pop-in, bottom sheet content stagger

## Architecture restructure (Complete)

- Folder layout aligned with the Liquefied app: `core/{config/navigation, data/mock, di, ui/{animation,components,theme,token}, utils}` + per-feature `data/domain/presentation`
- `core/design` → `core/ui`; theme persistence moved under `core/ui/theme`; tap feedback + `UiText` → `core/utils`
- Feature mock data seeded into per-feature `data/` (battle, quests, profile) using `UiText`

## Icons: Pixelarticons migration (Complete)

- Replaced Material Icons with **Pixelarticons** (MIT pixel-art icons) via the `dev.tonholo.s2c` Gradle plugin
- Source SVGs in `icons/pixelarticons/`; generated `RetroIcons.*` extension properties in `com.funapp.retroui.core.ui.icons`
- `material-icons-core` dependency removed; `DesignSystemScreen` gained an icon showcase grid

## Phase 18+: Data & domain layers

Per Liquefied MVVM + Clean pattern (`feature/<name>` with `data/domain/presentation`): di growth, network, repository, use cases, ViewModels, StateFlow state, `Result`/`SafeCall` + snackbar patterns.