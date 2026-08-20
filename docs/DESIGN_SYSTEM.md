# Retro UI — Design System

The shared design system behind **DECKRON**, the multiplayer arcade card duel.
One kit, four platforms (Android, iOS, Web, Desktop) — powered by Compose
Multiplatform and organized by **Atomic Design**.

## Versioning

`v1.0.0` — see [CHANGELOG.md](./CHANGELOG.md) for history. Bump
`DESIGN_SYSTEM_VERSION` in `DesignSystemScreen.kt` on every breaking change.

## The 5 Levels

| Level | Meaning | Examples |
| --- | --- | --- |
| **Tokens** | Raw material | colors, typography, spacing, radius, elevation, motion |
| **Atoms** | Smallest pieces | icons, buttons, chips, text fields, selection controls, progress bars, labels |
| **Molecules** | Small combos | cards, avatars, status labels, battle-log rows, empty states |
| **Organisms** | Big structures | panels, Stat HUD, game cards, bottom bar, dialog, toast |
| **Templates & Pages** | Full screens | home, collection, quest log, battle, settings |

## Tokens (`core/ui/token/`)

| Token | Source of truth | Rules |
| --- | --- | --- |
| `RetroPrimitiveColors` | Raw `Color` constants | Never used directly by features |
| `RetroColors` (theme) | Semantic layer: `primary`, `secondary`, `accent`, `error`, `surface`, `outline`, `text*` | The ONLY layer features read |
| `RetroTypography` | `display`/`title`/`body`/`caption`/`button` | Pixel font for headings, HUD, buttons |
| `RetroSpacing` | 4dp grid: `xs=4, sm=8, md=12, lg=16` (`xxs=2` exception) | Never invent arbitrary gaps |
| `RetroShapes` / `RetroShapeTokens` | Squared kit: button 2dp, card 4dp, dialog 6dp, pill 100dp | No pill-heavy modern UI |
| `RetroElevation` / `RetroBorders` | Hard offset shadows + ink outlines | No blur shadows, no glow |
| `RetroMotion` / `RetroAnimation` | Named specs: `press`, `pop`, `bounce`, `shake`, `slide`, `fade`, `flip`, `cardReveal`, `liquid`, `arcade` | Animate state, not content |

## Components (`core/ui/components/`)

Every component consumes tokens via `RetroTheme` only, fires
`rememberRetroTapFeedback` on press, and ships with:

- a **hard shadow + tactile press** (via `retroTactilePress` / `retroPopPress`)
- a **focus ring** for keyboard/talk-back (via `retroFocusRing`, applied last in the chain)
- correct **semantics roles** (`Role.Switch` / `Role.Checkbox` / `Role.RadioButton`)
- a **studio preview** (see `RetroComponentPreviews.kt`)
- a **status** in the showcase: `STABLE` (in production) or `BETA` (evolving)

## Guidelines

1. **Semantic colors only.** Never `Color(0xFF...)` in feature code — use `RetroTheme.colors`.
2. **`RetroIcons` only.** No Material or third-party icon sets.
3. **Every screen sits on `RetroScreen`** for safe-area/keyboard insets.
4. **Every pressable is tactile.** Shadow collapses, control sinks ~2dp, 120ms.
5. **A11y is not optional.** Roles, focus rings, and WCAG AA contrast are
   enforced by `TokenSanityTest` in `commonTest`.
6. **Motion via `RetroAnimation` specs only** — never inline durations.

## Showcase

`core/ui/DesignSystemScreen.kt` renders the full kit (tokens → templates),
versioned, with live motion demos. It is intentionally not routed in the app —
it's a reference artifact kept in the repo.