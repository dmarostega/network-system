<script setup>
import { ref } from "vue";
import { apiGet, apiPost } from "@/services/api";

const pingResp = ref(null);
const pingErr = ref(null);

const action = ref("ping");
const target = ref("google.com");

const runResp = ref(null);
const runErr = ref(null);

const loading = ref(false);

async function doPing() {
    pingErr.value = null;
    pingResp.value = null;

    try {
        pingResp.value = await apiGet("/api/ping");
    } catch (e) {
        runErr.value = e?.message ?? String(e);
    } finally {
        loading.value = false;
    }
}
</script>

<template>
    <div style="max-width: 900px; margin: 24px auto; padding: 16px; font-family: system-ui;">
        <h2>Command (Vue -> Java)</h2>
        <section style="margin-top:16px; padding: 12px; border: 1px solid #ddd; border-radius: 10px;">
            <h3>Ping</h3>
            <button  type="button" @click="doPing">Get /api/ping</button>

            <pre v-if="pingResp" style="margin-top: 12px;">{{ pingResp }}</pre>
            <pre v-if="pingErr" style="margin-top: 12px; color: #b00020;">Erro:  {{ pingErr }}</pre>
        </section>
        <section style="margin-top: 16px; padding: 12px; border: 1px solid #ddd; border-radius: 10px;">
            <h3>Executar comando</h3>
            <div style="display: flex; gap: 8px; flex-wrap: wrap; align-items: center;">
                <label for="action">
                    action:
                    <input v-model="action" style="margin-left: 6px;" />                    
                </label>

                <label for="target">
                    target:
                    <input v-model="target" style="margin-left: 6px;" />
                </label>

                <button  type="button" @click="runCommand" :disabled="loading">
                    {{ loading ? "Executando..." : "POST /api/commands/run" }}
                </button>
            </div>

            <pre v-if="runResp" style="margin-top: 12px; white-space: pre-wrap;">{{ runResp }}</pre>
            <pre v-if="runErr" style="margin-top: 12px; color: #b00020;">Erro: {{ runErr }}</pre>
        </section>
    </div>
</template>