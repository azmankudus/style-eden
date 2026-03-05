# UI/UX & Design System Standards

Consistent design is the foundation of a polished, premium-feeling application. This governs the SolidStart frontend implementation.

## 1. TailwindCSS & Styling 
*   **Vibrant, Modern Aesthetics**: Emphasize vibrant color palettes, subtle gradients, and glassmorphism where appropriate. Avoid "flat" boring designs unless explicitly requested.
*   **Tailwind v4 Variables**: Define all brand colors in the global stylesheet using modern CSS variables integrated with Tailwind.
*   **Dark Mode**: Dark mode support is MANDATORY. Ensure `dark:` variants are used comprehensively across all layouts, cards, and text components.

## 2. Component Reusability
*   **Atomic Components**: Buttons, inputs, dialogs, and cards must be isolated into reusable components (e.g., `src/components/ui/Button.tsx`).
*   **Props & Slots**: Use Solid's generic props and `children` to ensure components are deeply reusable. Avoid hardcoding margins (`mt-4`) inside atomic components; allow the parent layout to dictate spacing.

## 3. Interactivity & Micro-Animations
*   **Hover/Focus States**: Every interactive element MUST have pronounced hover, focus, and active states. Use `ring` utilities for accessibility.
*   **Transitions**: Apply smooth transitions (e.g., `transition-all duration-200`) to everything that changes state, color, or shape.

## 4. Agent Guardrails
*   ❌ **No Inline Styles**: Never hallucinate inline `style="..."` properties unless absolutely required for dynamic calculation (e.g., setting a progress bar percentage). Always use Tailwind classes.
*   ✅ **Verify Against Existing Components**: Before instructing the agent to build a new UI element, verify if a similar component already exists in `src/components/ui/` to prevent duplication.
