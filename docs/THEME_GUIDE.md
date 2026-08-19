# Theme Guide

## Color tokens

Access semantic colors via `RetroTheme.colors`. Never use primitive colors directly.

### Light (paper)

| Token | Value |
|---|---|
| `background` | `#F8F0DA` |
| `surface` | `#FFF8E8` |
| `surfaceVariant` | `#EFE1C0` |
| `surfaceMuted` | `#E6D3C0` |
| `outline` / `outlineStrong` | `#171717` |
| `primary` (green) | `#45D66F` |
| `secondary` (yellow) | `#FFC52E` |
| `accent` (purple) | `#8B5CF6` |
| `error` (red) | `#F05B5B` |
| `warning` (orange) | `#F4A62A` |
| `info` (blue) | `#4C6FFF` |
| `textPrimary` | `#171717` |
| `textSecondary` | `#3A3A3A` |
| `textMuted` | `#6B5D4B` |

### Dark (arcade night)

Dark scheme is deliberately designed, not inverted. `background #14140F`, `surface #1E1C16`, ink text `#F8F0DA`. Accents stay bright flat arcade colors.

## Typography

| Role | Face | Size |
|---|---|---|
| `display` | Press Start 2P | 34sp |
| `headline` | Press Start 2P | 24sp |
| `title` | Press Start 2P | 16sp |
| `label` | Press Start 2P | 12sp |
| `body` | Pixelify Sans | 16sp |
| `bodySmall` | Pixelify Sans | 14sp |
| `caption` | Pixelify Sans | 12sp |

## Spacing

`RetroTheme.spacing` — 4dp grid: xxs 2, xs 4, sm 8, md 12, lg 16, xl 20, xxl 24, xxxl 32.

## Hard shadow

`Modifier.retroHardShadow(offsetX, offsetY, color, shape)` — 0-blur solid offset shadow. Default 3/4dp; pressed collapses to 0.

## Corners

`RetroTheme.shapeTokens`: button 6dp, card 8dp, chip 4dp, input 6dp, dialog 10dp, badge 4dp. Squared/slightly-rounded — no modern pill-heavy rounding.

## Motion

`RetroMotion.FastMs` 120 (press), `NormalMs` 240, `SlowMs` 400. Press feedback is quick (100-150ms) to feel tactile.