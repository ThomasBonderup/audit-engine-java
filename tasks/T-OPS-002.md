# T-OPS-002

## Objective

Keep `PLAN.md` compact by archiving older handoff iterations into a monthly archive file.

## Scope

- add a repo-local rollover script for `PLAN.md`
- document the archive workflow in repo instructions
- update `PLAN.md` to point future sessions at the rollover command

## Non-goals

- application feature work
- Docker, Kafka, or Spring Boot behavior changes
- automatic cron or CI compaction

## Acceptance Criteria

- `bin/plan_handoff_rollover` exists and is executable
- the script archives older `## What changed (...)` blocks into `docs/plan-archive/PLAN-YYYY-MM.md`
- the script retains the newest N iteration blocks in `PLAN.md`
- `AGENTS.md` and `README.md` mention the compact handoff workflow
- `PLAN.md` points to the rollover path for future use

## Verification

- `bash -n bin/plan_handoff_rollover`
- `./bin/plan_handoff_rollover PLAN.md docs/plan-archive/PLAN-$(date +%Y-%m).md 8`
