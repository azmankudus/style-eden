# Build & Orchestration Blueprint

How Java (Gradle) and TypeScript (Bun) coexist in this blueprint.

## 1. Backend: Gradle
*   **Kotlin DSL**: All build scripts use `.gradle.kts`.
*   **Version Catalog**: Centralized dependency management in `libs.versions.toml`.
*   **Shadow Jar**: Build single executable fat JARs for deployment.

## 2. Frontend: Bun
*   **Package Management**: Use `bun install` for lightning-fast speeds.
*   **Scripts**: Standardized `bun run dev`, `build`, and `test`.
*   **Testing**: Use Bun's native test runner where applicable.

## 3. Integration Best Practices
*   **Dev Proxy**: Vite/SolidStart configured to proxy `/api` to Micronaut.
*   **Unified CI**: Integration pipeline running both Gradle and Bun tests.
*   **Docker**: Multi-stage build combining the optimized Java JAR and built frontend assets.
