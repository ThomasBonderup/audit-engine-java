# T-BOOTSTRAP-001

## Objective

Set up this repository to run a GPT-5.4 + Codex workflow using Ralph-style feature loops, Beads issue tracking, and compact plan logging.

## Scope

- add repo-local operating instructions
- add lightweight specs/task scaffolding
- initialize Beads for the repo
- configure sync-branch workflow and git hooks
- capture the new operating baseline in `PLAN.md`

## Non-goals

- application feature work
- domain refactors
- Kafka or Spring behavior changes
- CI/CD automation changes beyond local workflow setup

## Acceptance Criteria

- `AGENTS.md` exists with GPT-5.4-oriented Codex instructions
- `specs/README.md` exists and defines the spec/task contract
- `PLAN.md` reflects the new workflow and current next step
- `tasks/T-BOOTSTRAP-001.md` exists and matches the setup goal
- Beads is initialized for this repository
- sync branch workflow is configured
- git hooks are installed

## Verification

- `bd info`
- `bd ready`
- `bd hooks list`
- `./gradlew test`
