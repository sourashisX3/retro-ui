# Changelog

## 1.0.0 — 2026-08-21 · design system hardening

First versioned release of the Retro UI design system. Scope: make the kit
production-grade, auditable, and testable — without touching app navigation.

### Added

- **Design system showcase upgrade** (`DesignSystemScreen.kt`):
  - Atomic Design taxonomy (`AtomicLevel`), component maturity badges
    (`ComponentStatus`), and a version header (`v1.0.0`).
  - New **Motion** token section: duration chips, interactive entrance-style
    playground, tactile/pop press feedback demos.
  - New **Organism** demos: bottom bar, dialog, toast.
  - New **Templates & Pages** overview mapping every app screen to the kit.
- **Focus rings** (`foundation/RetroFocus.kt`): `retroFocusRing` draws an
  offset chunky indicator on keyboard/talk-back focus. Wired into
  `RetroButton`, `RetroIconButton`, `RetroChip`, `RetroSwitch`,
  `RetroCheckbox`, `RetroRadio`, and `RetroBottomBar` tabs.
- **Semantics roles** for selection controls: `Role.Switch`,
  `Role.Checkbox`, `Role.RadioButton` plus `stateDescription` (on/off,
  checked/unchecked, selected/not selected).
- **Studio previews** (`components/RetroComponentPreviews.kt`): 12 previews
  covering buttons, chips, selection, text fields, cards, game cards,
  progress bars, avatars, Stat HUD, bottom bar, toasts, and a full screen.
- **Token sanity tests** (`commonTest/.../TokenSanityTest.kt`): palette pins,
  4dp spacing grid, shape/motion pins, and WCAG contrast gates (AA body
  text, AA-large accent fills, AAA page text) in both themes.
- **Docs**: `docs/DESIGN_SYSTEM.md` (token map, component contract,
  guidelines) and this changelog.

### Changed

- `SectionHeader` now carries atomic level + status (STABLE/BETA) badges.

### Unchanged

- No routes added: the showcase remains unrouted; app navigation untouched.