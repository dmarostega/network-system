import { createRouter, createWebHistory } from 'vue-router';
import CommandsPage from '@/pages/CommandsPage.vue';  

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: "/", redirect: "/commands"},
    { path: "/commands", component: CommandsPage },
  ],
})

export default router
