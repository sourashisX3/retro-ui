# Component Guide

All components live in `com.funapp.retroui.core.ui.components.*` and must be rendered inside `RetroTheme`.

## Icons

Every icon is a `RetroIcons.*` extension property generated from the Pixelarticons pack (MIT) by the svg-to-compose plugin — source SVGs live in `icons/pixelarticons/`. Import both the receiver and the icon:

```kotlin
import com.funapp.retroui.core.ui.icons.RetroIcons
import com.funapp.retroui.core.ui.icons.Home

Icon(imageVector = RetroIcons.Home, contentDescription = "Home", tint = RetroTheme.colors.primary)
```

**Rule:** features use only `RetroIcons.*` — never Material or third-party icon sets. Tint via `RetroTheme` colors; icons are flat 24×24 pixel art that match the theme by construction.

## RetroButton

Tactile arcade button. Pressing collapses the hard shadow and sinks the button ~2dp (120ms).

```kotlin
RetroButton(text = "PLAY", onClick = { })
RetroButton("JUMP", variant = RetroButtonVariant.Secondary, onClick = { })
RetroButton("MAGIC", variant = RetroButtonVariant.Accent, onClick = { })
RetroButton("ATTACK", variant = RetroButtonVariant.Danger, onClick = { })
RetroButton("OUTLINE", variant = RetroButtonVariant.Outline, onClick = { })
RetroButton("LOADING", loading = true, onClick = { })
RetroButton("SMALL", small = true, leadingIcon = RetroIcons.Add, onClick = { })
```

**Variants:** Primary (green), Secondary (yellow), Accent (purple), Danger (red), Outline (cream/ink)

## RetroCard

Physical paper/game card: cream surface + ink outline + hard offset shadow + optional header strip.

```kotlin
RetroCard {
    RetroText("Title", style = RetroTheme.typography.title)
}
RetroCard(
    header = {
        RetroCardHeader(title = "Player card", subtitle = "Level 12 · Warrior", trailing = { /* ... */ })
    },
) { /* body */ }
```

## RetroDashedGroup

Dashed-ink grouping container for settings/panels/related controls.

```kotlin
RetroDashedGroup {
    RetroText("SETTINGS", style = RetroTheme.typography.label)
    RetroSwitch(checked = true, onCheckedChange = { })
}
```

## RetroProgressBar

Classic game-HUD bar: dark outline, flat fill, optional segments + centered value.

```kotlin
RetroProgressBar(progress = 0.66f)                                  // green health
RetroProgressBar(progress = 0.4f, color = RetroProgressColor.Energy, segments = 10, showValue = true)
RetroProgressBar(progress = 0.15f, color = RetroProgressColor.Danger, valueText = "LOW")
```

**Colors:** Health (green), Energy (yellow), Xp (purple), Danger (red), Info (blue)

## StatHud

Compact `ICON VALUE` game-HUD stat row.

```kotlin
StatHud(
    stats = listOf(
        HudStat(RetroIcons.Favorite, "150", colors.error, "HP"),
        HudStat(RetroIcons.Star, "200", colors.accent, "XP"),
    ),
)
```

## RetroStatusLabel

Compact arcade status widget (e.g. `LIVE ●`).

```kotlin
RetroStatusLabel(text = "LIVE", dotColor = colors.error)
RetroStatusLabel(text = "READY", dotColor = colors.success)
```

## SpeechBubble

Game dialogue box: cream + ink outline + hard shadow + triangular pointer.

```kotlin
SpeechBubble {
    RetroText("Select a player to continue!", style = RetroTheme.typography.bodySmall)
}
```

## Other controls

```kotlin
RetroIconButton(imageVector = RetroIcons.Home, contentDescription = "Home", onClick = { })
RetroChip(text = "HEROES", selected = true, onClick = { })
RetroTextField(value = text, onValueChange = { text = it }, label = "Player name")
RetroSwitch(checked = switchOn, onCheckedChange = { switchOn = it })
RetroCheckbox(checked = c, onCheckedChange = { c = it }, label = "Remember me")
RetroRadio(selected = r, onSelect = { r = it }, label = "Warrior")
```

## Foundations

```kotlin
RetroText("Score 1250", style = RetroTheme.typography.title)
RetroDivider()                          // 2dp ink line
Modifier.retroHardShadow(offsetX = 3.dp, offsetY = 4.dp, shape = RetroTheme.shapeTokens.card)
```

## Motion

Every entrance uses the arcade motion personality. Pick the style by element type — never invent raw tweens.

```kotlin
Modifier.retroEntrance(style = RetroEntranceStyle.Pop, delayMillis = 0)
Modifier.retroEntrance(style = RetroEntranceStyle.Coin, delayMillis = 60)
Modifier.retroEntrance(delayMillis = retroCascade(index))   // default Rise, 60ms step
```

| Style | Motion | Use for |
|---|---|---|
| `Rise` | fade + rise 24dp (arcade spring) | panels, banners, sections, headers, forms |
| `Pop` | fade + scale 0.84→1 + rise 10dp | game cards, stat cards, card slots, chips, quest rows |
| `Coin` | fade + scale 0.6→1 blip + rise 6dp | icons, badges, HUD, VS badge, battle log, counts |
| `SlideLeft` | fade + slide 32dp from the left | rows and side elements |
| `Stomp` | scale 1.06→1 slam, no fade | section titles |

- All entrances animate only `graphicsLayer` properties (alpha / translation / scale) — never `width`/`height`/`offset`, which re-layout every frame.
- Screen transitions in `AppNavHost` use `RetroAnimation.arcadeOffset` (⅓-screen push) + `RetroAnimation.fade`; pops reverse the direction.
- Cascade cadence is uniform: `retroCascade(index, stepMs = 60)` — 60ms per list row, 40ms for tight grids/slots.
- All specs come from `RetroAnimation` (press / pop / arcade / bounce / shake / slide / fade / flip / cardReveal / liquid / draw).

## Golden rules

- Never create `GreenButton` / `PurpleButton` — use `RetroButtonVariant`.
- Never hardcode colors/dp — use `RetroTheme.*` tokens.
- Apply `retroHardShadow` BEFORE `.background`/`.border` in the modifier chain.
- **Press feedback is two-tier** — never leave an interactive surface on raw `clickable`:
  - `Modifier.retroTactilePress(interactionSource, shape, shadowColor, shadowX, shadowY)` — for surfaces **with a hard shadow** (buttons, icon buttons, chips, cards, slots): collapses the shadow + sinks 2dp, exactly like `RetroButton`.
  - `Modifier.retroPopPress(interactionSource)` — for shadowless compact controls (switch, checkbox, radio, bottom-bar tabs): springy scale-down + sink with overshoot pop.
  - Both wire the SAME `MutableInteractionSource` used by the component's `clickable`, with `indication = null`.
- Every interactive control calls `rememberRetroTapFeedback().play()` (haptic + blip) in its click handler.