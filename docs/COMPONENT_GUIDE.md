# Component Guide

All components live in `com.funapp.retroui.core.design.components.*` and must be rendered inside `RetroTheme`.

## RetroButton

Tactile arcade button. Pressing collapses the hard shadow and sinks the button ~2dp (120ms).

```kotlin
RetroButton(text = "PLAY", onClick = { })
RetroButton("JUMP", variant = RetroButtonVariant.Secondary, onClick = { })
RetroButton("MAGIC", variant = RetroButtonVariant.Accent, onClick = { })
RetroButton("ATTACK", variant = RetroButtonVariant.Danger, onClick = { })
RetroButton("OUTLINE", variant = RetroButtonVariant.Outline, onClick = { })
RetroButton("LOADING", loading = true, onClick = { })
RetroButton("SMALL", small = true, leadingIcon = Icons.Filled.Add, onClick = { })
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
        HudStat(Icons.Filled.Favorite, "150", colors.error, "HP"),
        HudStat(Icons.Filled.Star, "200", colors.accent, "XP"),
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
RetroIconButton(imageVector = Icons.Filled.Home, contentDescription = "Home", onClick = { })
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

## Golden rules

- Never create `GreenButton` / `PurpleButton` — use `RetroButtonVariant`.
- Never hardcode colors/dp — use `RetroTheme.*` tokens.
- Apply `retroHardShadow` BEFORE `.background`/`.border` in the modifier chain.