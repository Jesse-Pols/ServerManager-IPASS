import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Workshop from '@/components/Workshop'
import Login from '@/components/Login'
import ControlPanel from '@/components/ControlPanel'
import Logs from '@/components/Logs'

import store from './store'
import cookies from './cookies';

Vue.use(Router);

const router = new Router({
    mode: 'history',
    routes: [
        { path: '/', component: Home },
        { path: '/login', component: Login },
        { path: '/controlpanel', component: ControlPanel, meta: { requiresAuth: true }},
        { path: '/workshop/:type/:action', component: Workshop, meta: { requiresAuth: true }},
        { path: '/logs', component: Logs },
        { path: '*', redirect: '/' }
    ]
});

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // Check if logged in
        if (!cookies.checkCookie("loggedIn")) {
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