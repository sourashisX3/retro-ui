# Game Flow — Online Loop

How a player moves from the home screen into a live-looking battle and back,
plus the screens that support the loop. All data is mocked for now; every
repository is behind an interface so the backend phase swaps in Ktor
implementations without UI changes.

```
HOME ── BATTLE ──> MATCHMAKING ──(found)──> BATTLE ──(retreat)──> HOME
 │                                                        │
 └── CARDS ──> COLLECTION (search + rarity filter)        │
 └── QUESTS ──> CLAIM (success toast)                     │
 └── PROFILE ──> badges, stats                            │
 └── RANK ──> LEADERBOARD (podium + list)                 │
```

## Matchmaking

1. Home → **BATTLE** pushes `Route.Matchmaking`.
2. `MatchmakingScreen` spins a radar icon and blinks the status line.
3. `MatchmakingRepository.findOpponent()` (mock: 2.5s delay, random
   `MockChampion` from the shared roster) resolves, then `onFound(opponent)`
   navigates to `Route.Battle` (replacing the matchmaking entry).
4. **CANCEL SEARCH** pops back home and fires an Info toast.

## Battle

- `BattleScreen` renders the matched opponent (name, icon, HP bar) from the
  `MockChampion` handed over by matchmaking.
- The red exit icon (top-right) opens the RETREAT confirmation dialog;
  confirming pops back home.
- Hand, log, attack/defend are static mocks.

## Leaderboard

- 5th bottom-bar tab **RANK** (`Route.Leaderboard`).
- `LeaderboardRepository.getLeaderboard()` (mock: 12 entries, player at
  rank 4 highlighted).
- UI: 1-2-3 podium in gold/silver/bronze blocks (2nd-1st-3rd columns),
  then the full ranked list below with compact rows; rank numbers of the
  top three are podium-tinted; YOUR row uses `primaryContainer`.

## Toasts

`RetroToastController` (provided at `AppNavHost` root) queues one toast at a
time; each auto-dismisses (2s/3.5s/5s) with a liquid pop-in at the top.
Wired today: deck SAVE → Success, quest CLAIM → Success, matchmaking CANCEL →
Info.

## Backend handoff

Swap the mock constructors in `App.kt`'s `AppContainer` for real
repositories. No screen references a mock type directly.