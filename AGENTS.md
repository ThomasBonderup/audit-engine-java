# AGENTS.md

## Model + Tooling Baseline

- Primary coding model for this repository: GPT-5.4 via Codex.
- Operating mode: Ralph-style single-feature loop with Beads-first execution.
- Issue tracking: `bd` with local `.beads/` state and Dolt-backed history.
- Default rule: work one claimed Beads item at a time. Do not batch unrelated edits.

## Source Of Truth

Read in this order before implementation:

1. `specs/README.md`
2. `AGENTS.md`
3. `PLAN.md`
4. relevant `tasks/<TASK>.md`

Specs describe intent, not guaranteed implementation. Confirm behavior from code before stating anything exists.

## GPT-5.4 Prompting Rules

When using Codex/GPT-5.4 for implementation in this repo:

- Keep prompts explicit and bounded to one feature slice.
- Always include goal, constraints, touched files, and verification target.
- Ask for minimal diffs and deterministic evidence.
- Prefer repo facts over speculation; cite file paths after reading them.
- Keep planning compact: 3-8 checklist items, with exactly one active item.
- Prefer serial execution over parallel workstreams to avoid backpressure and context drift.

Use this prompt shape:

```md
Objective: <single feature or bugfix>
Why: <user value / acceptance>
Constraints:
- one Beads item only
- minimal diff
- no speculative refactors
- update PLAN.md verification log
Verify:
- <smallest compile/test command>
- <completion gate command>
Touched context:
- <files or packages>
```

## Ralph Loop

1. Run `bd ready` once at the item boundary.
2. Claim exactly one issue: `bd update <id> --status in_progress`.
3. Make the smallest coherent change.
4. Run the required backpressure gate for the touched scope.
5. Update `PLAN.md` with:
   - current objective
   - active Beads issue + task id
   - `## What changed`
   - `## Verification`
6. Close the item only after verification passes: `bd close <id> --reason "Done"`.
7. Run `bd ready` once to select the next item.

## Beads Cadence

- Keep `bd` commands serial.
- Do not spam `bd ready` during active coding.
- Prefer one scoped issue per feature slice.
- If no ready work exists for an active task, create one small issue tied to the task label.
- Keep `.beads/` local on the main branch; use sync branch flow for history/export.

## Backpressure

### Gate 1: edit-time quick check

Use the smallest proof command while editing:

- `./gradlew test --tests <ClassName>` for touched tests
- `./gradlew compileJava compileTestJava` for compile-only validation

### Gate 2: completion gate

Required before closing an item:

- `./gradlew test`

### Gate 3: task-boundary confidence gate

Optional, once per task or before handoff:

- `docker compose config`
- `docker compose up -d`
- targeted `curl` proof when API behavior changed

## Codex Workflow

- Keep repository memory in files, not chat:
  - `PLAN.md` is the active handoff log
  - `tasks/<TASK>.md` defines acceptance
  - Beads holds queue/dependencies/status
- Prefer concise prompts and concise logs because GPT-5.4 performs better here with explicit bounds and less repetitive narration.
- Before context rollover, update `PLAN.md` with next item, files touched, and latest verification results.
- Keep `PLAN.md` compact:
  - retain only recent iteration blocks in the root plan
  - archive older blocks under `docs/plan-archive/`
  - run `./bin/plan_handoff_rollover PLAN.md docs/plan-archive/PLAN-$(date +%Y-%m).md 8` when the plan starts to grow

## Beads Setup

Minimal reference:

- `bd prime`
- `bd ready`
- `bd create "..." -t task -p 1 --labels "task:T-..."`
- `bd update <id> --status in_progress`
- `bd close <id> --reason "Done"`
- `bd sync`

Repository bootstrap target:

- `bd init -p aej`
- `bd migrate sync beads-sync`
- `bd hooks install --beads --chain`

## Safety

- Never commit secrets, tokens, or local credentials.
- Avoid destructive commands unless required and documented in `PLAN.md`.
- Keep diffs commit-ready.
