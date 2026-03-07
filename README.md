# Java Audit Engine — Backend Ramp-Up (Java 21 • Spring Boot • Kafka • Postgres)

A hands-on Java backend repo focused on **ramping to mid/senior backend level** through a real product slice: an **evidence-based audit engine**.

The goal is simple:

- Build a credible backend system with production-like concerns (reliability, security, observability).
- Use it as a structured learning path for **Java 21 + Spring Boot + Kafka**.
- Maintain daily momentum via small katas + weekly feature delivery.

---

## What this repo is (and why it exists)

This project simulates a common “real company” backend system:

- **Ingest evidence** from agents/probes (HTTP + async messaging)
- **Persist and query** evidence in Postgres
- **Run audits** that produce findings and deltas over time
- **Emit events** for downstream systems (notifications, dashboards, compliance workflows)
- **Operate** the system with proper SLO thinking, metrics, logs, and tracing

It’s not a toy. It’s a learning vehicle for:

- Java fundamentals + JVM internals
- Spring Boot production patterns
- Event-driven architecture (Kafka)
- Security foundations (JWT, TLS, cert chains)
- Reliability engineering practices

## Local startup with Docker Compose

The repository includes a `docker-compose.yml` that can start Kafka, Kafka UI, and the Spring Boot API together.

```bash
docker compose up -d
```

The first run builds the application image from [`Dockerfile`](/Users/thomaswintherbonderup/Development/audit-engine-java/Dockerfile). Once the stack is up:

- API: `http://localhost:8080`
- Health: `http://localhost:8080/actuator/health`
- Kafka UI: `http://localhost:8081`

To stop the stack:

```bash
docker compose down
```

---

## Target skill profile

**Role:** mid-level to senior backend engineer  
**Stack:** Java 21, Spring Boot, Postgres, Kafka, Docker  
**Emphasis:** reliability, security, maintainability, performance

---

## Repo map (suggested)

/apps/audit-api # Spring Boot REST API (ingest, query)
/apps/audit-runner # Scheduled/triggered audit execution
/modules/domain # Core domain model (evidence, findings, rules)
/modules/persistence # Repos, migrations, transactional boundaries
/modules/messaging # Kafka producers/consumers, outbox, DLQ patterns
/modules/security # JWT, mTLS, cert validation utilities
/modules/observability # Metrics, tracing, structured logging
/katas # Daily kata set (algorithms + Java language drills)
/docs # ADRs, runbooks, architecture notes

---

## Architecture overview

### Core concepts

- **Evidence**: immutable payload + metadata (assetId, probe, collectedAt, rawJson, auditRunId)
- **Audit Run**: execution of a rule set against evidence
- **Finding**: result with severity, explanation, and references to evidence
- **Delta**: comparison between audit runs (regressions, improvements, unchanged)

### Key flows

1. **Ingest evidence** via REST (`POST /evidence`)
2. **Publish evidence event** (`evidence.ingested`)
3. **Persist evidence** (transactional, idempotent)
4. **Run audit** (scheduled or triggered by event)
5. **Emit findings** (`audit.findings.created`) + store results
6. **Expose query APIs** for evidence, findings, deltas

---

## Milestones (what “done” looks like)

### Milestone A — Solid API + persistence

- Evidence ingestion endpoint with validation and idempotency
- Flyway migrations
- Pagination + filtering for evidence queries
- Integration tests using Testcontainers (Postgres)

### Milestone B — Event-driven ingestion (Kafka)

- Producer + consumer, schema strategy
- Retry + DLQ
- Outbox pattern to ensure “DB write + Kafka publish” consistency

### Milestone C — Audit runner & deltas

- Run audits over a time window
- Generate findings and deltas between runs
- Store results and expose query endpoints

### Milestone D — SRE-grade operations

- Structured logging, correlation IDs
- Metrics (Prometheus) + basic dashboards
- Tracing (OpenTelemetry)
- SLO-style error budgets for core endpoints

### Milestone E — Security hardening

- JWT auth + scopes
- TLS basics + cert chain validation (where relevant)
- Secure defaults: input limits, timeouts, SSRF-safe patterns

---

## Daily routine (30–60 minutes)

**Rule:** one small kata + one small repo improvement.

### Daily Katas (pick 1)

1. **Java language kata**
   - records vs classes, equals/hashCode pitfalls
   - sealed classes, pattern matching (where available)
   - generics variance (`<? extends T>`, `<? super T>`)
   - streams vs loops tradeoffs
2. **Algorithms & data structures**
   - arrays, hash maps, stacks/queues
   - binary search
   - BFS/DFS
   - heaps / priority queues
   - sliding window
3. **Backend engineering kata**
   - implement idempotency key storage
   - add retry with exponential backoff
   - add pagination + sorting
   - add rate-limiting / request size caps
4. **JVM/JIT/GC mini-kata**
   - measure allocation rate
   - compare `StringBuilder` vs concatenation
   - understand escape analysis by experiment
   - learn one GC concept and verify via logs

> Put kata solutions in `/katas/YYYY-MM-DD-<topic>.md` with short notes.

---

## Weekly cadence (2–6 hours/week)

Each week: ship **one meaningful slice** that improves credibility.

### Week themes (repeatable)

- **Week 1:** correctness + tests
- **Week 2:** performance + profiling
- **Week 3:** messaging + reliability patterns
- **Week 4:** security + hardening
- **Week 5:** observability + SLOs
- **Week 6:** refactor + architecture docs

---

## Feature backlog (recruiter-visible)

### API & domain

- [ ] Evidence ingestion: validation, schema versioning, size limits
- [ ] Evidence query API: pagination, filtering, time ranges
- [ ] Findings API: search by asset, severity, rule, runId
- [ ] Audit runs API: trigger run, list history, status

### Persistence & correctness

- [ ] Flyway migrations + indexing strategy
- [ ] Idempotency keys (per assetId + probe + collectedAt)
- [ ] Transaction boundaries (service layer)
- [ ] Optimistic locking where appropriate

### Messaging (Kafka)

- [ ] Evidence ingested event + consumer processing pipeline
- [ ] Retry topics + DLQ
- [ ] Outbox pattern (reliable publish)
- [ ] Schema strategy: JSON + versioning OR Avro/Protobuf (document choice)

### Reliability engineering

- [ ] Timeouts, bulkheads, backpressure (where relevant)
- [ ] Rate limiting or quotas for ingestion
- [ ] Circuit breaker for downstream calls (if any)
- [ ] “Poison message” handling strategy

### Observability

- [ ] Structured logging (JSON) + correlation IDs
- [ ] Metrics: latency, error rate, Kafka lag, DB timings
- [ ] OpenTelemetry tracing (HTTP + Kafka)
- [ ] Basic runbook: “What to do when ingestion is failing”

### Security

- [ ] JWT auth (scopes/roles)
- [ ] Audit trails: who did what + when
- [ ] Secure configuration practices (no secrets in repo)
- [ ] Certificate chain validation utility (optional but great signal)
- [ ] Threat modeling notes (STRIDE-lite) for key endpoints

---

## Engineering topics to practice in this repo

### Java fundamentals (core)

- Immutability, records, value objects
- Exceptions and Checked Exceptions vs result types (tradeoffs)
- Collections, generics, comparators
- Concurrency: executors, virtual threads (Java 21), structured concurrency concepts
- IO basics, serialization pitfalls
- Functional Programming in Java (Lambda, Closures)

### Spring Boot fundamentals

- Dependency injection, profiles, configuration
- Validation (`jakarta.validation`)
- Controller/service/repository layering
- Transaction management (`@Transactional`)
- Integration testing patterns

### Design patterns (used intentionally)

- Strategy pattern for rules/audits
- Factory for rule composition
- Decorator for enrichment/logging
- Adapter for external systems
- Outbox pattern for messaging reliability

### SOLID & maintainability

- Keep domain pure: minimize Spring inside core logic
- Test pyramid: unit -> integration -> contract
- ADRs for major decisions (Kafka schema strategy, auth model, etc.)

### Event-driven vs messaging (practical)

- When to use synchronous REST vs async Kafka
- Idempotency and ordering
- Exactly-once myths; practical “effectively-once”
- Retry and DLQ strategies
- Consumer lag and backpressure

### JVM & lower-level knowledge

- What JIT optimizes and when it doesn’t
- Allocation, GC basics, escape analysis
- Profiling with async-profiler / JFR
- Cost of abstraction: streams vs loops in hot paths

---

## Hard problems (sprinkle in)

Pick 1–2 per month and implement end-to-end.

1. **Outbox pattern implementation**
   - DB table + background publisher + retry semantics
2. **Idempotent ingestion**
   - dedupe key strategy + conflict handling
3. **Delta computation at scale**
   - compare findings across runs efficiently
4. **Schema evolution**
   - versioned events + backward compatible consumers
5. **Multi-tenant boundaries (lightweight)**
   - tenantId isolation, indexes, and auth scope mapping
6. **Backpressure**
   - ingestion limits + Kafka consumer tuning + batch processing
7. **Security**
   - JWT scopes + least privilege + audit trails
8. **Operational “game day”**
   - simulate Kafka outage; show system behavior and recovery

---

## Testing strategy

- **Unit tests:** domain rules, delta logic, mapping
- **Integration tests:** Postgres via Testcontainers, Kafka via Testcontainers
- **Contract tests:** simple producer/consumer schema assertions
- **Load tests (optional):** basic ingestion throughput checks

---

## Documentation (make it recruiter-friendly)

Add short docs in `/docs`:

- `ARCHITECTURE.md` — one-page overview + diagrams
- `ADR-0001-kafka-schema.md` — JSON vs Avro decision
- `RUNBOOK.md` — how to operate and debug
- `SECURITY.md` — auth model, secrets handling, threat notes
- `SLO.md` — basic SLOs (latency/error rate) and what you measure

---

## How to run (placeholder commands)

> Adjust to your actual build layout.

```bash
# Start dependencies
docker compose up -d

# Run API
./gradlew :apps:audit-api:bootRun

# Run runner (if separate)
./gradlew :apps:audit-runner:bootRun

# Tests
./gradlew test

## License
MIT
```
