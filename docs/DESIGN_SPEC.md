# Visual Specification — Retro Pixel / Hand-Drawn Game UI

The reference visual language this design system reproduces:

> **Warm cream paper + thick black ink outlines + flat arcade colors + hard offset shadows + pixel typography + compact game-HUD layouts + hand-drawn vector/pixel illustrations + tactile interactions.**

This is NOT a "gamified" Material UI. It is a retro game design kit / physical sticker-and-paper interface translated into digital UI.

## 1. Overall canvas

- Warm cream/paper background — never pure white, gray, dark-by-default, glassmorphic or gradient-heavy.
- Primary background: `#F8F0DA`
- Surfaces:
  - Background `#F8F0DA`
  - Surface `#FFF8E8`
  - Secondary Surface `#EFE1C0`
  - Muted Surface `#E6D3C0`
- Cards feel like pieces placed ON TOP of the paper. Background/card difference is subtle.

## 2. Black outline language

- Near-black outline `#171717` is the strongest visual characteristic.
- Never use `#CCCCCC` / `#E0E0E0` / `#DADADA` as the primary component border.
- Typical border: 2dp solid dark · Emphasized: 3dp · Hero: 3-4dp.
- Borders should look hand-illustrated, not like a Material divider.

## 3. Hard offset shadow

- NO blur, NO soft floating shadow, NO realistic drop shadow.
- A solid hard-edged shape steps down-right behind the component.
- Default: X 2-3dp, Y 2-4dp, blur 0dp. Pressed: X 0dp, Y 0-1dp.

## 4. Buttons

- Compact, bold, outlined rectangular controls.
- Dark outline + bright flat fill + compact corners + hard bottom/right shadow + bold typography + centered label + short height.
- Primary buttons are commonly bright green (`#45D66F`) with ink text `#171717`.
- No Material 3 filled-button look, no pill by default.

## 5. Pressed state

- Shadow collapses, component translates down 1-3dp, 100-150ms. Feels physical.

## 6. Typography

- Headings/labels use a PIXEL / ARCADE typeface (Press Start 2P).
- Body uses a readable pixel-styled face (Pixelify Sans) — not pixel font everywhere.
- Deliberate contrast: PIXEL DISPLAY + SIMPLE READABLE BODY.

## 7. Weight

- Bold, SemiBold, ExtraBold. Never thin/light/elegant/delicate.

## 8. Corners

- No modern highly-rounded UI. Prefer 0-2dp (pixel), 4-8dp standard, 8-10dp larger cards. Pills only where semantic.

## 9-10. Cards

- Cream/off-white surface + dark outline + hard offset shadow + compact radius + strong internal separators + compact spacing.
- Optional top header strip with a slightly different cream tone, dark separator line.

## 11. Game statistics

- `ICON + VALUE` compact rows (e.g. HP/MP/XP). Prominent icons, bold numbers, tight spacing. Not a business dashboard.

## 12-13. Progress / health bars

- Dark outer outline, thin height, cream track, flat bright fill, dark numbers.
- Reusable variants: Health, Energy, XP, Success, Warning, Danger.
- FLAT fill — no gloss/gradient/blur/neumorphism. Optional value text, optional segments.

## 14-15. Color language

- Bright flat arcade colors:
  - Green `#45D66F` · Yellow `#FFC52E` · Orange `#F4A62A` · Red `#F05B5B` · Blue `#4C6FFF` · Purple `#8B5CF6` · Pink `#F06491`
- Bright but not neon/glowing. No excessive gradients.
- Hierarchy: background → cream surfaces → dark outlines → bright accents → important action.
- Primary action → green · Warning → yellow/orange · Danger → red · Information → blue · Special → purple.

## 16. Dashed group containers

- Dark dashed stroke, 1-2dp, cream/transparent interior, square/slightly rounded.
- Used to group controls, settings sections, game panels. Not on every component.

## 17. Speech bubbles

- Cream background, dark outline, hard offset shadow, small triangular pointer, compact text, playful appearance.

## 18. Status / live labels

- Cream surface, ink outline, compact, bold text, optional colored dot (e.g. `LIVE ●`). Avoid generic Material chips.

## 19-20. Icons & illustrations

- Bold, illustrative, dark outlines, flat colors. Pixel/vector/hand-drawn. Unified system — no mixing Material thin-line + 3D + photo-real.

## 21. Character cards

- NAME / IMAGE (muted tan) / STATS / ACTION. Pixel-art/vector character, centered.

## 22-25. Layout rules

- Dividers: 1-2dp dark printed lines, never subtle gray.
- Dense but not cramped: 4/8/12/16dp rhythm.
- Playful VISUALS + strict LAYOUT — consistent alignment, predictable margins, uniform heights.
- Controlled imperfection: allowed in decorations/illustrations, never in alignment/grid/spacing.

## 26. Material adaptation

- Material/Compose primitives may be used internally, but their visual appearance is overridden by `RetroButton` / `RetroCard` / etc. Default Material styling must not dominate.

## Visual match check

Before implementing any component, verify:
1. Warm cream/paper appearance? 2. Strong dark outline? 3. Hard offset shadow (not soft)? 4. Pixel/arcade typography? 5. Bright, flat, playful colors? 6. Compact density? 7. Squared/slightly-rounded shapes? 8. Tactile feel? 9. Looks like a game UI element? 10. Belongs beside the reference?

> Goal: "if this component were placed inside the provided reference image, it should look like it was designed as part of the same original UI kit."