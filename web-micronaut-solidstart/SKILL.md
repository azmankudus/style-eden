---
name: blueprint-web-micronaut-solidstart
metadata:
  compatible_agents: [antigravity, claude-code, opencode, openclaw]
  tags: [blueprint, web-app, full-stack, micronaut, solidstart, java21, typescript, ci-cd, docker, sdlc, quality]
description: >
  Specialized blueprint for crafting full-stack web applications using Micronaut (Java 21) 
  and SolidStart (TypeScript) for a team environment. This skill orchestrates the entire SDLC, 
  from discovery and documentation scaffolding to build configuration (Gradle/Bun), CI/CD pipelines, 
  and Docker containerization. Includes strict anti-hallucination guardrails and API contract 
  governance to keep AI agents and human developers perfectly synchronized.
  Triggers: "blueprint web app micronaut solidstart", "new fullstack java typescript project", 
  "setup micronaut solidstart blueprint", "generate ci cd for micronaut solidstart", 
  "dockerize micronaut solidstart", "rancang web app java solidstart".
---

# Web App Blueprint: Micronaut & SolidStart

Design, architect, and deploy a modern, reactive full-stack web application using high-performance Java 21 and SolidStart. This blueprint is designed specifically for **team collaboration** and enforced **agent reliability**.

## Command Reference

| Command | Phase | Description |
|---|---|---|
| `/blueprint init` | Pre | Start the discovery and planning session for this stack |
| `/blueprint docs` | Pre | Scaffold the full SDLC documentation structure and ADRs |
| `/blueprint check` | During | Validate project code against anti-hallucination standards |
| `/blueprint build-plan` | During | Generate combined Gradle and Bun build strategies |
| `/blueprint ci-cd` | Post | Generate GitHub Actions workflows for Gradle + Bun |
| `/blueprint docker` | Post | Scaffold multi-stage Dockerfiles and docker-compose.yml |

---

## 1. Agent Anti-Hallucination Guardrails

Before finalizing any strategy or code generation, the agent MUST verify against these strict rules to prevent hallucination and drift:
*   **API Contracts first**: Do not generate frontend components without an agreed-upon API contract (e.g., OpenAPI spec). The backend is the source of truth.
*   **Micronaut Standards**: Enforce constructor injection, explicit `@Produces(MediaType.APPLICATION_JSON)`, and strictly type-safe `jakarta.validation` limits. No `@Inject` on fields.
*   **SolidStart Standards**: Enforce `strict` TypeScript, use `createResource` for API data fetching, and forbid `any` types. No prop-drilling or direct DOM manipulation.
*   **Quality Gates**: Ensure Gradle Checkstyle/Spotless (Java) and ESLint/Prettier (TypeScript) standards are met before committing.

---

## 2. SDLC & Team Integration

This blueprint supports a full software development lifecycle (SDLC) to ensure team alignment:
1.  **Product Specs & ADRs**: Scaffolded via `/blueprint docs` into `docs/00-product` and `docs/01-architecture`.
2.  **API Governance**: Documented in `references/api-contract.md` to ensure Backend and Frontend teams (and agents) speak the same language.
3.  **Continuous Integration & Deploy**: Handled via `/blueprint ci-cd` and `/blueprint docker` workflows.

---

## Reference Files

### Project & Planning
| File | Description |
|---|---|
| `references/project/sdlc-team.md` | SDLC documentation structure, PR workflows, and ADR patterns |
| `references/project/manifest.md` | Single source of truth for features and project state |

### Requirements & Governance
| File | Description |
|---|---|
| `references/requirement/api-contract.md` | API synchronization and governance (OpenAPI source of truth) |

### Design & Frontend System
| File | Description |
|---|---|
| `references/design/ui-ux-design-system.md` | UI/UX aesthetics, Tailwind v4 component strategies, and animations |

### Development
| File | Description |
|---|---|
| `references/development/java.md` | JDK 21 language-level conventions |
| `references/development/micronaut.md` | Micronaut patterns and anti-hallucination guardrails |
| `references/development/gold-standard.md` | Executable code snippets for Controllers, Envelopes, and DTOs |
| `references/development/solidstart.md` | SolidStart patterns and anti-hallucination guardrails |
| `references/development/build.md` | Legacy build (Gradle/Bun) orchestration basics |

### Testing
| File | Description |
|---|---|
| `references/testing/strategy.md` | Complete testing strategy and quality gates |

### Security
| File | Description |
|---|---|
| `references/security/owasp-guardrails.md` | Essential OWASP security guardrails |

### Deployment & CI/CD
| File | Description |
|---|---|
| `references/deployment/ci-cd-docker.md` | CI/CD pipelines, Git conventions, and Docker deployment strategies |

### Maintenance & Operations
| File | Description |
|---|---|
| `references/maintenance/dependency-updates.md` | Aggressive and secure dependency update strategies |
| `references/operations/observability.md` | Application monitoring, metrics, and structured logging |
