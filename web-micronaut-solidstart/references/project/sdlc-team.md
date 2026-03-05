# SDLC & Team Documentation Strategy

A structured team requires a clear documentation and process flow to ensure seamless communication, onboarding, and issue tracking.

## 1. Documentation Structure
When running `/blueprint docs`, the agent should scaffold the following SDLC folder structure:
```
docs/
├── 00-product/
│   ├── README.md
│   ├── 01-product-spec.md
│   └── 02-requirements.md
├── 01-architecture/
│   ├── README.md
│   ├── 01-overview.md
│   ├── 02-data-layer.md
│   └── adr/
│       └── 0001-record-architecture-decisions.md
├── 02-development/
│   ├── README.md
│   └── 01-getting-started.md
└── 03-deployment/
    ├── README.md
    └── 01-runbook.md
```

## 2. Architecture Decision Records (ADRs)
Every major technical choice must be recorded as an ADR in `docs/01-architecture/adr/`.
*   **Format**: Title, Status (Proposed/Accepted/Deprecated), Context, Decision, Consequences.
*   **Purpose**: Prevents the team (and AI agents) from second-guessing architecture choices over time.

## 3. Pull Request & Code Review Flow (Team Rules)
*   Require **Conventional Commits** (e.g., `feat(ui): add dashboard`, `fix(api): handle null pointers`).
*   Establish branching: `main` (production), `develop` (staging), `feature/*` (active work).
*   Enforce PR templates that require checking off: 
    *   [ ] Unit tests passed
    *   [ ] E2E check passed
    *   [ ] API contract matched
