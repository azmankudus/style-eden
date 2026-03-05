import { createSignal, createMemo } from 'solid-js';
import { fetchWithEnvelope, ResponseEnvelope } from '../lib/apiClient';
import enDict from '../locales/en.json';
import msDict from '../locales/ms.json';

interface UserRequest {
  username: string;
  email: string;
}

interface UserResponse {
  id: number;
  username: string;
  email: string;
  status: string;
}

const locales: Record<string, any> = {
  en: enDict,
  ms: msDict
};

export default function UserForm() {
  const [lang, setLang] = createSignal('en');
  const [username, setUsername] = createSignal('');
  const [email, setEmail] = createSignal('');
  const [response, setResponse] = createSignal<ResponseEnvelope<UserResponse> | null>(null);
  const [loading, setLoading] = createSignal(false);

  // Simple dict translation hook
  const t = createMemo(() => locales[lang()]);

  const handleSubmit = async (e: Event) => {
    e.preventDefault();
    setLoading(true);
    setResponse(null);

    try {
      const res = await fetchWithEnvelope<UserRequest, UserResponse>(
        '/api/v1/users',
        'POST',
        { username: username(), email: email() },
        true, // Enable metadata
        lang() // HTTP Accept-Language header
      );
      setResponse(res);
    } catch (err) {
      console.error("Network error:", err);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div class="p-4 max-w-md mx-auto relative rounded-xl shadow-lg border border-gray-200 mt-12">
      {/* Locale Switcher */}
      <div class="absolute -top-10 right-0 flex gap-2">
        <button
          type="button"
          onClick={() => setLang('en')}
          class={`px-3 py-1 rounded-t-md text-sm ${lang() === 'en' ? 'bg-blue-600 text-white' : 'bg-gray-200 text-gray-700'}`}>
          EN
        </button>
        <button
          type="button"
          onClick={() => setLang('ms')}
          class={`px-3 py-1 rounded-t-md text-sm ${lang() === 'ms' ? 'bg-blue-600 text-white' : 'bg-gray-200 text-gray-700'}`}>
          MS
        </button>
      </div>

      <h2 class="text-xl font-bold mb-4">{t()["form.title"]}</h2>
      <form onSubmit={handleSubmit} class="flex flex-col gap-3">
        <input
          type="text"
          placeholder={t()["form.username"]}
          value={username()}
          onInput={(e) => setUsername(e.currentTarget.value)}
          class="border px-3 py-2 rounded focus:ring-2 focus:ring-blue-500"
          required
        />
        <input
          type="email"
          placeholder={t()["form.email"]}
          value={email()}
          onInput={(e) => setEmail(e.currentTarget.value)}
          class="border px-3 py-2 rounded focus:ring-2 focus:ring-blue-500"
          required
        />
        <button
          type="submit"
          disabled={loading()}
          class="bg-blue-600 text-white py-2 rounded hover:bg-blue-700 disabled:opacity-50"
        >
          {loading() ? t()["form.submitting"] : t()["form.submit"]}
        </button>
      </form>

      {/* Render Response Envelope Result */}
      {response() && (
        <div class="mt-6 p-3 bg-gray-50 rounded text-sm overflow-auto">
          {response()?.errors ? (
            <div class="text-red-600">
              <strong>{t()["form.error"]}</strong>
              <pre class="mt-2 whitespace-pre-wrap">{JSON.stringify(response()?.errors, null, 2)}</pre>
            </div>
          ) : (
            <div class="text-green-700">
              <strong>{t()["form.success"]}</strong>
              <pre class="mt-2 whitespace-pre-wrap">{JSON.stringify(response()?.data, null, 2)}</pre>
            </div>
          )}

          {response()?.meta && (
            <div class="mt-4 text-gray-600 border-t pt-2">
              <strong>{t()["form.meta"]}</strong>
              <pre class="mt-2 whitespace-pre-wrap">{JSON.stringify(response()?.meta, null, 2)}</pre>
            </div>
          )}
        </div>
      )}
    </div>
  );
}
