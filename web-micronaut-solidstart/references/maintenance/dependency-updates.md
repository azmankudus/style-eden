# Maintenance & Dependency Updates

Keeping the stack modern and secure requires an aggressive but safe update strategy. Stale dependencies are the #1 cause of technical debt.

## 1. Automated Dependency Management
*   **Dependabot / Renovate**: Configure bots to automatically submit PRs when new versions of dependencies are released.
*   **Approval Gates**: Auto-merge `patch` and `minor` version updates ONLY if all CI/CD Quality Gates pass (100% test success). `major` bumps always require human review.

## 2. Backend (Micronaut / Gradle)
*   **Gradle Versions Plugin**: Use `com.github.ben-manes.versions` to generate a dependency update report.
*   **Micronaut BOM**: Always use the Micronaut Bill of Materials (BOM) to manage transitive dependency versions symmetrically. Never try to manually pick version numbers for core Micronaut packages.
*   **Java Versions**: Periodically review LTS releases.

## 3. Frontend (SolidStart / Bun)
*   **Bun Outdated**: Run `bun outdated` regularly to see what packages are drifting.
*   **Lockfiles**: NEVER delete `bun.lockb` to "fix a bug" magically. Resolve the specific package problem instead.

## 4. Agent Guardrails
*   ✅ **Check Versions First**: If asked to upgrade a package, the agent should first run the appropriate tool (e.g., `bun outdated` or `./gradlew dependencyUpdates`) to understand the delta.
*   ❌ **No Blind Upgrades**: Do not blindly modify `build.gradle` or `package.json` package versions without checking for breaking changes in their changelogs.
