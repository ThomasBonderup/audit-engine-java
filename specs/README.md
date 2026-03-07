# Specs README

## Intent

This repository uses lightweight specs plus Beads issues to drive implementation one feature at a time.

Specifications define:

- product intent
- acceptance criteria
- non-goals for the current slice
- required verification evidence

Implementation status must always be verified from source code and tests.

## Working Agreement

- One feature slice per Beads issue.
- One active task file in focus at a time.
- One compact `PLAN.md` handoff log.
- Verification must be deterministic and recorded.

## Model-Specific Prompting

Prompts for GPT-5.4 should be:

- narrow
- explicit about constraints
- grounded in the current files
- oriented around exact verification commands

Avoid broad requests like "refactor the repo" or "improve architecture". Prefer requests like "implement task `T-BOOTSTRAP-001` checklist item 2 and verify with `./gradlew test`".

## Task File Contract

Each file under `tasks/` should contain:

- objective
- scope
- non-goals
- acceptance criteria
- verification

Keep the task small enough that one Codex session can finish or meaningfully advance it.
