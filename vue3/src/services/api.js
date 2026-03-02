export async function apiGet(url) {
    const r = await fetch(url);
    const data = await r.json().catch(() => null);
    if(!r.ok) throw new Error(data?.message ?? `HTTP' ${r.status}`);
    return data;
}

export async function apiPost(url, body) {
    const r = await fetch(url, {
        method: "POST",
        headers: { "Content-type":"application/json"},
        body: JSON.stringify(body)
    });
    const data = await r.json().catch(() => null);
    if(!r.ok) throw new Error(data?.message ?? `HTTP ${r.status}`);
    return data;
}