# SolidStart Blueprint Conventions

Guidelines for building the reactive frontend within this blueprint.

## 1. Reactivity
*   **Signals**: Fine-grained reactivity for local component state.
*   **Stores**: Deeply-nested state management for global data.
*   **Resources**: Native data fetching with `createResource`.

## 2. Structure & Patterns
*   **File-based Routing**: Nested routes in `src/routes`.
*   **Islands Architecture**: Optimize hydration by isolating interactive components.
*   **TypeScript**: Strict typing across all components and API layers.

## 3. Styling & UX
*   **TailwindCSS v4**: Utility-first styling with modern CSS features.
*   **Solid Icons**: Standardized iconography library.
*   **Responsive Design**: Mobile-first approach.

## 4. Testing Strategy
*   **Vitest**: Component and utility function unit testing.
*   **Playwright**: End-to-end browser testing.
*   **Mocking**: Use MSW (Mock Service Worker) to mock the Micronaut API.

## 5. Anti-Patterns (Anti-Hallucination Guardrails)
*   ❌ **No `any` Types**: Never use `any`. Interfaces/types must strictly mirror the backend DTOs.
*   ❌ **No Prop Drilling**: Avoid passing props through multiple nested components. Use Solid Context or global Signals where appropriate.
*   ❌ **No Direct DOM Manipulation**: Never use `document.getElementById()`. Use Solid's `ref` directive.
*   ❌ **No Hallucinated UI Logic**: Do not place complex business logic in the UI components; that belongs in the Micronaut backend or dedicated frontend utility services.
