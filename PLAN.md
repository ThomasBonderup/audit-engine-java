# PLAN

## Current objective

Keep `PLAN.md` compact for the feature-loop workflow by adding a local rollover script and documenting the archive path.

## Checklist

- [x] Locate the working rollover script in `combotto-monitor`
- [x] Port the script into this repo with the same archive behavior
- [x] Document compact `PLAN.md` retention in repo workflow docs
- [x] Capture the new rollover path in `PLAN.md`
- [x] Run completion verification and close the scoped Beads item

## Next checklist item

No open workflow item remains. Future sessions should use `./bin/plan_handoff_rollover PLAN.md docs/plan-archive/PLAN-$(date +%Y-%m).md 8` once the handoff log grows past the retained window.

## What changed (2026-03-07, T-BOOTSTRAP-001 GPT-5.4 Codex workflow baseline)

- Added repo-level operating artifacts for GPT-5.4 + Codex in `AGENTS.md`, `specs/README.md`, and `tasks/T-BOOTSTRAP-001.md`.
- Initialized local Beads with Dolt backend, configured `sync.branch=beads-sync`, installed chained hooks under `.beads/hooks/`, and created bootstrap issue `aej-pjl`.
- Replaced the stale Docker-only handoff in `PLAN.md` with a compact Ralph-style workflow baseline for one-feature-at-a-time execution.

## Verification (2026-03-07, T-BOOTSTRAP-001 setup)

- PASS `bd init -p aej`
- PASS `bd migrate sync beads-sync`
- PASS `bd hooks install --beads --chain`
- PASS `bd create "Bootstrap GPT-5.4 Codex workflow" -t task -p 1 --labels "task:T-BOOTSTRAP-001,area:workflow,area:tooling" --description "..."`
- PASS `bd update aej-pjl --status in_progress`
- PASS `bd info`
- PASS `bd hooks list`
- PASS `./gradlew test`
- PASS `bd close aej-pjl --reason "Done"`
- PASS `bd ready`

## What changed (2026-03-07, T-OPS-002 PLAN handoff retention)

- Added `bin/plan_handoff_rollover`, ported from `combotto-monitor`, to archive older `## What changed (...)` blocks into `docs/plan-archive/PLAN-YYYY-MM.md` while retaining the newest iteration blocks in `PLAN.md`.
- Added `tasks/T-OPS-002.md` and updated repo workflow docs so future feature-loop sessions treat `PLAN.md` as a compact handoff artifact instead of a full history ledger.
- Refreshed the live `PLAN.md` objective/checklist so future sessions have the rollover command and retention policy in the handoff itself.

## Verification (2026-03-07, T-OPS-002 PLAN handoff retention)

- PASS `bd ready`
- PASS `bd create "Keep PLAN.md as compact handoff log" -t task -p 2 --labels "task:T-OPS-002,area:workflow,area:tooling" --description "..."`
- PASS `bd update aej-r04 --status in_progress`
- PASS `bash -n bin/plan_handoff_rollover`
- PASS `./bin/plan_handoff_rollover PLAN.md docs/plan-archive/PLAN-$(date +%Y-%m).md 8` (`No compaction needed: 2 iteration block(s), keep=8.`)
- PASS `./gradlew test`
- PASS `bd close aej-r04 --reason "Done"`
- PASS `bd ready`
