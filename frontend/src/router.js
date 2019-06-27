import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import DienstWorkshop from '@/components/DienstWorkshop'
import Login from '@/components/Login'
import ControlPanel from '@/components/ControlPanel'

import store from './store'

Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes: [
        { path: '/', component: Home },
        { path: '/login', component: Login },
        { path: '/controlpanel', component: ControlPanel, meta: { requiresAuth: true }},
        { path: '/dienst/:action', component: DienstWorkshop, meta: { requiresAuth: true }},
        { path: '*', redirect: '/' }
    ]
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // Check if logged in
        if (!store.getters.isLoggedIn) {
            next({
                path: '/login'
            })
        } else {
            next();
        }
    } else {
        next();
    }
});

export default router;