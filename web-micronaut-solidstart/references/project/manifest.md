# Project Manifest

A living document to act as the single source of truth for the project's current state, ensuring human developers and AI agents are completely aligned.

## 1. Purpose of the Manifest
While ADRs track **why** decisions were made, the Manifest tracks **what** currently exists:
*   What domains/entities have been implemented?
*   What is the status of the API?
*   Are there known technical debts?

## 2. File Structure (Target)
Generate a `manifest.md` or `project-status.md` file in the root directory (or `docs/`). It should include:
*   **Core Entities implemented**: Checkboxes for Models, Repositories, Services, and Controllers.
*   **Frontend Features**: Checkboxes for pages, components, and state integrations.
*   **Open Action Items**: A to-do list for pending work.

## 3. Agent Guardrail
*   ✅ **Update the Manifest**: After completing a major feature, the AI agent SHOULD automatically propose an update to the project's `manifest.md` to reflect the newly completed entity or route. If the manifest doesn't exist, recommend generating it.
