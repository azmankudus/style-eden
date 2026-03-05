---
name: blueprint-web-micronaut-solidstart
metadata:
  compatible_agents: [antigravity, claude-code, opencode, openclaw]
  tags: [blueprint, web-app, full-stack, micronaut, solidstart, java21, typescript]
description: >
  Specialized blueprint for crafting full-stack web applications using Micronaut (Java 21) 
  and SolidStart (TypeScript). This skill orchestrates the entire SDLC, from discovery 
  to build configuration, enforcing strict conventions for Gradle, Bun, and reactive UI patterns. 
  Triggers: "blueprint web app micronaut solidstart", "new fullstack java typescript project", 
  "setup micronaut solidstart blueprint", "rancang web app java solidstart".
---

# Web App Blueprint: Micronaut & SolidStart

Design and architect a modern, reactive full-stack web application using high-performance Java 21 and SolidStart.

## SDLC Flow

1.  **Discovery**: Define the business domain and user personas.
2.  **Logic Planning**:
    *   **Backend**: Micronaut REST API (see `references/micronaut.md`).
    *   **Frontend**: SolidStart UI (see `references/solidstart.md`).
3.  **Cross-Cutting Concerns**: 
    *   **Language Standards**: Java 21 & TS (see `references/java.md`).
    *   **Build & Ops**: Gradle & Bun (see `references/build.md`).
4.  **Specification**: Generate SRS and high-level architecture diagrams.

## Command Reference

| Command | Description |
|---|---|
| `/blueprint init` | Start the discovery and planning session for this stack |
| `/blueprint spec` | Generate specialized documentation (SRS, Architecture) |
| `/blueprint build-plan` | Generate combined Gradle and Bun build strategies |

---

## 1. `/blueprint init` — Discovery Session

Identify the project nature:
1.  **Domain Entities**: What are the core models?
2.  **API Contract**: REST vs. gRPC for this specific app.
3.  **State Requirements**: Global store vs. local signals.

---

## Reference Files

| File | Description |
|---|---|
| `references/java.md` | JDK 21 language-level conventions |
| `references/micronaut.md` | Backend framework-specific patterns |
| `references/solidstart.md` | Frontend framework-specific patterns |
| `references/build.md` | Unified build (Gradle/Bun) orchestration |
