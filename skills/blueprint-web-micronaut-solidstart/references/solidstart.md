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

## 4. Best Practices
*   **Error Boundaries**: Prevent total app crashes on UI errors.
*   **Suspense**: Native handling of loading states.
*   **Streaming SSR**: Utilize streaming for faster page loads.
