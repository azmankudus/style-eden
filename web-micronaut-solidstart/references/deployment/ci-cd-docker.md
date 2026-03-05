# CI/CD & Docker Strategy

Automating building, testing, and deployment to ensure parity between environments.

## 1. Continuous Integration (GitHub Actions)
When running `/blueprint ci-cd`, generate workflows that test BOTH environments:
*   **Backend (Micronaut)**: Run `./gradlew check` (Checkstyle, PMD) and `./gradlew test` (JUnit, Testcontainers).
*   **Frontend (SolidStart)**: Run `bun run lint` (ESLint) and `bun run test` (Vitest/Playwright).
*   **Quality Gates**: The PR cannot be merged unless both CI pipelines pass.

## 2. Docker Containerization strategy
When running `/blueprint docker`, generate production-ready Dockerfiles.
*   **Micronaut Backend**:
    *   Use a multi-stage `Dockerfile`.
    *   Stage 1: `gradle:jdk21` to build the Fat JAR.
    *   Stage 2: `eclipse-temurin:21-jre-alpine` or Ubuntu minimal to run the JAR.
*   **SolidStart Frontend**:
    *   Use a multi-stage Node/Bun `Dockerfile`.
    *   Stage 1: `oven/bun` to install dependencies and run `bun run build`.
    *   Stage 2: Serve using an optimized Node server or Nginx (if static).

## 3. Local Development (Docker Compose)
Provide a `docker-compose.yml` that spins up dependencies to lower team onboarding friction:
*   PostgreSQL / MySQL
*   Redis (if used for Session/Cache)
*   Localstack or MinIO (if S3 is needed)
